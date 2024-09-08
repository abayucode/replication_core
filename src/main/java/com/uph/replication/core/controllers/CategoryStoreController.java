package com.uph.replication.core.controllers;

import com.uph.replication.core.constants.APIConstants;
import com.uph.replication.core.dto.ReqInsertUpdateCategoryStoreDTO;
import com.uph.replication.core.dto.responses.ApiResult;
import com.uph.replication.core.services.CategoryStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(APIConstants.BASE_STORE_URL)
public class CategoryStoreController {

    @Autowired
    CategoryStoreService categoryStoreService;

    @PostMapping("/category")
    public ApiResult<Object> insertNewCategoryStore(@RequestBody ReqInsertUpdateCategoryStoreDTO dto) {
        return categoryStoreService.insertCategory(dto);
    }

    @GetMapping("/categories")
    public ApiResult<Object> getAllCategory() {
        return categoryStoreService.getAllCategoryProduct();
    }

    @PutMapping("/category")
    public ApiResult<Object> updateCategoryStore(@RequestBody ReqInsertUpdateCategoryStoreDTO dto) {
        return categoryStoreService.updateCategoryStore(dto);
    }

    @DeleteMapping("/category/{storeCategoryName}")
    public ApiResult<Object> deleteCategoryStore(@PathVariable String storeCategoryName) {
        return categoryStoreService.deleteCategoryStore(storeCategoryName);
    }
}
