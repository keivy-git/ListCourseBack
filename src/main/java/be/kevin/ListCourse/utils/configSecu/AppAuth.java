package be.kevin.ListCourse.utils.configSecu;

import be.kevin.ListCourse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Cette classe récupere les détails user à partir de la class dao utilisateur en lecture seule
 */
@Component
public class AppAuth extends DaoAuthenticationProvider {
//    @Autowired
//    UserService userService;
//    @Autowired
//    PasswordEncoder passwordEncoder;


    public AppAuth(PasswordEncoder passwordEncoder, UserService userService) {
        this.setUserDetailsService(userService);
        this.setPasswordEncoder(passwordEncoder);
    }



    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;

        String name = auth.getName();
        String password = auth.getCredentials()
                  .toString();

        UserDetails user = getUserDetailsService().loadUserByUsername(name);

        if (user == null) {
            throw new BadCredentialsException("Le nom d'utilisateur et le mot de passe ne correspond pas à " + auth.getPrincipal());
        }
        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }


}
