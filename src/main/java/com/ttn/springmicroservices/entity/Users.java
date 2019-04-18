package com.ttn.springmicroservices.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users extends AbstractEntity {

  private String name;

  private String email;

  private Integer age;

  private String emailResetToken;
}
