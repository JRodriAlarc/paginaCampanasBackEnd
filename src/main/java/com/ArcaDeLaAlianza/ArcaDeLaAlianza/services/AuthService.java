package com.ArcaDeLaAlianza.ArcaDeLaAlianza.services;


import com.ArcaDeLaAlianza.ArcaDeLaAlianza.Config.JwtUtil;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.dto.AuthDTO;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.dto.UserDTO;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.enums.Role;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.models.User;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    public String authenticate(AuthDTO authDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getPassword()));
        return jwtUtil.generateToken(authDTO.getUsername());
    }

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return jwtUtil.generateToken(username);
        }
        return null;
    }


    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public User register(UserDTO userDTO) {
       User user = new User();
       String passwordEncoded = passwordEncoder.encode(userDTO.getPassword());


         user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            user.setUsername(userDTO.getUsername());
            user.setPassword(passwordEncoded);
            user.setPhone(userDTO.getPhone());
            user.setRole(Role.CUSTOMER);

            return userRepository.save(user);

    }

}
