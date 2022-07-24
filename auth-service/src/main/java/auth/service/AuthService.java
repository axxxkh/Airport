package auth.service;

import auth.dto.AuthResponse;
import auth.dto.LoginRequest;
import auth.dto.RegisterRequest;
import auth.exceptions.UserAuthException;
import auth.exceptions.UserRegisterException;

import javax.validation.Valid;

public interface AuthService {

    public AuthResponse login(@Valid LoginRequest loginRequest) throws UserAuthException;

    public AuthResponse registerUser(@Valid RegisterRequest request) throws UserRegisterException;
}
