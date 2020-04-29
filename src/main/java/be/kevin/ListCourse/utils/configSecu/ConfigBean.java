package be.kevin.ListCourse.utils.configSecu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
Cette classe est dédié au @Bean tandis que la class SecurtyConfig est dédié à la configuration de la sécurité.
 */

@Configuration
public class ConfigBean {

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }
}
