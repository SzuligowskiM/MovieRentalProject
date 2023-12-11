package com.example.demo.auth;

import com.example.demo.config.JwtService;
import com.example.demo.models.Role;
import com.example.demo.models.UserCredential;
import com.example.demo.models.UserMain;
import com.example.demo.repositories.UserCredentialRepository;
import com.example.demo.repositories.UserMainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
//    https://www.youtube.com/watch?v=KxqlJblhzfI&ab_channel=Amigoscode 1:49:10
    private final UserMainRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserCredentialRepository userCredentialRepository;
    public AuthenticationResponse register(RegisterRequest request) {

        var user = new UserMain();
        var credential = new UserCredential(null,request.getEmail(), passwordEncoder.encode(request.getPassword()));
        userCredentialRepository.save(credential);

        user.setName(request.getFirstname());
        user.setSurname(request.getLastname());
        user.setRole("USER");
        user.setUserCredential(credential);
        user.setBalance(0.0);
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = repository.findByUserCredentialEmail(request.getEmail()).orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
