package com.enigmacamp.shopify.security;

import com.enigmacamp.shopify.model.dto.response.JwtClaims;
import com.enigmacamp.shopify.model.entity.UserAccount;
import com.enigmacamp.shopify.service.JwtService;
import com.enigmacamp.shopify.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && jwtService.verifyJwtToken(bearerToken)) {
            JwtClaims jwtClaims = jwtService.getJwtClaims(bearerToken);
            UserAccount userAccount = userService.loadUserById(jwtClaims.getUserAccountId());
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userAccount.getUsername(),
                    null,
                    userAccount.getAuthorities()
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
