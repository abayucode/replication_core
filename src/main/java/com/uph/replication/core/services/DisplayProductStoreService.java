package com.uph.replication.core.services;

import com.uph.replication.core.dto.ApiResult;
import com.uph.replication.core.dto.requests.ReqSearchProduct;
import com.uph.replication.core.dto.responses.RespDisplayProductsDTO;
import com.uph.replication.core.entities.SetProductsStore;

import java.util.List;

public interface DisplayProductStoreService {
    ApiResult<List<RespDisplayProductsDTO>> displayProducts(ReqSearchProduct keyword);
    ApiResult<List<SetProductsStore>> findAllProductsStore();
}
