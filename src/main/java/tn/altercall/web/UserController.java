package tn.altercall.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.altercall.entities.User;
import tn.altercall.payload.request.LoginRequest;
import tn.altercall.payload.request.SignupRequest;
import tn.altercall.payload.response.MessageResponse;
import tn.altercall.services.UserImpl;

import javax.validation.Valid;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class UserController {


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

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User userDetail, @PathVariable("id") Long id){
         User _user = userService.updateUser(userDetail, id);
        return new ResponseEntity<>(_user, HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") Long id){
     var response =    userService.deleteUser(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
