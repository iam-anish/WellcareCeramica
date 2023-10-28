package com.wellcareceramica.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails wellcare = User.builder()
                .username("wellcare")
                .password(passwordEncoder().encode("well#123"))
                .build();
        return new InMemoryUserDetailsManager(wellcare);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf-> csrf.disable())
                .cors(cors-> cors.disable())
                .authorizeHttpRequests(auth-> auth
                        .requestMatchers(HttpMethod.GET,"/product/**")
                .permitAll()
                .requestMatchers(HttpMethod.GET,"/category/**")
                .permitAll()
                .requestMatchers(HttpMethod.GET,"/type/**")
                .permitAll()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());

//                .authorizeHttpRequests(rmr -> rmr.anyRequest()
//                .authenticated())
////                .requestMatchers(PUBLIC_URLS)
////                .permitAll()
////                .requestMatchers(HttpMethod.GET,"/product/**")
////                .permitAll()
////                .requestMatchers(HttpMethod.GET,"/category/**")
////                .permitAll()
////                .requestMatchers(HttpMethod.GET,"/type/**")
////                .permitAll()
//                .exceptionHandling()
//                .and()
//                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public DaoAuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(this.userDetailsService);
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//        return daoAuthenticationProvider;
//    }

    @Bean
    public FilterRegistrationBean corsFilter(){

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedHeader("mode");
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000","http://172.16.101.137:3000"));
        corsConfiguration.addAllowedOriginPattern("*");
//        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("Authorization");
        corsConfiguration.addAllowedHeader("Content-Type");
        corsConfiguration.addAllowedHeader("Accept");
        corsConfiguration.addAllowedMethod("GET");
        corsConfiguration.addAllowedMethod("POST");
        corsConfiguration.addAllowedMethod("PUT");
        corsConfiguration.addAllowedMethod("DELETE");
        corsConfiguration.addAllowedMethod("OPTIONS");
        corsConfiguration.setMaxAge(3600L);
        source.registerCorsConfiguration("/**",corsConfiguration);

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean<>(new CorsFilter(source));
        filterRegistrationBean.setOrder(-100);
        return  filterRegistrationBean;
    }
}
