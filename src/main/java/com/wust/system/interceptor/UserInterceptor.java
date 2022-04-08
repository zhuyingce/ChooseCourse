package com.wust.system.interceptor;

import com.wust.system.config.JwtConfig;
import com.wust.system.dao.UserTokenDao;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private UserTokenDao userTokenDao;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws SignatureException {

        //获取cookie(userId)
        Cookie[] cookies = request.getCookies();
        String userId = "";
        if (cookies != null) {

            for (Cookie cookie : cookies) {

                if ("userId".equals(cookie.getName())) {

                    userId = cookie.getValue();
                }
            }
        }

        /* Token 验证 */
        //获取http请求头的token信息
        String token = request.getHeader(jwtConfig.getHeader());

        //如果有携带token
        if (token == null || "".equals(token)) {

            throw new SignatureException("token不能为空!");
        }

        //如果已经退出登录，数据库中token为null，手动带上的假token
        if (userTokenDao.queryTokenByUserId(Integer.parseInt(userId)) == null) {

            throw new SignatureException("token无效，请重新登录!");
        }

        Claims claims = null;
        try{
            claims = jwtConfig.getTokenClaim(token);
            if(claims == null || jwtConfig.isTokenExpired(claims.getExpiration())){
                throw new SignatureException("token无效，请重新登录!");
            }
        }catch (Exception e){
            throw new SignatureException("token无效，请重新登录!");
        }

        return true;
    }
}
