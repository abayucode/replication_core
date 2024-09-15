package com.uph.replication.core.controllers;

import com.uph.replication.core.constants.APIConstants;
import com.uph.replication.core.dto.ApiResult;
import com.uph.replication.core.dto.requests.ReqSearchProduct;
import com.uph.replication.core.dto.responses.RespDisplayProductsDTO;
import com.uph.replication.core.entities.MasterProducts;
import com.uph.replication.core.services.DisplayProductStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(APIConstants.B2C_BASE_PRODUCT_URL)
public class DisplayProductController {

    @Autowired
    DisplayProductStoreService displayProductStoreService;

    @PostMapping
    public ApiResult<List<RespDisplayProductsDTO>> search(@RequestBody ReqSearchProduct request) {
        return displayProductStoreService.displayProducts(request);
    }
}
