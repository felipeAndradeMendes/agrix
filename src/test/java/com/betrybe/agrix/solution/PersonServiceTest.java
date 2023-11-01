package com.betrybe.agrix.solution;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.exception.PersonNotFoundException;
import com.betrybe.agrix.ebytr.staff.repository.PersonRepository;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class PersonServiceTest {

  @Autowired
  PersonService personService;

  @MockBean
  PersonRepository personRepository;

  @Test
  void testPersonCreation() {
    Person personToSave = new Person();
    personToSave.setUsername("Felipe");
    personToSave.setPassword("SenhaSecreta");

    Person personToReturn = new Person();
    personToReturn.setId(1L);
    personToReturn.setUsername("Felipe");
    personToReturn.setPassword("SenhaSecreta");

    Mockito.when(personRepository.save(any(Person.class)))
        .thenReturn(personToReturn);

    Person savedPerson = personService.create(personToSave);

    Mockito.verify(personRepository).save(any(Person.class));

    assertEquals(savedPerson.getId(), personToReturn.getId());
    assertEquals(savedPerson.getUsername(), personToReturn.getUsername());
    assertEquals(savedPerson.getPassword(), personToReturn.getPassword());
  }

  @Test
  void testGetPersonById() {
    Person personToGet = new Person();
    personToGet.setId(42L);
    personToGet.setUsername("Felipe");
    personToGet.setPassword("SenhaSecreta");

    Mockito.when(personRepository.findById(eq(42L)))
        .thenReturn(Optional.of(personToGet));

    Person returnedPerson = personService.getPersonById(42L);

    Mockito.verify(personRepository).findById(eq(42L));
    assertEquals(personToGet.getId(), returnedPerson.getId());
    assertEquals(personToGet.getUsername(), returnedPerson.getUsername());
    assertEquals(personToGet.getPassword(), returnedPerson.getPassword());
  }

  @Test
  void testGetPersonByIdNotFoundException() {
    Mockito.when(personRepository.findById(any()))
        .thenReturn(Optional.empty());

    assertThrows(PersonNotFoundException.class, () -> personService.getPersonById(999L));

    Mockito.verify(personRepository).findById(eq(999L));
  }

  @Test
  void testGetPersonByUsername() {
    Person personToGet = new Person();
    personToGet.setId(42L);
    personToGet.setUsername("Felipe");
    personToGet.setPassword("SenhaSecreta");

    Mockito.when(personRepository.findByUsername(eq("Felipe")))
        .thenReturn(Optional.of(personToGet));

    Person returnedPerson = personService.getPersonByUsername("Felipe");

    Mockito.verify(personRepository).findByUsername(eq("Felipe"));

    assertEquals(returnedPerson.getId(), 42L);
    assertEquals(returnedPerson.getUsername(), personToGet.getUsername());
    assertEquals(returnedPerson.getPassword(), personToGet.getPassword());
  }

  @Test
  void testGetPersonByUsernameNotFoundException() {
    Mockito.when(personRepository.findByUsername(any()))
        .thenReturn(Optional.empty());

    assertThrows(PersonNotFoundException.class, () -> personService.getPersonByUsername("Lucréscio"));

    Mockito.verify(personRepository).findByUsername(eq("Lucréscio"));
  }

}
