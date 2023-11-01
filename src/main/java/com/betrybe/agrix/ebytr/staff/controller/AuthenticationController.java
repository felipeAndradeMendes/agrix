package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.ebytr.staff.controller.dto.AuthenticationDto;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  private final AuthenticationManager authenticationManager;

  private final PersonService personService;

  @Autowired
  public AuthenticationController(AuthenticationManager authenticationManager,
      PersonService personService) {
    this.authenticationManager = authenticationManager;
    this.personService = personService;
  }


  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody AuthenticationDto authenticationDto) {

    UsernamePasswordAuthenticationToken usernamePassword =
        new UsernamePasswordAuthenticationToken(
            authenticationDto.username(),
            authenticationDto.password());

    Authentication auth = authenticationManager.authenticate(usernamePassword);

    String response = auth.getName();

    return ResponseEntity.ok(response);
  }
}
