package com.projetojwt.backend.security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.projetojwt.backend.service.JwtService;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");

        if (authorization != null &&
            authorization.startsWith("Bearer ")) {

            String token = authorization.substring(7);

            if (jwtService.tokenValido(token)) {

                Claims claims = jwtService.extrairClaims(token);

                String email = claims.getSubject();
                String role = claims.get("role", String.class);

                var authority =
                    new SimpleGrantedAuthority("ROLE_" + role);

                var authentication =
                    new UsernamePasswordAuthenticationToken(
                        email,
                        null,
                        List.of(authority)
                    );

                SecurityContextHolder
                    .getContext()
                    .setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}