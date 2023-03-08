package com.example.MUsicPLay.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.MUsicPLay.Configure.JwtUtil;
import com.example.MUsicPLay.Configure.LoginRequest;
import com.example.MUsicPLay.Configure.LoginResponse;
import com.example.MUsicPLay.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.MUsicPLay.Model.User;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.*;

import static com.example.MUsicPLay.Configure.JwtUtil.SECRET;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil JwtProperties;
    @PostMapping("/users/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Optional<User> user = userService.findByEmail(loginRequest.getEmail());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!user.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email, please check the email!");
        }
        if(passwordEncoder.matches(loginRequest.getPassword(), user.get().getPassword())) {
            String token = JWT.create()
                    .withSubject(user.get().getEmail())
                    .withClaim("userId", user.get().getUser_id())
                    .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                    .sign(Algorithm.HMAC512(SECRET.getBytes()));
            return ResponseEntity.ok(new LoginResponse(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password, please check the password!");
        }
    }
    @GetMapping("/users/token")
    public Long getUserIdFromToken(@RequestParam("token") String token) {
        Long userId = JwtProperties.getUserIdFromToken(token);
        return userId;
    }
    @PostMapping("/users/add")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult result)  {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.createUser(user);
        return ResponseEntity.ok().build();
    }
    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleDuplicateKeyException(DuplicateKeyException ex) {
        return "Email already exists. If you have an account, you can look for it by email.";
    }
    @GetMapping("/users/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/users/get/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    @PutMapping("/users/update")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }
    @DeleteMapping("/users/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
