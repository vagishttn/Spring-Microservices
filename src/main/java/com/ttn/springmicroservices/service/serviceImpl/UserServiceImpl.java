package com.ttn.springmicroservices.service.serviceImpl;

import com.ttn.springmicroservices.entity.Users;
import com.ttn.springmicroservices.repository.UserRepository;
import com.ttn.springmicroservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  @Autowired private UserRepository userRepository;

  @Override
  public Iterable<Users> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public Users save(Users users) {
    userRepository.save(users);
    return users;
  }

  @Override
  public Users findById(Long id) {
    Optional<Users> users = userRepository.findById(id);
    return users.orElse(null);
  }

  @Override
  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }
}
