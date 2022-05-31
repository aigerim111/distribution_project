package com.example.demo.security;

import com.example.demo.entities.CustomUserDetails;
import com.example.demo.entities.User;
import com.example.demo.payload.response.ResponseWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager manager;
    private final JWTUtils jwtUtils = new JWTUtils();

    public JWTAuthenticationFilter(AuthenticationManager manager) {
        this.manager = manager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        return manager.authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        CustomUserDetails user = (CustomUserDetails) authResult.getPrincipal();
        User user1 = user.getUser();

        String accessToken = jwtUtils.generateAccessToken(user1);
        String refreshToken = jwtUtils.generateRefreshToken(user1);

        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.putMessage("accessToken", accessToken);
        responseWrapper.putMessage("refreshToken", refreshToken);
        response.setContentType(SecurityConstants.CONTENT_TYPE);

        new ObjectMapper().writeValue(response.getOutputStream(), responseWrapper.getMessage());
    }
}
