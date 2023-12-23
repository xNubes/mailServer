package com.springframework.boot.springbootstartermail;
public record Details (
    String name,
    String subject,
    String message,
    String email,
    String receiver
) {}
