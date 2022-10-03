package tn.altercall.services;

import tn.altercall.entities.User;
import tn.altercall.payload.response.JwtResponse;

public interface UserService {

    public String userSignUp(User user);
    public JwtResponse userSignIn(User user);
    public User updateUser(User user);
    public void deleteUser(Long id);
}
