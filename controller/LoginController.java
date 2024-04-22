// LoginController.java
package MrcProject6.MrcProject6.controller;

import MrcProject6.MrcProject6.model.UserInfo;
import MrcProject6.MrcProject6.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class LoginController {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    @Autowired
    public LoginController(UserRepository userRepository, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody UserInfo user) {
        // Retrieve user information from the database based on the provided user ID
        UserInfo storedUser = userRepository.findByUserId(user.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with user ID: " + user.getUserId()));

        // Compare the provided password with the stored password
        if (!storedUser.getUserPassword().equals(user.getUserPassword())) {
            // If passwords don't match, return unauthorized response
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
        }

        // Generate JWT token
        String token = jwtUtils.generateToken(storedUser.getUserNick());

        // Return the token and user nickname in the response
        return ResponseEntity.ok(new LoginResponse(token, storedUser.getUserNick()));
    }
}