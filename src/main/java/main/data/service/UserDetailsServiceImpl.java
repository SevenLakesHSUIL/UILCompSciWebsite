package main.data.service;

import main.data.User;
import main.data.UserDetailsImpl;
import main.data.persistence.UserDetailsImplRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDetailsImplRepository userDetailsImplRepository;

    @Autowired
    public UserDetailsServiceImpl(UserDetailsImplRepository userDetailsImplRepository) {
        this.userDetailsImplRepository = userDetailsImplRepository;
        userDetailsImplRepository.save(new User("a", "a", true));
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDetailsImpl user = userDetailsImplRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }
}