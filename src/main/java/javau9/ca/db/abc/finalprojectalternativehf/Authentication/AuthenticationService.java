package javau9.ca.db.abc.finalprojectalternativehf.Authentication;

import javau9.ca.db.abc.finalprojectalternativehf.Models.Role;
import javau9.ca.db.abc.finalprojectalternativehf.Models.SimpleUser;
import javau9.ca.db.abc.finalprojectalternativehf.Service.JwtService;
import javau9.ca.db.abc.finalprojectalternativehf.Service.SimpleUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final SimpleUserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        SimpleUser user = SimpleUser.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userService.save(user);
        String jwtToken = jwtService.generateToken((UserDetails) user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        SimpleUser user = userService.findbyEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + request.getEmail()));
        String jwtToken = jwtService.generateToken((UserDetails) user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

}
