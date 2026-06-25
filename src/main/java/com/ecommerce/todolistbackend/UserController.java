package com.ecommerce.todolistbackend;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService service;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest request){
        return ResponseEntity.ok(service.addUser(request));
    }
    @GetMapping
    public String test(){
        return "Test is success";
    }
}
