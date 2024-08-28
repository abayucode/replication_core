package com.uph.replication.core.services;

import com.uph.replication.core.dto.ReqInsertStoreDTO;
import com.uph.replication.core.dto.responses.ApiResult;

public interface StoreService {

    ApiResult<Object> insertNewStore(ReqInsertStoreDTO reqInsertStoreDTO);

}
