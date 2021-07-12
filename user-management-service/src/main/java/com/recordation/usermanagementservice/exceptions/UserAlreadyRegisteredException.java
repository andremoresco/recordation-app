package com.recordation.usermanagementservice.exceptions;

public class UserAlreadyRegisteredException extends Exception {

    public String code;
    public String message;

    public UserAlreadyRegisteredException() {
        super("User already registered with the identifier.");
        this.code = "USER_ALREADY_REGISTERED";
        this.message = "User already registered with the identifier.";
    }
}
