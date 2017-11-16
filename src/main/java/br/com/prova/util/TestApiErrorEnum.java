package br.com.prova.util;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TestApiErrorEnum {

	UNEXPECTED_TECHNICAL_ERROR("E002"),
    INVALID_ARGUMENT("E100"),
    REGISTRY_NOT_FOUND("E101"), 
    DUPLICATED_REGISTRY("E102");

    private final String code;

    private TestApiErrorEnum(String s) {
        code = s;
    }

    public boolean equalsCode(String otherName) {
        return (otherName == null) ? false : code.equals(otherName);
    }

    @JsonValue
    @Override
    public String toString() {
        return this.code;
    }
}
