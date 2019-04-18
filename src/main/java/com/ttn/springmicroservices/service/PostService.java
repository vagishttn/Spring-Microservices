package com.ttn.springmicroservices.service;

import com.ttn.springmicroservices.entity.Post;
import com.ttn.springmicroservices.entity.Users;

public interface PostService {
    void save(Post post);

    Post addUserToPostAndSave(Post post, Users user);

    Post findById(Long id);
}
