package org.spring.rest.crud.service;

import org.spring.rest.crud.entity.Authorities;
import org.spring.rest.crud.entity.User;
import org.spring.rest.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public User register(User user) {

    if (userRepository.findByUsername(user.getUsername()).orElse(null) == null) {
      List<Authorities> authorities = user.getAuthorities();
      authorities.forEach(authority -> authority.setUser(user));
      List<Authorities> peeked = authorities.stream().peek(authority -> authority.setUsername(user.getUsername()))
          .toList();
      user.setAuthorities(peeked);
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      return userRepository.save(user);
    } else {
      System.out.println("else");
      return null;
    }
  }

}
