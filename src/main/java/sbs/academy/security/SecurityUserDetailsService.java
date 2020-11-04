package sbs.academy.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sbs.academy.data.User;
import sbs.academy.repositories.UserRepositorie;
@Service
public class SecurityUserDetailsService implements UserDetailsService {
    private UserRepositorie userRepositorie;

    public SecurityUserDetailsService(UserRepositorie userRepositorie) {
        this.userRepositorie = userRepositorie;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepositorie.findByMail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new SecurityUserPrincipal(user);
    }
}
