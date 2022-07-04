package com.brayanmnz.market.domain.dto;

public class AuthenticationResponse {
    private String JWT;

    public AuthenticationResponse(String JWT) {
        this.JWT = JWT;
    }

    public String getJWT() {
        return JWT;
    }
    public void setJWT(String JWT) {
        this.JWT = JWT;
    }
}
