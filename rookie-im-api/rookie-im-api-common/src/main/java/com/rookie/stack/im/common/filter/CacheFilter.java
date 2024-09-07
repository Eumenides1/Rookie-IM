package com.rookie.stack.im.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author eumenides
 * @description 缓存过滤器
 * @date 2024/9/7
 */
@Component
@ServletComponentScan
@WebFilter(urlPatterns = "/*",filterName = "xssFilter")
public class CacheFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(new CacheHttpServletRequestWrapper((HttpServletRequest) request), response);
    }
}
