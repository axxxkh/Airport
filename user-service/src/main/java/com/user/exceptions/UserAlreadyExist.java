package com.user.exceptions;

public class UserAlreadyExist extends Exception {
    public UserAlreadyExist() {
        super();
    }

    public UserAlreadyExist(String message) {
        super(message);
    }
}
