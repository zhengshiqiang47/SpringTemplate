package com.dyfwz.controller;

import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class CorsFilter extends OncePerRequestFilter {
    //protected static Logger logger = LoggerFactory.getLogger(CorsFilter.class);

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        logger.info("entering FILTER page");

        if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) {
            // CORS "pre-flight" request
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Content-Type");
            response.addHeader("Access-Control-Max-Age", "1800");//30 min
        }
        //This will filter your requests and responses.
        filterChain.doFilter(request, response);
    }

}