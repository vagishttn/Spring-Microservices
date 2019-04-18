package com.ttn.springmicroservices.controller;

import com.ttn.springmicroservices.entity.Post;
import com.ttn.springmicroservices.entity.Users;
import com.ttn.springmicroservices.exception.PostNotFoundException;
import com.ttn.springmicroservices.exception.UserNotFoundException;
import com.ttn.springmicroservices.service.PostService;
import com.ttn.springmicroservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/api")
public class PostController {

  @Autowired private PostService postService;
  @Autowired private UserService userService;

  @GetMapping("/users/{id}/posts")
  public List<Post> getAllPostOfUser(@PathVariable("id") Long id) {
    Users user = userService.findById(id);
    if (isNull(user)) throw new UserNotFoundException("User Not Found");
    return user.getPosts();
  }

  @PostMapping("/users/{id}/posts")
  public ResponseEntity<Object> savePost(
      @PathVariable("id") Long id, @Valid @RequestBody Post post) {
    Users user = userService.findById(id);
    if (isNull(user)) throw new UserNotFoundException("User Not Found");

    Post savedPost = postService.addUserToPostAndSave(post, user);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedPost.get_id())
            .toUri();
    return ResponseEntity.created(location).build();
  }

  @GetMapping("/posts/{id}")
  public Post getPostOfUser(@PathVariable("id") Long id) {
    Post post = postService.findById(id);
    if (isNull(post)) throw new PostNotFoundException("Post Not Fund");
    Link selfLink = ControllerLinkBuilder.linkTo(PostController.class).slash(id).withSelfRel();
    post.getLinks().clear();
    post.getLinks().add(selfLink);
    return post;
  }
}
