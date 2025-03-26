package com.sa.global.service;

import com.sa.global.dto.UserLoginDto;
import com.sa.global.dto.UserRegisterDto;
import com.sa.global.entity.User;
import com.sa.global.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class GlobalUserService {
    @Autowired
    private UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User findUserByEmail(UserLoginDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail());
        if (user == null || !passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }

        return user;
    }

    public void registerUser(UserRegisterDto userDto) {
        User userByEmail = userRepository.findByEmail(userDto.getEmail());
        User userByPhone = userRepository.findByPhone(userDto.getPhone());
        if (userByEmail != null || userByPhone !=null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        }
        User newUser = new User();
        newUser.setRegion(userDto.getRegion());
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setPhone(userDto.getPhone());

        userRepository.save(newUser);
    }
}
