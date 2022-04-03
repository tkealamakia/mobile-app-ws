package com.tsunazumi.app.ws.ui.controller;


import com.tsunazumi.app.ws.ui.model.response.UserRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

  @GetMapping
  public String getUsers(@RequestParam(value="page", defaultValue="1") int page,
                         @RequestParam(value="limit", defaultValue="50") int limit,
                         @RequestParam(value="sort", required=false) String sort) {
    return "Get users was called with page: " + page + " and limit: " + limit + " and sort: " + sort;
  }

  @GetMapping(path="/{userId}",
      produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
  public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
    UserRest returnValue = new UserRest();
    returnValue.setEmail("ealderson@allsafe.com");
    returnValue.setFirstName("Elliot");
    returnValue.setLastName("Alderson");
    return new ResponseEntity<>(returnValue, HttpStatus.OK);
  }

  @PostMapping
  public String createUser() {
    return "create user was called";
  }

  @PutMapping
  public String updateUser() {
    return "update user was called";
  }

  @DeleteMapping
  public String deleteUser() {
    return "delete user was called";
  }

}