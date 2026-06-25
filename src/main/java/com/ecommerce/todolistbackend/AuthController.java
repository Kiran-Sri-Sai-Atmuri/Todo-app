package com.ecommerce.todolistbackend;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173/")
public class AuthController {
    private final JwtService service;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    @PostMapping("/login")
    public AuthResponse generateToken(@RequestBody UserRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.userName(),request.password()));
        User user = userService.findByUserName(request.userName());
        String token = service.generateToken(request,user.getId());

        return new AuthResponse(token, user.getId());
    }
}
