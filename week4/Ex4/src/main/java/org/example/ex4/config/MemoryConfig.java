package org.example.ex4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class MemoryConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsServiceMemory(PasswordEncoder encoder) {
        UserDetails user1 = User.withUsername("testUser")
                .password(encoder.encode("password"))
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password(encoder.encode("password"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user1, admin);
    }
}
