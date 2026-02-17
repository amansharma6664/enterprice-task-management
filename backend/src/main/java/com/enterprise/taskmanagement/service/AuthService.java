package com.enterprise.taskmanagement.service;
 
import com.enterprise.taskmanagement.entity.User;
import com.enterprise.taskmanagement.exception.BadRequestException;
import com.enterprise.taskmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public User registerUser(String username, String email, String password, String firstName, String lastName) {
        if (userRepository.existsByUsername(username)) {
            throw new BadRequestException("Username already exists");
        }
        
        if (userRepository.existsByEmail(email)) {
            throw new BadRequestException("Email already exists");
        }
        
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(User.Role.USER);
        user.setActive(true);
        
        return userRepository.save(user);
    }
}
