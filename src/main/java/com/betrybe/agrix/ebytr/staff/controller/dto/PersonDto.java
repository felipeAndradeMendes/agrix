package com.betrybe.agrix.ebytr.staff.controller.dto;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.security.Role;

/**
 * The type Person dto.
 */
public record PersonDto(Long id, String username, Role role) {

  /**
   * To person person.
   *
   * @return the person
   */
  public Person toPerson() {
    return new Person(id, username, role);
  }
}
