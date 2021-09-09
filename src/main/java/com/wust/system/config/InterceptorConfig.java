package com.wust.system.config;

import com.wust.system.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport
{    //拦截器配置类需要实现这个类

    @Autowired
    private UserInterceptor interceptor;

    @Override
    public void addInterceptors( InterceptorRegistry registry )
    {
        //定义要拦截的路径
        String[] addPathPattern = {
                "/**"
        };
        //定义排除拦截的路径
        String[] excludePathPattern = {
                "/user/login",
                "/user/register",
        };

        registry.addInterceptor( interceptor )
                .addPathPatterns( addPathPattern )
                .excludePathPatterns( excludePathPattern );
    }
}
