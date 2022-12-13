package org.omsoft.auth;

import org.omsoft.entity.MyUser;
import org.omsoft.repo.UserRepo;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private UserRepo repo;
    public CustomAuthenticationProvider(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String userName = authentication.getName();
        final String password = authentication.getCredentials().toString();
        MyUser myUser = repo.findByUserName(userName);
        if(myUser != null && myUser.getUserPassword().equals(password)){
            final List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(myUser.getUserRole()));
            final UserDetails userDetails = new User(userName,password,authorities);
            final Authentication auth = new UsernamePasswordAuthenticationToken(userDetails,password,authorities);
            return auth;
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
