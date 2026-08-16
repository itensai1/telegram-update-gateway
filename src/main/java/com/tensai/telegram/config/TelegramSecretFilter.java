package com.tensai.telegram.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class TelegramSecretFilter extends OncePerRequestFilter {
    @Value("${telegram.webhook-secret}")
    private String expectedSecret;
    private final static String expectedHeader = "X-Telegram-Bot-Api-Secret-Token";


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String clientSecret = request.getHeader(expectedHeader);

        if (clientSecret == null) {
            filterChain.doFilter(request, response);
            return;
        }

        if (expectedSecret.equals(clientSecret)) {
            var auth = new UsernamePasswordAuthenticationToken("TelegramWebhook",
                    null,
                    List.of(new SimpleGrantedAuthority("ROLE_TELEGRAM")));
            SecurityContextHolder.getContext().setAuthentication(auth);
        }else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        filterChain.doFilter(request, response);
    }
}
