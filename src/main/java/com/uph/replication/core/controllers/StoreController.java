package com.uph.replication.core.controllers;

import com.uph.replication.core.constants.APIConstants;
import com.uph.replication.core.dto.ReqInsertStoreDTO;
import com.uph.replication.core.dto.responses.ApiResult;
import com.uph.replication.core.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = APIConstants.BASE_STORE_URL)
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PostMapping("/new")
    public ApiResult<Object> insertStore(@RequestBody ReqInsertStoreDTO reqInsertStoreDTO) {
        return storeService.insertNewStore(reqInsertStoreDTO);
    }
}
