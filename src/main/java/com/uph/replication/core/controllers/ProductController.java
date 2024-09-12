package com.uph.replication.core.controllers;

import com.uph.replication.core.constants.APIConstants;
import com.uph.replication.core.dto.requests.ReqInsertProductDTO;
import com.uph.replication.core.dto.ApiResult;
import com.uph.replication.core.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(APIConstants.B2B_BASE_PRODUCT_URL)
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/new")
    public ApiResult<Object> insertNewProduct(@RequestBody ReqInsertProductDTO request) {
        return productService.insertNewProduct(request);
    }

}
