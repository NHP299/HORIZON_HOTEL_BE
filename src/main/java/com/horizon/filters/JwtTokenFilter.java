package com.horizon.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.horizon.response.ResponseObject;
import com.horizon.util.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    @Value("${spring.application.api-prefix-home}")
    private String apiPrefix;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws IOException {

        try {
            if (isByPassToken(request)) {
                filterChain.doFilter(request, response); // enable bypass
                return;
            }
            //request token
            final String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                writeErrorResponse(response, HttpStatus.UNAUTHORIZED, "Unauthorized", "Missing or invalid Authorization header");
                return;
            }
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                final String token = authHeader.substring(7);
                final String email = jwtTokenUtil.extractEmail(token);
                if (email != null &&
                        SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                    if (jwtTokenUtil.validateToken(token, userDetails)) {
                        UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(
                                        userDetails,
                                        null,
                                        userDetails.getAuthorities()
                                );
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }
            }
            filterChain.doFilter(request, response); // enable bypass
        } catch (Exception e) {
            writeErrorResponse(response, HttpStatus.UNAUTHORIZED, "Unauthorized", "An error occurred during token validation");
        }
        //request non-token

    }

    private boolean isByPassToken(@NonNull HttpServletRequest request) {
        final List<org.springframework.data.util.Pair<String, String>> bypassTokens = Arrays.asList(
                Pair.of(String.format("%s/accounts/register", apiPrefix), "POST"),
                Pair.of(String.format("%s/accounts/login", apiPrefix), "POST"),
                Pair.of(String.format("%s/accounts/logout", apiPrefix), "POST"),
                Pair.of(String.format("%s/accounts/current-user", apiPrefix), "GET"),
                Pair.of(String.format("%s/accounts/change-password", apiPrefix), "POST"),
                Pair.of(String.format("%s/promotion", apiPrefix), "GET"),
                Pair.of(String.format("%s/promotion/apply", apiPrefix), "POST"),
                Pair.of(String.format("%s/room-type", apiPrefix), "GET"),
                Pair.of(String.format("%s/rooms", apiPrefix), "GET"),
                Pair.of(String.format("%s/bookings", apiPrefix), "GET"),
                Pair.of(String.format("%s/payment/vn-pay-callback", apiPrefix), "GET"),
                Pair.of(String.format("%s/payment/vn-pay", apiPrefix), "POST"),
                Pair.of(String.format("%s/payment/cash", apiPrefix), "POST")
        );

        String requestURI = request.getRequestURI();
        for (Pair<String, String> bypassToken : bypassTokens) {
            if (requestURI.startsWith(bypassToken.getFirst()) && request.getMethod().equals(bypassToken.getSecond())) {
                return true;
            }
        }

        return false;
    }

    private void writeErrorResponse(HttpServletResponse response, HttpStatus status, String message, String data) throws IOException {
        ResponseObject<?> responseObject = new ResponseObject<>(
                status,
                message,
                data
        );
        response.setStatus(status.value());
        response.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(responseObject);
        response.getWriter().write(jsonResponse);
    }


}



