package com.uph.replication.core.dto;

import com.uph.replication.core.enums.ApiResultEnums;
import lombok.Data;
import lombok.ToString;

import java.util.Map;

@Data
@ToString
public class ApiResult<T> {

    private String message;
    private String code;
    private Object result;

    public ApiResult(ApiResultEnums apiResultEnums, T result) {
        this.code = apiResultEnums.getHttpRes();
        this.message = apiResultEnums.getMessage();
        this.result = result;
    }

    public ApiResult(ApiResultEnums apiResultEnums, Map<String, String> result) {
        this.code = apiResultEnums.getHttpRes();
        this.message = apiResultEnums.getMessage();
        this.result = result;
    }
}
