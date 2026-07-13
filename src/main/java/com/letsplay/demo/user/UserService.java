package com.letsplay.demo.user;

import org.springframework.stereotype.Service;

import com.letsplay.demo.exception.custom.BadRequestException;
import com.letsplay.demo.exception.custom.ConflictException;
import com.letsplay.demo.exception.custom.NotFoundException;
import com.letsplay.demo.user.DTO.CreateRequest;
import com.letsplay.demo.user.DTO.EditRequest;
import com.letsplay.demo.user.DTO.UpdateRequest;
import com.letsplay.demo.user.DTO.UserResponse;

import java.util.List;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final UserMapper userMapper;

    public UserResponse createUser(CreateRequest req) {
        validEmail(req.email());

        User user = new User();
        user.setName(req.name());
        user.setEmail(req.email());
        user.setPassword(encoder.encode(req.password()));
        user.setRole(req.role());
        return userMapper.toResponse(userRepository.save(user));
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }

    public UserResponse getUserById(String id) {
        return userMapper.toResponse(retrieveUser(id));
    }

    public UserResponse updateUserById(String id, UpdateRequest req) {
        User user = retrieveUser(id);

        validEmail(req.email());
        user.setName(req.name());
        user.setEmail(req.email());
        user.setRole(req.role());
        return userMapper.toResponse(userRepository.save(user));
    }

    public void editUserById(String id, EditRequest req) {
        User user = retrieveUser(id);

        if (validName(req.name()))
            user.setName(req.name());
        if (validEmail(req.email()))
            user.setEmail(req.email());
        if (req.role() != null)
            user.setRole(req.role());
        if (req.password() != null)
            user.setPassword(encoder.encode(req.password()));

        userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    // Private    
    private User retrieveUser(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    private boolean validName(String name) {
        if (name == null)
            return false;

        if (name.isBlank())
            throw new BadRequestException("Name must not be Blank");

        return true;
    }

    private boolean validEmail(String email) {
        if (email == null)
            return false;

        if (userRepository.findByEmail(email).isPresent())
            throw new ConflictException("Email already exists");

        return true;
    }
}