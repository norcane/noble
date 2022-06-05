package com.norcane.noble.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private static final String STATIC_RESOURCES = "/jakarta.faces.resource/**";

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        // made static to break circular dependency (see https://stackoverflow.com/a/70265714)
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain httpSecurity(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers(STATIC_RESOURCES).permitAll()
            .antMatchers("/admin/login", "/admin/logout").permitAll()
            .antMatchers("/admin/**").authenticated()
            .antMatchers("/**").permitAll()
            .and()
            .formLogin().loginPage("/admin/login")
            .and()
            .logout().logoutSuccessUrl("/admin/login").invalidateHttpSession(true).deleteCookies("JSESSIONID")
            .and()
            .exceptionHandling().accessDeniedPage("/error")
            .and()
            .csrf().disable();

        return http.build();
    }
}
