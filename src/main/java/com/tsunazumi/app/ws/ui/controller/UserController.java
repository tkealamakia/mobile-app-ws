package com.tsunazumi.app.ws.ui.controller;


import com.tsunazumi.app.ws.ui.model.request.UpdateUserDetailsRequestModel;
import com.tsunazumi.app.ws.ui.model.request.UserDetailsRequestModel;
import com.tsunazumi.app.ws.ui.model.response.UserRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController {
  Map<String, UserRest> users;

  @GetMapping
  public String getUsers(@RequestParam(value="page", defaultValue="1") int page,
                         @RequestParam(value="limit", defaultValue="50") int limit,
                         @RequestParam(value="sort", required=false) String sort) {
    return "Get users was called with page: " + page + " and limit: " + limit + " and sort: " + sort;
  }

  @GetMapping(path="/{userId}",
      produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
  public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
    String firstName = null;
    int fistNameLength = firstName.length();
    if (users.containsKey(userId)) {
      return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
  }

  @PostMapping(
      consumes = {
          MediaType.APPLICATION_XML_VALUE,
          MediaType.APPLICATION_JSON_VALUE
      },
      produces = {
          MediaType.APPLICATION_XML_VALUE,
          MediaType.APPLICATION_JSON_VALUE
      }
  )
  public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {
    UserRest returnValue = new UserRest();
    returnValue.setEmail(userDetails.getEmail());
    returnValue.setFirstName(userDetails.getFirstName());
    returnValue.setLastName(userDetails.getLastName());

    String userId = UUID.randomUUID().toString();
    returnValue.setUserId(userId);
    if (users == null) {
      users = new HashMap<>();
      users.put(userId, returnValue);
    }
    System.out.println(userId);

    return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
  }

  @PutMapping(path="/{userId}",
          consumes = {
                  MediaType.APPLICATION_XML_VALUE,
                  MediaType.APPLICATION_JSON_VALUE
          },
          produces = {
                  MediaType.APPLICATION_XML_VALUE,
                  MediaType.APPLICATION_JSON_VALUE
          }
  )
  public UserRest updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserDetailsRequestModel userDetails) {
    UserRest storedUserDetails = users.get(userId);
    storedUserDetails.setFirstName(userDetails.getFirstName());
    storedUserDetails.setLastName(userDetails.getLastName());
    users.put(userId, storedUserDetails);
    return storedUserDetails;
  }

  @DeleteMapping(path="/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable String id) {
    users.remove(id);
    return ResponseEntity.noContent().build();

  }

}