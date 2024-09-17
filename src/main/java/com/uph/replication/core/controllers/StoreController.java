package com.uph.replication.core.controllers;

import com.uph.replication.core.constants.APIConstants;
import com.uph.replication.core.dto.requests.ReqInsertStoreDTO;
import com.uph.replication.core.dto.ApiResult;
import com.uph.replication.core.dto.responses.ReqRespUpdateStore;
import com.uph.replication.core.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = APIConstants.B2B_BASE_STORE_URL)
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PostMapping("/new")
    public ApiResult<Object> insertStore(@RequestBody ReqInsertStoreDTO reqInsertStoreDTO) {
        return storeService.insertNewStore(reqInsertStoreDTO);
    }

    @PatchMapping
    public ApiResult<ReqRespUpdateStore> updateStore(@RequestBody ReqRespUpdateStore reqRespUpdateStore) {
        return storeService.updateStore(reqRespUpdateStore);
    }
}
