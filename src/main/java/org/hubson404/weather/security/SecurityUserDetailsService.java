package org.hubson404.weather.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {

    private final SecurityUserRepository securityUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return securityUserRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(username + " not found"));

//        log.info("Login attempt: {}", username);
//
//        throw new UsernameNotFoundException("username " + " not found");
    }
}
