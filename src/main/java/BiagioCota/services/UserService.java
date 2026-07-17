package BiagioCota.services;

import BiagioCota.entities.User;
import BiagioCota.exceptions.UserEsisteGiaException;
import BiagioCota.payloads.auth.*;
import BiagioCota.repositories.UserRepository;
import BiagioCota.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public List<UserResponse> finAll() {
        return this.userRepository.findAll().stream().map(user -> UserResponse.from(user)).toList();
    }

    public UserResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UserEsisteGiaException("Username già in uso: " + request.getUsername());
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserEsisteGiaException("Email già in uso: " + request.getEmail());
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setRole(request.getRole());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return UserResponse.from(userRepository.save(user));
    }


    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        return new LoginResponse(jwtUtil.generateToken(request.getUsername()));
    }
}
