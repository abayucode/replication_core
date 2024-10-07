package com.uph.replication.core.interceptor;

import com.uph.replication.core.constants.HeaderConstants;
import com.uph.replication.core.constants.InterceptorRespConstants;
import com.uph.replication.core.dto.ApiResult;
import com.uph.replication.core.enums.ApiResultEnums;
import com.uph.replication.core.services.StoreService;
import com.uph.replication.core.services.StoreServiceImpl;
import com.uph.replication.core.utils.JSONUtils;
import com.uph.replication.core.utils.StrUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Component
public class RequestInterceptor implements HandlerInterceptor {

    @Autowired
    private StoreServiceImpl storeService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String partnerId = request.getHeader(HeaderConstants.X_PARTNER_ID);
        String externalId = request.getHeader(HeaderConstants.X_EXTERNAL_ID);
        String signature = request.getHeader(HeaderConstants.X_SIGNATURE);
        String timestamp = request.getHeader(HeaderConstants.X_TIMESTAMP);

        if (StrUtils.isNullOrEmpty(partnerId)) {
            responseError(response, new ApiResult<>(ApiResultEnums.ERROR, InterceptorRespConstants.X_PARTNER_ID_MANDATORY));
            return false;
        }

        if (StrUtils.isNullOrEmpty(externalId)) {
            responseError(response, new ApiResult<>(ApiResultEnums.ERROR, InterceptorRespConstants.X_EXTERNAL_ID_MANDATORY));
            return false;
        }

        if (StrUtils.isNullOrEmpty(signature)) {
            responseError(response, new ApiResult<>(ApiResultEnums.ERROR, InterceptorRespConstants.X_SIGNATURE_ID_MANDATORY));
            return false;
        }

        if (StrUtils.isNullOrEmpty(timestamp)) {
            responseError(response, new ApiResult<>(ApiResultEnums.ERROR, InterceptorRespConstants.X_TIMESTAMP_ID_MANDATORY));
            return false;
        }

        Boolean isStoreExist = storeService.isStoreExist(partnerId);
        if (!isStoreExist) {
            responseError(response, new ApiResult<>(ApiResultEnums.ERROR, InterceptorRespConstants.X_PARTNER_ID_NOT_FOUND));
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private void responseError(HttpServletResponse response, ApiResult<Object> objectApiResult) throws Exception {
        response.setContentType("application/json");
        response.getWriter().write(Objects.requireNonNull(JSONUtils.objectToJson(objectApiResult)));
    }

    private boolean isEmpty(HttpServletResponse response, String partnerId, String externalId, String signature, String timestamp) throws Exception {
        if (null == partnerId) {
            responseError(response, new ApiResult<>(ApiResultEnums.ERROR, InterceptorRespConstants.X_PARTNER_ID_MANDATORY));
            return true;
        }

        if (null == externalId) {
            responseError(response, new ApiResult<>(ApiResultEnums.ERROR, InterceptorRespConstants.X_EXTERNAL_ID_MANDATORY));
            return true;
        }

        if (null == signature) {
            responseError(response, new ApiResult<>(ApiResultEnums.ERROR, InterceptorRespConstants.X_SIGNATURE_ID_MANDATORY));
        }

        if (null == timestamp) {
            responseError(response, new ApiResult<>(ApiResultEnums.ERROR, InterceptorRespConstants.X_TIMESTAMP_ID_MANDATORY));
        }

        return false;
    }
}
