package com.recordation.usermanagementservice.exceptions;

public class UserArgumentsNotValidException extends Exception {

    public String code;
    public String message;

    public UserArgumentsNotValidException() {
        super("User arguments not valid!");
        this.code = "USER_ARGUMENT_NOT_VALID";
        this.message = "User arguments not valid!";
    }

    public UserArgumentsNotValidException(String message) {
        super(message);
        this.code = "USER_ARGUMENT_NOT_VALID";
        this.message = "User arguments not valid! " + message;
    }

}
