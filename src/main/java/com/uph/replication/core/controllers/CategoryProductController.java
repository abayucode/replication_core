package com.uph.replication.core.controllers;

import com.uph.replication.core.constants.APIConstants;
import com.uph.replication.core.dto.ReqInsertCategoryProduct;
import com.uph.replication.core.dto.responses.ApiResult;
import com.uph.replication.core.services.CategoryProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(APIConstants.BASE_PRODUCT_URL)
public class CategoryProductController {

    @Autowired
    private CategoryProductService categoryProductService;

    @PostMapping("/category")
    public ApiResult<Object> insertNewCategoryProduct(@RequestBody ReqInsertCategoryProduct request) {
        return categoryProductService.insertNewCategoryProducts(request);
    }

}
