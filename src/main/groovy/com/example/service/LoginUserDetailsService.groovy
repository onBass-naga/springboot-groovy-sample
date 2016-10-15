package com.example.service

import com.example.domain.User
import com.example.repository.UserRepository
import groovy.transform.Canonical
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User as SSUser
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class LoginUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOne(username)
        if (user == null) {
            throw new UsernameNotFoundException("The requested user is not found.")
        }
        return new LoginUserDetails(user)
    }
}

@Canonical
class LoginUserDetails extends SSUser {
    private final User user;

    LoginUserDetails(User user) {
        super(user.getUsername(),
                user.getEncodedPassword(),
                AuthorityUtils.createAuthorityList("ROLE_USER"))

        this.user = user
    }

    User getUser() {
        return this.user
    }
}