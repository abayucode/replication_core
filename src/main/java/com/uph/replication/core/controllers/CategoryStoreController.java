package com.uph.replication.core.controllers;

import com.uph.replication.core.constants.APIConstants;
import com.uph.replication.core.dto.ReqInsertCategoryStoreDTO;
import com.uph.replication.core.dto.responses.ApiResult;
import com.uph.replication.core.services.CategoryStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(APIConstants.BASE_STORE_URL)
public class CategoryStoreController {

    @Autowired
    CategoryStoreService categoryStoreService;

    @PostMapping("category")
    public ApiResult<Object> insertNewCategoryStore(@RequestBody  ReqInsertCategoryStoreDTO dto) {
        return categoryStoreService.insertCategory(dto);
    }

}
