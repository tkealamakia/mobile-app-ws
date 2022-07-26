package com.tsunazumi.app.ws.ui.controller;


import com.tsunazumi.app.ws.exception.UserServiceException;
import com.tsunazumi.app.ws.ui.model.request.UpdateUserDetailsRequestModel;
import com.tsunazumi.app.ws.ui.model.request.UserDetailsRequestModel;
import com.tsunazumi.app.ws.ui.model.response.UserRest;
import com.tsunazumi.app.ws.userservice.UserService;
import com.tsunazumi.app.ws.userservice.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("users")
public class UserController {
  Map<String, UserRest> users;

  @Autowired
  UserService userService;

  @GetMapping
  public String getUsers(@RequestParam(value="page", defaultValue="1") int page,
                         @RequestParam(value="limit", defaultValue="50") int limit,
                         @RequestParam(value="sort", required=false) String sort) {
    return "Get users was called with page: " + page + " and limit: " + limit + " and sort: " + sort;
  }

  @GetMapping(path="/{userId}",
      produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
  public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
    UserRest userRest = userService.getUser(userId);
    if (userRest != null) {
      return new ResponseEntity<>(userRest, HttpStatus.OK);
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
    UserRest returnValue = userService.createUser(userDetails);
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