package me.ledovec.ticketsystem.services.auth;

public class AuthResponse {

    final public boolean response;
    final public String token;

    AuthResponse(boolean response, String token) {
        this.response = response;
        this.token = token;
    }

    public static AuthResponse empty() {
        return new AuthResponse(false, "");
    }

}
