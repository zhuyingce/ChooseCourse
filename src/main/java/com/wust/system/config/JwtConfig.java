package com.wust.system.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@ConfigurationProperties(prefix = "config.jwt")
@Component
@Data
public class JwtConfig {
 
    private String secret;
    private long expire;
    private String header;
 
    //生成token
    public String createToken (String subject){
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);//过期时间
 
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(subject)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    //获取token中注册信息
    public Claims getTokenClaim (String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        }catch (Exception e){
            return null;
        }
    }
    //验证token是否过期失效
    public boolean isTokenExpired (Date expirationTime) {
        return expirationTime.before(new Date());
    }
 
    //获取token失效时间
    public Date getExpirationDateFromToken(String token) {
        return getTokenClaim(token).getExpiration();
    }

    //获取Id从token中
    /*public Integer getIdFromToken(String token) {
        return Integer.parseInt(getTokenClaim(token).getSubject());
    }*/

    public Integer getIdFromToken(String token) {
        return Integer.parseInt(getTokenClaim(token).getSubject());
    }

    //获取jwt发布时间
    public Date getIssuedAtDateFromToken(String token) {
        return getTokenClaim(token).getIssuedAt();
    }
 
}