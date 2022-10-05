package tn.altercall.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonatype.plexus.components.sec.dispatcher.PasswordDecryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.altercall.entities.User;
import tn.altercall.payload.request.LoginRequest;
import tn.altercall.payload.request.SignupRequest;
import tn.altercall.payload.response.MessageResponse;
import tn.altercall.services.UserImpl;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class UserController {



    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserImpl userService;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        var jwtResponse = userService.authenticateUser(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

        var registerMessage = userService.registerUser(signUpRequest);
        return ResponseEntity.ok(new MessageResponse(registerMessage));
    }

    // get all users
    @GetMapping("/users/")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    // get user by id
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id)  {

        var response = userService.getUserById(id);

        //LOGGER.info("The password decrypted is {}");
        response.setPassword("");


        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User userDetail, @PathVariable("id") Long id) {
        User _user = userService.updateUser(userDetail, id);
        return new ResponseEntity<>(_user, HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") Long id) {
        var response = userService.deleteUser(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteAllUser/")
    public ResponseEntity<?> deleteAllUser() {
        var response = userService.deleteAllUser();
        List<Boolean> result = response.values().stream().collect(Collectors.toList());
        if (result.get(0) == Boolean.FALSE) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);

    }



}
