package com.getir.book.util.security;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void configure(HttpSecurity aHttpSecurity) throws Exception {
        aHttpSecurity.csrf().disable().httpBasic()
                .disable();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/**");
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE + 1)
    FilterRegistrationBean<UserAuthenticationFilter> userAuthenticationFilter() {
        final FilterRegistrationBean<UserAuthenticationFilter> registration = new FilterRegistrationBean<>(new UserAuthenticationFilter(jwtTokenProvider));
        registration.setName("User Authentication Filter");
        registration.addUrlPatterns("/api/v1/*");
        return registration;
    }

}