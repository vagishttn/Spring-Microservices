package com.ttn.springmicroservices.events;

import com.ttn.springmicroservices.entity.Post;
import com.ttn.springmicroservices.entity.Users;
import com.ttn.springmicroservices.service.PostService;
import com.ttn.springmicroservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.stream.IntStream;

@Component
public class BootStrap {

  @Autowired private UserService userService;
  @Autowired private PostService postService;

  @EventListener(ContextRefreshedEvent.class)
  public void initData() {

    IntStream.rangeClosed(1, 10)
        .forEach(
            e -> {
              Users users = new Users();
              Post post = new Post();
              Post post1 = new Post();
              post.setDescription("Description" + new Random().nextInt(3));
              post1.setDescription("Description" + new Random().nextInt(5));
              postService.save(post);
              postService.save(post1);
              users.setName("Name" + e);
              users.setAge(20 + e);
              users.setEmail("email" + e + "@gmail.com");
              users.setEmailResetToken(null);
              users.setActivated(true);
              users.setDeleted(false);
              users.getPosts().add(post);
              users.getPosts().add(post1);
              userService.save(users);
              post.setUsers(users);
              post1.setUsers(users);
              postService.save(post);
              postService.save(post1);
            });
  }
}
