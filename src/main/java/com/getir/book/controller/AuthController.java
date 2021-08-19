package com.getir.book.controller;


import com.getir.book.model.LoginRequest;
import com.getir.book.util.security.JwtTokenProvider;
import com.getir.book.util.security.UserAuthenticationProvider;
import io.jsonwebtoken.lang.Assert;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserAuthenticationProvider userAuthenticationProvider;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        Assert.notNull(loginRequest, "Login Request can't be null");
        userAuthenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        String jwt = jwtTokenProvider.generateUserToken("user1token");
        return ResponseEntity.ok(jwt);
    }




}
