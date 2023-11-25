package com.ctf.v1.service;

public interface JwtService {
    String extractUserName(String token);

    String generateToken(String userDetails);

    String generateRefreshToken(String userDetails);

    boolean isTokenValid(String token, String userDetails);
}