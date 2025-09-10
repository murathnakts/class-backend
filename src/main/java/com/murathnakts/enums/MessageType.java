package com.murathnakts.enums;

import lombok.Getter;

@Getter
public enum MessageType {
    NO_RECORD_EXIST("1004", "No record exists"),
    TOKEN_EXPIRED("1005", "Token expired"),
    USERNAME_NOT_FOUND("1006", "Username not found"),
    USERNAME_OR_PASSWORD_INVALID("1007", "Username or password invalid"),
    REFRESH_TOKEN_NOT_FOUND("1008", "Refresh token not found"),
    REFRESH_TOKEN_EXPIRED("1009", "Refresh token expired"),
    GROUP_NOT_FOUND("1010", "Group not found"),
    USER_NOT_FOUND("1011", "User not found"),
    USER_ALREADY_ADDED("1012", "User already added"),
    GENERAL_EXCEPTION("9999",  "General exception"),;

    private final String code;
    private final String message;

    MessageType(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
