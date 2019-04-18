package com.ttn.springmicroservices.repository;

import com.ttn.springmicroservices.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {}
