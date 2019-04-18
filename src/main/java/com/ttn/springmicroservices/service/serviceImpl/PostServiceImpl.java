package com.ttn.springmicroservices.service.serviceImpl;

import com.ttn.springmicroservices.entity.Post;
import com.ttn.springmicroservices.entity.Users;
import com.ttn.springmicroservices.repository.PostRepository;
import com.ttn.springmicroservices.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

  @Autowired private PostRepository postRepository;

  @Override
  public void save(Post post) {
    postRepository.save(post);
  }

  @Override
  public Post addUserToPostAndSave(Post post, Users user) {
    post.setUsers(user);
    return postRepository.save(post);
  }

  @Override
  public Post findById(Long id) {
    Optional<Post> post = postRepository.findById(id);
    return post.orElse(null);
  }
}
