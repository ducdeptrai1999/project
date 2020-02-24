//package com.buiminhduc.security;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import java.io.IOException;
//import java.util.logging.LogRecord;
//
//@WebFilter(urlPatterns = {"/*"})
//public class CounterFilter implements Filter {
//    private int count = 0;
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        count=0;
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        count++;
//        System.out.println(count);
//        chain.doFilter(request,response);
//    }
//    @Override
//    public void destroy() {
//
//    }
//}
