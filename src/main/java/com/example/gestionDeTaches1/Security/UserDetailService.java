package com.example.gestionDeTaches1.Security;


import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.gestionDeTaches1.Model.User;
import com.example.gestionDeTaches1.Repository.UserRepository;

/**
 * Permet à Spring Security d'authentifier un Employee via son email + mot de passe (haché).
 * Chaque employé a le rôle ROLE_EMPLOYEE ; à faire évoluer si des rôles distincts
 * (ex: ROLE_ADMIN) sont nécessaires plus tard dans le fil rouge.
 */
@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Aucun compte pour l'email : " + email));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles("user")
                .build();
                // .authorities(List.of(new SimpleGrantedAuthority(user.getRole())))
    }
}