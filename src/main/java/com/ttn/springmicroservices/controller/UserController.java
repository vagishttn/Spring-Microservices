package com.ttn.springmicroservices.controller;

import com.ttn.springmicroservices.entity.Users;
import com.ttn.springmicroservices.exception.UserNotFoundException;
import com.ttn.springmicroservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/api")
public class UserController {

  @Autowired private UserService userService;

  @GetMapping("/users")
  private Iterable<Users> getAllUsers() {
    return userService.getAllUsers();
  }

  @PostMapping("/users")
  public ResponseEntity<Object> createUser(@RequestBody Users user) {
    Users savedUser = userService.save(user);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedUser.getId())
            .toUri();

    return ResponseEntity.created(location).build();
  }

  @GetMapping("/users/{id}")
  public Users getUser(@PathVariable("id") Long id) throws UserNotFoundException {
    Users user = userService.findById(id);
    if (isNull(user)) throw new UserNotFoundException("User Not found with id = " + id);
    return user;
  }

  @DeleteMapping("/users/{id}")
  public Users deleteUser(@PathVariable("id") Long id) {
    Users users = userService.findById(id);
    if (isNull(users)) throw new UserNotFoundException("User not found with id = " + id);
    return users;
  }
}
