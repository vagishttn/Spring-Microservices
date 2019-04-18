package com.ttn.springmicroservices.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post extends AbstractEntity {

  @Size(min = 3, max = 255)
  @NotNull
  private String description;

  @ManyToOne(fetch = FetchType.EAGER)
  @JsonIgnore
  private Users users;

  public Post(String description) {
    this.description = description;
  }
}
