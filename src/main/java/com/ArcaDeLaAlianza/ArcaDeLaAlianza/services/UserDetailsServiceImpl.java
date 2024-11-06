//package com.ArcaDeLaAlianza.ArcaDeLaAlianza.services;
//
//
//import com.ArcaDeLaAlianza.ArcaDeLaAlianza.Config.JwtUtil;
//import com.ArcaDeLaAlianza.ArcaDeLaAlianza.dto.UserDTO;
//import com.ArcaDeLaAlianza.ArcaDeLaAlianza.models.User;
//import com.ArcaDeLaAlianza.ArcaDeLaAlianza.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private UserRepository userRepository;
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.findByUsername(username)
//                .map(user -> new org.springframework.security.core.userdetails.User(
//                        user.getUsername(),
//                        user.getPassword(),
//                        Collections.singleton(new SimpleGrantedAuthority(("ROLE_" + user.getRole().name()))
//                        )))
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
//    }
//}
