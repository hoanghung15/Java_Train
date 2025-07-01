package org.example.ex4.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.example.ex4.dto.CustomUserDetails;
import org.example.ex4.entity.User;
import org.example.ex4.repository.UserRepository;
import org.example.ex4.service.JwtService;
import org.example.ex4.service.TokenService;
import org.example.ex4.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtFilter extends OncePerRequestFilter {
    @NonFinal
    @Value("${jwt.access.key}")
    String accessKey;
    @NonFinal
    @Value("${jwt.refresh.key}")
    String refreshKey;
    @NonFinal
    @Value("${jwt.access.expire}")
    int expAccess;
    @Value("${jwt.refresh.expire}")
    @NonFinal
    int expRefresh;
    JwtService jwtService;
    UserRepository userRepository;
    TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header != null) {
            String accessToken = null;
            String username = null;

            if (header.startsWith("Bearer ")) {
                accessToken = header.substring(7);
                username = jwtService.extractUsername(accessToken, accessKey);
            }
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                User user = userRepository.findByUsername(username);
                UserDetails userDetails = new CustomUserDetails(user);
                List<String> listTokenStored = tokenService.getTokenFromRedis(username);

                if (listTokenStored != null && listTokenStored.contains(accessToken) && jwtService.validateToken(accessToken, userDetails, accessKey)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            filterChain.doFilter(request, response);
        } else {
            filterChain.doFilter(request, response);
        }

    }

}
