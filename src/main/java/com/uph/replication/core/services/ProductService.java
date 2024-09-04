package com.uph.replication.core.services;

import com.uph.replication.core.dto.ReqInsertProductDTO;
import com.uph.replication.core.dto.responses.ApiResult;

public interface ProductService {
    public ApiResult<Object> insertNewProduct(ReqInsertProductDTO reqInsertProductDTO);
}
