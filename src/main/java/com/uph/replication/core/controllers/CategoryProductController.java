package com.uph.replication.core.controllers;

import com.uph.replication.core.constants.APIConstants;
import com.uph.replication.core.dto.requests.ReqGetCategoryProductById;
import com.uph.replication.core.dto.requests.ReqInsertCategoryProduct;
import com.uph.replication.core.dto.ApiResult;
import com.uph.replication.core.dto.requests.ReqUpdateCategoryProduct;
import com.uph.replication.core.dto.responses.RespGetCategoryProducts;
import com.uph.replication.core.dto.responses.RespUpdateCategoryProduct;
import com.uph.replication.core.services.CategoryProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(APIConstants.B2B_BASE_CATEGORY_PRODUCT_URL)
public class CategoryProductController {

    @Autowired
    private CategoryProductService categoryProductService;

    @PostMapping
    public ApiResult<Object> insertNewCategoryProduct(@RequestBody ReqInsertCategoryProduct request) {
        return categoryProductService.insertNewCategoryProducts(request);
    }

    @GetMapping
    public ApiResult<List<RespGetCategoryProducts>> getCategoryProducts() {
        return categoryProductService.findAllCategoryProduct();
    }

    @DeleteMapping
    public ApiResult<Object> deleteCategoryProduct(@RequestBody ReqGetCategoryProductById request) {
        return categoryProductService.deleteCategoryProduct(request);
    }

    @PatchMapping
    public ApiResult<RespUpdateCategoryProduct> updateCategoryProduct(@RequestBody ReqUpdateCategoryProduct request) {
        return categoryProductService.updateCategoryProduct(request);
    }
}
