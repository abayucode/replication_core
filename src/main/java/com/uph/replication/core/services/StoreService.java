package com.uph.replication.core.services;

import com.uph.replication.core.dto.requests.ReqInsertStoreDTO;
import com.uph.replication.core.dto.ApiResult;
import com.uph.replication.core.dto.responses.ReqRespUpdateStore;

public interface StoreService {

    ApiResult<Object> insertNewStore(ReqInsertStoreDTO reqInsertStoreDTO);
    Boolean isStoreExist(String storeCode);
    ApiResult<ReqRespUpdateStore> updateStore(ReqRespUpdateStore reqRespUpdateStore);

}
