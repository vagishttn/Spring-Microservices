package com.ttn.springmicroservices.service;

import com.ttn.springmicroservices.entity.Users;

public interface UserService {
  Iterable<Users> getAllUsers();

    Users save(Users users);

  Users findById(Long id);

  void deleteUser(Long id);
}
