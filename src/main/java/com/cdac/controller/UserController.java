package com.cdac.controller;

import com.cdac.dto.UserReqDTO;
import com.cdac.dto.UserRespDTO;
import com.cdac.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

<<<<<<< HEAD
@CrossOrigin(origins = "http://localhost:3000")
=======
>>>>>>> cf83aff6a78db13d4b57b430b69755b3012088e5
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

<<<<<<< HEAD
    @PostMapping("/add")
=======
    @PostMapping
>>>>>>> cf83aff6a78db13d4b57b430b69755b3012088e5
    public ResponseEntity<UserRespDTO> createUser(@RequestBody @Valid UserReqDTO dto) {
        return ResponseEntity.ok(userService.createUser(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRespDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRespDTO> updateUser(@PathVariable Long id, @RequestBody @Valid UserReqDTO dto) {
        return ResponseEntity.ok(userService.updateUser(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

<<<<<<< HEAD
    @GetMapping("/list")
=======
    @GetMapping
>>>>>>> cf83aff6a78db13d4b57b430b69755b3012088e5
    public ResponseEntity<List<UserRespDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserRespDTO> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }
}
