package com.uph.replication.core.services;

import com.uph.replication.core.dto.ApiResult;
import com.uph.replication.core.dto.requests.ReqInsertSetProductStoreDTO;
import com.uph.replication.core.dto.responses.RespDisplayProductsDTO;

import java.util.List;

public interface SetProductStoreService {
    ApiResult<RespDisplayProductsDTO> insertSetProductStore(ReqInsertSetProductStoreDTO request);
    ApiResult<List<RespDisplayProductsDTO>> getProductByCategory();
}
