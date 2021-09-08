package com.wust.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CrossConfig
{
    private CorsConfiguration corsConfig( )
    {
        CorsConfiguration corsConfiguration = new CorsConfiguration( );
        corsConfiguration.addAllowedOrigin( "http://localhost:8080" );  //允许所有域名访问
        corsConfiguration.addAllowedHeader( "*" );  //允许所有请求头
        corsConfiguration.addAllowedMethod( "*" );  //允许所有的请求类型
        corsConfiguration.setMaxAge( 3600L );
        corsConfiguration.setAllowCredentials( true ); //允许请求携带验证信息（cookie）
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter( )
    {
        //存储request与跨域配置信息的容器，基于url的映射
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource( );
        source.registerCorsConfiguration( "/**", corsConfig( ) );
        return new CorsFilter( source );
    }

}


//package team.taoyuan.config;
//
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
////import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebFilter( urlPatterns = "/*", filterName = "CORSFilter" )
//public class CrossConfig implements Filter
//{
//    @Override
//    public void init( FilterConfig filterConfig )
//    {
//    }
//
//    @Override
//    public void destroy( )
//    {
//    }
//
//    @Override
//    public void doFilter( ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain ) throws ServletException, IOException
//    {
////        HttpServletRequest req = ( HttpServletRequest ) servletRequest;
//        HttpServletResponse resp = ( HttpServletResponse ) servletResponse;
////        String origin = req.getHeader( "Origin" );
////        if ( origin == null )
////        {
////            origin = req.getHeader( "Referer" );
////        }
//        String origin = "http://localhost:8080";
//
//        resp.setHeader( "Access-Control-Allow-Origin", origin );//这里不能写*，*代表接受所有域名访问，如写*则下面一行代码无效。谨记
//        resp.setHeader( "Access-Control-Allow-Credentials", "true" );//true代表允许携带cookie
//        chain.doFilter( servletRequest, servletResponse );
//    }
//
//
//}