package com.gurshobit.collegefestival.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig {
    private static final String[] WHITELISTED = {
            "/",
            "/403",
            "/404",
            "/resources/**",
            "/*.css"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(WHITELISTED).permitAll()
                .antMatchers(HttpMethod.GET,"/students/list","/students/add").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                .antMatchers(HttpMethod.POST,"/students/save").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                .antMatchers(HttpMethod.GET,"/students/update","/students/delete").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated();
        http
                .formLogin()
                .loginProcessingUrl("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/students/list",true)
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout")
                .and()
                .httpBasic();

        http.exceptionHandling().accessDeniedPage("/403");

        http.csrf().disable();
        http.cors().disable();
        http.headers().frameOptions().disable();
        return http.build();
    }

}
