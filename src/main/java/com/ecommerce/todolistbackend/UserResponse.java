package com.ecommerce.todolistbackend;

import lombok.Builder;

@Builder
public record UserResponse(
        String userName,
        Integer id
) {
}
