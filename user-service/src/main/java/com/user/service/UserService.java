package com.user.service;

import com.user.entity.User;
import com.user.exceptions.UserAlreadyExist;
import com.user.exceptions.UserNotFound;

public interface UserService {

    User getByEmail(String email) throws UserNotFound;

    User saveUser(User user) throws UserAlreadyExist;
}