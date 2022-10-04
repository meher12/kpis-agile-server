package tn.altercall.services;

import tn.altercall.entities.User;
import tn.altercall.payload.request.LoginRequest;
import tn.altercall.payload.request.SignupRequest;
import tn.altercall.payload.response.JwtResponse;

import java.util.Map;

public interface UserService {

    String registerUser(SignupRequest signUpRequest);

    JwtResponse authenticateUser(LoginRequest loginRequest);

    User updateUser(User user, Long id);

    Map<String, Boolean> deleteUser(Long id);
}
