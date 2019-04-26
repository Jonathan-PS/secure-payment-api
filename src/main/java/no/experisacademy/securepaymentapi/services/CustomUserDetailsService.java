package no.experisacademy.securepaymentapi.services;

import no.experisacademy.securepaymentapi.models.CustomUserDetails;
import no.experisacademy.securepaymentapi.models.RegisteredUser;
import no.experisacademy.securepaymentapi.repositories.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<RegisteredUser> optionalRegisteredUser = registeredUserRepository.findByEmail(username);

        optionalRegisteredUser
                .orElseThrow(() -> new UsernameNotFoundException("Email not found"));
        return optionalRegisteredUser
                .map(CustomUserDetails::new).get();
    }
}
