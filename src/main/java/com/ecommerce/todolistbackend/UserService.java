package com.ecommerce.todolistbackend;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo repo;
    private final PasswordEncoder encoder;
    public UserResponse addUser(UserRequest request){
        User savedUser = repo.save(userRequestToUser(request));
        return UserResponse.builder()
                .id(savedUser.getId())
                .userName(savedUser.getUsername())
                .build();
    }

    private User userRequestToUser(UserRequest request) {
        return User.builder()
                .name(request.userName())
                .password(encoder.encode(request.password()))
                .build();
    }


    public User findByUserName(String username) {
        return repo.findByName(username)
                .orElseThrow(()-> new RuntimeException("User not found exception !!!"));
    }
}
