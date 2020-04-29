package be.kevin.ListCourse.utils.configSecu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter  {

//    @Autowired
//     UserService userService;
     @Autowired
    DaoAuthenticationProvider daoAuthenticationProvider;

//     @Autowired
//     PasswordEncoder passwordEncoder;

//    public SecurityConfig(UserService userService) {
//        this.userService = userService;
//    }

    @Override
    protected void configure (AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(new Http403ForbiddenEntryPoint() {})
                .and()
                .authenticationProvider(daoAuthenticationProvider)
                .formLogin()
                .loginProcessingUrl("/login")
                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
                .and()
                .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/logout").permitAll()
                .antMatchers("/user").authenticated()
                .anyRequest().permitAll()
                .and()
                .headers()
                .frameOptions()
                .disable();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder(){
//
//       return new BCryptPasswordEncoder();
//    }
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        return new AppAuth(passwordEncoder());
//    }


}
