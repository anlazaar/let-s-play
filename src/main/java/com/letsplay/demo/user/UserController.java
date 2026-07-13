package com.letsplay.demo.user;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.letsplay.demo.user.DTO.CreateRequest;
import com.letsplay.demo.user.DTO.EditRequest;
import com.letsplay.demo.user.DTO.UpdateRequest;
import com.letsplay.demo.user.DTO.UserResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@Valid @RequestBody CreateRequest req) {
        return userService.createUser(req);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public UserResponse updateUser(@PathVariable String id, @RequestBody UpdateRequest req) {
        return userService.updateUserById(id, req);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editUser(@PathVariable String id, @RequestBody EditRequest req) {
        userService.editUserById(id, req);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }
}