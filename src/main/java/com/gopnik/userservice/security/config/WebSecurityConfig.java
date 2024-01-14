package com.gopnik.userservice.security.config;


import com.gopnik.userservice.appuser.AppUserService;
import com.gopnik.userservice.jwtimplementation.JWTAuthenticationEntryPoint;
import com.gopnik.userservice.jwtimplementation.JWTAuthenticationFilter;
import com.gopnik.userservice.security.PasswordEncoder;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig {

    private JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private JWTAuthenticationFilter jwtAuthenticationFilter;
    private AuthenticationProvider authenticationProvider;

    //Below filter chain is authorizing login and registration
    // to any user and applying authentication on any other uri.
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("api/v*/registration/**")
                                .permitAll()
                                .requestMatchers("/auth/login")
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .exceptionHandling(this::authenticationEntryPoint)
                .authenticationProvider(authenticationProvider)
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    private void authenticationEntryPoint(ExceptionHandlingConfigurer<HttpSecurity> ex) {
        ex.authenticationEntryPoint(jwtAuthenticationEntryPoint);
    }
}