package com.uph.replication.core.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ApiResultEnums {

    SUCCESS("Successfully", String.valueOf(HttpStatus.OK.value())),
    ERROR("Failed", String.valueOf(HttpStatus.NOT_FOUND.value())),
    STORE_ADDED_SUCCESS("Store added successfully", String.valueOf(HttpStatus.OK.value())),
    STORE_ADDED_FAILED("Category of store not found", String.valueOf(HttpStatus.NOT_FOUND.value()));

    private String message;
    private String httpRes;

    ApiResultEnums(String message, String httpRes) {
        this.message = message;
        this.httpRes = httpRes;
    }
}
