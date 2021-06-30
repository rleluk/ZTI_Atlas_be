package pl.zti.atlas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.zti.atlas.model.Credentials;
import pl.zti.atlas.model.Token;
import pl.zti.atlas.model.User;
import pl.zti.atlas.model.UserRole;
import pl.zti.atlas.security.SecretKeyGenerator;
import pl.zti.atlas.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

import java.util.Optional;


@RestController
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Token> authenticate(@RequestBody Credentials credentials) {
        Optional<User> userData = userService.findByEmail(credentials.getEmail());

        if (userData.isPresent()) {
            User user = userData.get();

            if (passwordEncoder.matches(credentials.getPassword(), user.getPassword())) {
                String token = Jwts.builder()
                    .setSubject(user.getEmail())
                    .claim("email", user.getEmail())
                    .claim("role", user.getRole())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                    .signWith(SignatureAlgorithm.HS256, SecretKeyGenerator.getSecretKeyBytes())
                    .compact();

                return new ResponseEntity<>(new Token(token), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody Credentials credentials) {
        Optional<User> userData = userService.findByEmail(credentials.getEmail());

        if (userData.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            User user = userService.insert(new User(credentials.getEmail(), credentials.getPassword(), UserRole.ADMIN));
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }
}
