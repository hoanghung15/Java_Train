package org.example.ex4.Util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.example.ex4.entity.User;
import org.example.ex4.repository.UserRepository;
import org.example.ex4.service.JwtService;
import org.example.ex4.service.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {

    TokenService tokenService;
    JwtService jwtService;
    UserRepository userRepository;
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

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        String accessToken = null;
        String refreshToken = null;
        User user = userRepository.findByEmail((email));
        if (user == null) {
            user = new User();
            user.setUsername(name);
            user.setEmail(email);
            user.setRole("USER");
            userRepository.save(user);

        }
        accessToken = jwtService.generateToken(user, true, expAccess, accessKey);
        refreshToken = jwtService.generateToken(user, false, expRefresh, refreshKey);
        String redirectUrl = UriComponentsBuilder
                .fromUriString("http://localhost:5500/index.html")
                .queryParam("accessToken", accessToken)
                .queryParam("refreshToken", refreshToken)
                .build()
                .toUriString();
        response.sendRedirect(redirectUrl);

        log.info("Redirect to {}", redirectUrl);
    }
}
