package com.uph.replication.core.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ApiResultEnums {

    SUCCESS("Successfully", String.valueOf(HttpStatus.OK.value())),
    SUCCESS_UPDATED("Successfully updated", String.valueOf(HttpStatus.OK.value())),
    SUCCESS_DELETED("Successfully deleted", String.valueOf(HttpStatus.NO_CONTENT.value())),

    ERROR("Failed", String.valueOf(HttpStatus.NOT_FOUND.value())),
    ERROR_UPDATED("Fail updated the data", String.valueOf(HttpStatus.BAD_REQUEST.value())),
    ERROR_DELETE("Fail deleted the data", String.valueOf(HttpStatus.BAD_REQUEST.value())),

    ERROR_DATA_NOT_FOUND("Data not found", String.valueOf(HttpStatus.NOT_FOUND.value())),

    STORE_ADDED_SUCCESS("Store added successfully", String.valueOf(HttpStatus.OK.value())),
    STORE_ADDED_FAILED("Category of store not found", String.valueOf(HttpStatus.NOT_FOUND.value())),

    STORE_CATEGORY_ADDED_SUCCESS("Category of store added successfully", String.valueOf(HttpStatus.OK.value())),

    CATEGORY_PRODUCT_SUCCESS_ADDED("Category of product added successfully", String.valueOf(HttpStatus.OK.value())),
    CATEGORY_PRODUCT_ALREADY_EXIST("Category of product is already exist", String.valueOf(HttpStatus.FORBIDDEN.value())),
    CATEGORY_PRODUCT_IS_NOT_FOUND("Category of product is not found", String.valueOf(HttpStatus.NOT_FOUND.value())),

    PRODUCT_SUCCESS_ADDED("Product success added", String.valueOf(HttpStatus.OK.value())),
    PRODUCT_CODE_IS_EXIST("Product code can't duplicated", String.valueOf(HttpStatus.FORBIDDEN.value())),

    PRODUCT_STORE_SUCCESS_DISPLAYED("Successfully display product", String.valueOf(HttpStatus.OK.value()));

    private String message;
    private String httpRes;
    private String data;

    ApiResultEnums(String message, String httpRes) {
        this.message = message;
        this.httpRes = httpRes;
    }

    ApiResultEnums(String message, String data, String httpRes) {
        this.message = message;
        this.data = data;
        this.httpRes = httpRes;
    }
}
