package com.betrybe.agrix.ebytr.staff.controller.dto;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.security.Role;

public record PersonDto(Long id, String username, Role role) {

  public Person toPerson() {
    return new Person(id, username, role);
  }
}
