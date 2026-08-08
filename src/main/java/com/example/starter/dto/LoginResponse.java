package com.example.starter.dto;

public record LoginResponse(
        String token,
        String email,
        String role
) {}