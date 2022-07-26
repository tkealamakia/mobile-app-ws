package com.tsunazumi.app.ws.userservice;

import com.tsunazumi.app.ws.ui.model.request.UserDetailsRequestModel;
import com.tsunazumi.app.ws.ui.model.response.UserRest;

public interface UserService {
  UserRest createUser(UserDetailsRequestModel userDetails);
  UserRest getUser(String userId);
}
