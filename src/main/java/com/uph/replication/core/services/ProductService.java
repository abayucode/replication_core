package com.uph.replication.core.services;

import com.uph.replication.core.dto.requests.ReqInsertProductDTO;
import com.uph.replication.core.dto.ApiResult;

public interface ProductService {
    public ApiResult<Object> insertNewProduct(ReqInsertProductDTO reqInsertProductDTO);
}
