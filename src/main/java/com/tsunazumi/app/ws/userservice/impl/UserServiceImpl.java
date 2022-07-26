package com.tsunazumi.app.ws.userservice.impl;

import com.tsunazumi.app.ws.shared.Utils;
import com.tsunazumi.app.ws.ui.model.request.UserDetailsRequestModel;
import com.tsunazumi.app.ws.ui.model.response.UserRest;
import com.tsunazumi.app.ws.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
  Map<String, UserRest> users;
  Utils utils;

  public UserServiceImpl() {}

  @Autowired
  public UserServiceImpl(Utils utils) {
    this.utils = utils;
  }

  @Override
  public UserRest createUser(UserDetailsRequestModel userDetails) {
    UserRest returnValue = new UserRest();
    returnValue.setEmail(userDetails.getEmail());
    returnValue.setFirstName(userDetails.getFirstName());
    returnValue.setLastName(userDetails.getLastName());

    String userId = utils.generateUserId();
//    String userId = "1";
    returnValue.setUserId(userId);
    if (users == null) {
      users = new HashMap<>();
      users.put(userId, returnValue);
    }
    System.out.println(userId);
    return returnValue;
  }

  @Override
  public UserRest getUser(String userId) {
    return users.get(userId);
  }
}
