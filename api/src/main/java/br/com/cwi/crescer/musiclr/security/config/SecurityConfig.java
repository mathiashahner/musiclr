package br.com.cwi.crescer.musiclr.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.web.http.HttpSessionIdResolver;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.session.web.http.HeaderHttpSessionIdResolver.xAuthToken;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public HttpSessionIdResolver httpSessionIdResolver() {
        return xAuthToken();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().and()

                .authorizeRequests().antMatchers(POST, "/login", "/usuarios").permitAll().and()

                .authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic();
    }
}
