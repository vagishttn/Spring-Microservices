package com.ttn.springmicroservices.controller;

import com.ttn.springmicroservices.version.Name;
import com.ttn.springmicroservices.version.Person;
import com.ttn.springmicroservices.version.PersonV2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

  @RequestMapping("/v1")
  public Person getPerson() {
    return new Person("Vagish Dixit");
  }

  @RequestMapping("/v2")
  public PersonV2 getPersonV2() {
    return new PersonV2(new Name("Vagish", "Dixit"));
  }

  @RequestMapping(value = "/v1/param", params = "version=1")
  public Person getPersonParam() {
    return new Person("Vagish Dixit");
  }

  @RequestMapping(value = "/v2/param", params = "version=2")
  public PersonV2 getPersonV2Param() {
    return new PersonV2(new Name("Vagish", "Dixit"));
  }

  @RequestMapping(value = "/v1/header", headers = "API_VERSION=1")
  public Person getPersonHeader() {
    return new Person("Vagish Dixit");
  }

  @RequestMapping(value = "/v2/header", headers = "API_VERSION=2")
  public PersonV2 getPersonV2Header() {
    return new PersonV2(new Name("Vagish", "Dixit"));
  }

  @RequestMapping(value = "/v1/produces", produces = "application/v1+json")
  public Person getPersonProduces() {
    return new Person("Vagish Dixit");
  }

  @RequestMapping(value = "/v2/produces", produces = "application/v2+json")
  public PersonV2 getPersonV2Produces() {
    return new PersonV2(new Name("Vagish", "Dixit"));
  }
}
