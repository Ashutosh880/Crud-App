package org.spring.rest.crud.controller;

import org.spring.rest.crud.entity.User;
import org.spring.rest.crud.jwt.JwtTokenProvider;
import org.spring.rest.crud.service.AuthService;
import org.spring.rest.crud.service.CustomUserAuthService;
import org.spring.rest.crud.wrapper.GenericResponse;
import org.spring.rest.crud.wrapper.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private AuthService authService;

  @Autowired
  private CustomUserAuthService customUserAuthService;

  private final AuthenticationManager authenticationManager;

  @Autowired
  public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenUtil) {
    this.authenticationManager = authenticationManager;
    this.jwtTokenProvider = jwtTokenUtil;
  }

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @PostMapping("/signin")
  public ResponseEntity<LoginResponse> login(@RequestBody User loginRequest) {

    LoginResponse response = new LoginResponse();
    try {
      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
      final UserDetails userDetails = customUserAuthService.loadUserByUsername(loginRequest.getUsername());
      String jwtToken = jwtTokenProvider.generateToken(userDetails);
      response.setJwtToken(jwtToken);
      response.setMessage("Authenticated");
      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      System.out.println(e);
      response.setMessage("UnAuthorised");
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
  }

  @PostMapping("/signup")
  public ResponseEntity<?> register(@RequestBody User user) {
    User existingOrSavedUser = this.authService.register(user);

    if(existingOrSavedUser == null){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(GenericResponse.builder().description("User Already Registered")
          .statusCode(HttpStatus.BAD_REQUEST.value()).statusMsg(HttpStatus.BAD_REQUEST.getReasonPhrase()).build());
    }else{
      return ResponseEntity.ok().body(existingOrSavedUser);
    }
  }



}
