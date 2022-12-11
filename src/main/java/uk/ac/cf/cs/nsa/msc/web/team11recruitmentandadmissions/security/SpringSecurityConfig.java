package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private MyUserDetailsServiceImpl userDetailsService;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/js/**");
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Allow unauthorized access to "/login", "/register" "/register-save"
        // Access to other addresses requires authentication permissions
        http.authorizeRequests()
                .antMatchers("/login", "/register", "/register-save", "/error").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/candidates", true)
                // Username and Userpassword parameter names
                .passwordParameter("password")
                .usernameParameter("username")
                .loginPage("/login")
                // Login error jump to /login-error
                .failureUrl("/login-error")
                .permitAll()
                .and()
                // Set the logout URL and jump page after successful logout
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login");
    }
}