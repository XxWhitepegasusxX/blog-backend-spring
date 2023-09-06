package com.example.blog.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRecordDto(
    @NotBlank @Size(min = 3) String username,
    @NotBlank String email,
    @NotBlank String password
){}
