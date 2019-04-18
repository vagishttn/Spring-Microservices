package com.ttn.springmicroservices.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users extends AbstractEntity {

  @Size(min = 3, max = 15)
  @NotNull
  private String name;

  @Email @NotNull private String email;

  @NotNull private Integer age;

  private String emailResetToken;
}
