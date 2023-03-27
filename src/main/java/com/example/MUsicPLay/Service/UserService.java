package com.example.MUsicPLay.Service;

import com.example.MUsicPLay.Model.User;
import com.example.MUsicPLay.Repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Validator validator;
    public String authenticate(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user != null && BCrypt.checkpw(password, user.get().getPassword())) {
            String token = Jwts.builder()
                    .setSubject(user.get().getUser_id().toString())
                    .signWith(SignatureAlgorithm.HS256, "secret")
                    .compact();
            return token;
        } else {
            throw new RuntimeException("Invalid email or password");
        }
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public void updateName(User user) {
        Optional<User> optionalUser = userRepository.findById(user.getUser_id());
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setName(user.getName());
            existingUser.setImage(user.getImage());
            userRepository.save(existingUser);
        }
    }
    public void updateEmail(User user) {
        Optional<User> optionalUser = userRepository.findById(user.getUser_id());
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setEmail(user.getEmail());
            userRepository.save(existingUser);
        }
    }
    public void updateFill(User user) {
        Optional<User> optionalUser = userRepository.findById(user.getUser_id());
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setFill(user.getFill());
            userRepository.save(existingUser);
        }
    }
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public User createUser(User user) throws DuplicateKeyException{
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new DuplicateKeyException("Email already exists");
        }
        //user.setPassword(user.getPassword());
        return userRepository.save(user);
    }
    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getUser_id()).orElse(null);
        if (existingUser == null) {
            return null;
        }
        return userRepository.save(user);
    }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    public void updatePassword(Long userId, String newPassword) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            String encryptedPassword = new BCryptPasswordEncoder().encode(newPassword);
            user.get().setPassword(encryptedPassword);
            userRepository.save(user.get());
        }
    }
}
