package javau9.ca.db.abc.finalprojectalternativehf.Authentication;

import javau9.ca.db.abc.finalprojectalternativehf.DTO.LoginUserDto;
import javau9.ca.db.abc.finalprojectalternativehf.DTO.RegisterUserDto;
import javau9.ca.db.abc.finalprojectalternativehf.Models.SimpleUser;
import javau9.ca.db.abc.finalprojectalternativehf.Responses.LoginResponse;
import javau9.ca.db.abc.finalprojectalternativehf.Service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<SimpleUser> register(@RequestBody RegisterUserDto registerUserDto) {
        SimpleUser registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        SimpleUser authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse().setToken(jwToken).setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}
