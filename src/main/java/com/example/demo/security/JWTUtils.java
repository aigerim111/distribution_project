package com.example.demo.security;

import com.auth0.jwt.*;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JWTUtils {

    public String generateAccessToken(User user){
        Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.KEY.getBytes());

        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(getExpiryDate(10))
                .withClaim("roles", setClaims(user.getRoles()))
                .sign(algorithm);
    }

    public String generateRefreshToken(User user){
        Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.KEY.getBytes());

        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(getExpiryDate(60))
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .sign(algorithm);
    }


    public void verifyToken(String token) throws Exception {
        Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.KEY.getBytes());
        try{
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            jwtVerifier.verify(token);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public String getUsernameFromToken(String token){
        Map<String, Object> claims = new HashMap<>();

        Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.KEY.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);

        return decodedJWT.getSubject();
    }

    public Collection<SimpleGrantedAuthority> getRolesFromToken(String token){

        Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.KEY.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);

        List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));

        return authorities;
    }


    public Date getExpiryDate(int minutes){
        return new Date(SecurityConstants.EXPIRATION_TIME * minutes + System.currentTimeMillis());
    }

    public List<String> setClaims(Collection<Role> roles){
        List<String> rolesList = new ArrayList<>();
        roles.forEach(role -> rolesList.add(role.getName()));
        return rolesList;
    }
}
