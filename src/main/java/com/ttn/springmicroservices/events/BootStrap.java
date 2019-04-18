package com.ttn.springmicroservices.events;

import com.ttn.springmicroservices.entity.Users;
import com.ttn.springmicroservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
public class BootStrap {

  @Autowired private UserService userService;

  @EventListener(ContextRefreshedEvent.class)
  public void initData() {
    IntStream.rangeClosed(1, 10)
        .forEach(
            e -> {
              Users users = new Users();
              users.setName("Name" + e);
              users.setAge(20 + e);
              users.setEmail("email" + e + "@gmail.com");
              users.setEmailResetToken(null);
              users.setActivated(true);
              users.setDeleted(false);
              userService.save(users);
            });
  }
}
