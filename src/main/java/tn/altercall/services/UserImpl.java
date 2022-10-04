package tn.altercall.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.altercall.entities.ERole;
import tn.altercall.entities.Role;
import tn.altercall.entities.User;
import tn.altercall.exception.ResourceNotFoundException;
import tn.altercall.payload.request.LoginRequest;
import tn.altercall.payload.request.SignupRequest;
import tn.altercall.payload.response.JwtResponse;
import tn.altercall.repository.RoleRepository;
import tn.altercall.repository.UserRepository;
import tn.altercall.security.jwt.JwtUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserImpl implements UserService {


    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    public UserImpl(){}

    @Override
    public String registerUser(SignupRequest signUpRequest) {
        var message = "";
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            message = "Error: Email is already in use!";
            return message;
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        String strEmail = signUpRequest.getEmail();
        Set<Role> roles = new HashSet<>();

        if (strEmail.contains("@productowner.tn")) {
            Role userRole = roleRepository.findByName(ERole.ROLE_PRODUCTOWNER)
                    .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
            roles.add(userRole);
        }
        if (strEmail.contains("@scrumm.tn")) {
            Role userRole = roleRepository.findByName(ERole.ROLE_SCRUMMASTER)
                    .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
            roles.add(userRole);
        }
        if (strEmail.contains("@developer.tn")) {
            Role userRole = roleRepository.findByName(ERole.ROLE_DEVELOPER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }

        user.setRoles(roles);
        userRepository.save(user);
        return message = "User registered successfully!";
    }

    @Override
    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles);
    }

    @Override
    public User updateUser(User user, Long id) {

        var userUpdated = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id = " + id));


        userUpdated.setUsername(user.getUsername());
        //userUpdated.setEmail(user.getEmail());
        userUpdated.setPassword(encoder.encode(user.getPassword()));
        userUpdated = userRepository.saveAndFlush(userUpdated);

        return userUpdated;
    }

    @Override
    public Map<String, Boolean> deleteUser(Long id) {
        var userDel = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id = " + id));
        this.userRepository.delete(userDel);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return response;
    }
}
