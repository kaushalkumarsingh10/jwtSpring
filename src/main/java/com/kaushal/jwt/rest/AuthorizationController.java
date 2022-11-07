package com.kaushal.jwt.rest;

import com.kaushal.jwt.dao.Request;
import com.kaushal.jwt.dao.Response;
import com.kaushal.jwt.impl.service.MyUserDetailsService;
import com.kaushal.jwt.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@org.springframework.web.bind.annotation.RestController
public class AuthorizationController {
    Logger logger = LoggerFactory.getLogger(AuthorizationController.class);
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/hello")
    public String test() {
        return "Hello";
    }

    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody Request request) throws Exception {
        logger.debug("-- auth() request :{}",request);
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            logger.info("User Authenticated");
        }
        catch (BadCredentialsException e) {
            logger.error("Incorrect username or password");
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        logger.debug("jwt  :{}",jwt);
        return ResponseEntity.ok(new Response(jwt));
    }
}
