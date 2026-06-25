package com.ecommerce.todolistbackend;

public record UserRequest(
        String userName,
        String password
) {
}
