package com.uph.replication.core.services;

import com.uph.replication.core.dto.requests.ReqInsertStoreDTO;
import com.uph.replication.core.dto.ApiResult;

public interface StoreService {

    ApiResult<Object> insertNewStore(ReqInsertStoreDTO reqInsertStoreDTO);
    Boolean isStoreExist(String storeCode);

}
