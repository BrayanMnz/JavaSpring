package com.brayanmnz.market.web.controller;

import com.brayanmnz.market.domain.dto.AuthenticationRequest;
import com.brayanmnz.market.domain.dto.AuthenticationResponse;
import com.brayanmnz.market.domain.service.UserDetailService;
import com.brayanmnz.market.web.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private JWTUtil jwt;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticationRequest authRequest){
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPasswd()));
            UserDetails user = userDetailService.loadUserByUsername(authRequest.getUsername());
            String token = jwt.generateToken(user);

            return new ResponseEntity<>(new AuthenticationResponse(token), HttpStatus.OK);

        }catch (BadCredentialsException ex){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }
}
