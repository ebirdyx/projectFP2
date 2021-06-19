package io.kyorg.springsecuritydemo.configuration;

import io.kyorg.springsecuritydemo.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AppUserService appUserService;
    private final JwtTokenHelper jwtTokenHelper;
    private final RestAuthEntryPoint restAuthEntryPoint;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // in-memory auth
        auth.inMemoryAuthentication()
                .withUser("Imad")
                .password(passwordEncoder().encode("123"))
                .authorities("USER", "ADMIN");

        // database auth
        auth.userDetailsService(appUserService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // authorize all
        //http.authorizeRequests().anyRequest().permitAll();

        // authorize authenticated
        //http.authorizeRequests().anyRequest().authenticated();

        //http.authorizeRequests(
        //        request -> request.antMatchers("/h2-console/**")
        //                .permitAll()
        //                .anyRequest()
        //                .authenticated()
        //).httpBasic();

        http
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                    .authenticationEntryPoint(restAuthEntryPoint)
                .and()
                .authorizeRequests(
                        request -> request.antMatchers("/h2-console/**", "/api/v1/auth/login")
                                .permitAll()
                                .antMatchers(HttpMethod.OPTIONS, "/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .addFilterBefore(
                        new JwtAuthenticationFilter(appUserService, jwtTokenHelper),
                        UsernamePasswordAuthenticationFilter.class
                );


        http.cors();

        http.csrf().disable().headers().frameOptions().disable();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
