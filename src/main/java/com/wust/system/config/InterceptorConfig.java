package com.wust.system.config;

import com.wust.system.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    //拦截器配置类需要实现这个类

    @Autowired
    private UserInterceptor interceptor;

    @Override
    public void addInterceptors( InterceptorRegistry registry ){
        //定义要拦截的路径
        String[] addPathPattern = {
                "/**"
        };
        //定义排除拦截的路径
        String[] excludePathPattern = {
                "/user/login",
                "/user/register",
                "/static/**"
        };

        registry.addInterceptor( interceptor )
                .addPathPatterns( addPathPattern )
                .excludePathPatterns( excludePathPattern );
    }

    //定义静态资源访问
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //需要配置1：----------- 需要告知系统，这是要被当成静态文件的！
        //第一个方法设置访问路径前缀，第二个方法设置资源路径
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
