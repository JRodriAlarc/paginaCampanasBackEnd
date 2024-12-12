package com.ArcaDeLaAlianza.ArcaDeLaAlianza.services;

import com.ArcaDeLaAlianza.ArcaDeLaAlianza.Config.JwtUtil;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.dto.AuthDTO;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.dto.UserDTO;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.dto.UserInfoDTO;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.enums.Role;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.models.User;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public AuthService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }



    public String login(AuthDTO authDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getPassword())
            );

            return jwtUtil.generateToken(authDTO.getUsername());
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales incorrectas.");



        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales incorrectas.");
        }

    }


    public User register(UserDTO userDTO) {
       User user = new User();
       String passwordEncoded = passwordEncoder.encode(userDTO.getPassword());

        Optional<User> existingUser = userRepository.findByUsername(userDTO.getUsername());

        if (existingUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Ya existe este usuario " );
        }

         user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            user.setUsername(userDTO.getUsername());
            user.setPassword(passwordEncoded);
            user.setPhone(userDTO.getPhone());
            user.setRole(Role.CUSTOMER);

            return userRepository.save(user);

    }

    public UserInfoDTO getUser(String username, String token) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));


        UserInfoDTO userInfo = new UserInfoDTO();
        userInfo.setName(user.getName());
        userInfo.setEmail(user.getEmail());
        userInfo.setUsername(user.getUsername());
        userInfo.setPhone(user.getPhone());
        userInfo.setRole(user.getRole());
        userInfo.setToken(token);

        return userInfo;
    }
}
