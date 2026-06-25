package com.ecommerce.todolistbackend;

public record AuthResponse(
        String token,
        Integer userId
) {
}
