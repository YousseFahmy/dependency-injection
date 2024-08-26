package com.talent.dependency_injection.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HeaderAuthenticationFilter extends OncePerRequestFilter {


    @Override
    @SuppressWarnings("null")
    protected void doFilterInternal(HttpServletRequest request, 
            HttpServletResponse response, 
            FilterChain filterChain)
            throws ServletException, IOException {

        ArrayList<?> headers = Collections.list(request.getHeaderNames());

        if (! headers.contains("token")) {
            filterChain.doFilter(request, response);
            return;
        }
        
        String tokenHeader = request.getHeader("Token");

        if(!tokenHeader.equals("Admin")){
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.getWriter().println("Token header missing or incorrect.");
            return;
        }

        SecurityContext newCtx = SecurityContextHolder.createEmptyContext();
        newCtx.setAuthentication(new HeaderAuthentication());
        SecurityContextHolder.setContext(newCtx);
        filterChain.doFilter(request, response);
        return;
    }
    
}
