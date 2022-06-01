package com.example.demo.security;

import com.example.demo.payload.response.ResponseWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.FORBIDDEN;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private final JWTUtils jwtUtils = new JWTUtils();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().equals("/login") || request.getServletPath().equals("/api/auth/token/refresh")){
            filterChain.doFilter(request, response);
        } else {
            String header = request.getHeader(SecurityConstants.HEADER_STRING);
            if(header != null && header.startsWith(SecurityConstants.TOKEN_PREFIX)){
                String token = header.substring(SecurityConstants.TOKEN_PREFIX.length());
                try{
                    jwtUtils.verifyToken(token);
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken
                            (jwtUtils.getUsernameFromToken(token), null, jwtUtils.getRolesFromToken(token));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request,response);
                } catch (Exception e) {
                    response.setStatus(FORBIDDEN.value());
                    ResponseWrapper responseWrapper = new ResponseWrapper();
                    responseWrapper.putMessage("error", e.getMessage());
                    response.setContentType(SecurityConstants.CONTENT_TYPE);

                    new ObjectMapper().writeValue(response.getOutputStream(), responseWrapper.getMessage());
                }
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }
}
