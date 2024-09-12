package com.uph.replication.core.controllers;

import com.uph.replication.core.constants.APIConstants;
import com.uph.replication.core.entities.ProductsByCategories;
import com.uph.replication.core.services.ProductByCategoryService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping(value = APIConstants.B2C_BASE_PRODUCT_URL)
public class ProductByCategoryController {

    @Resource
    ProductByCategoryService productByCategoryService;

    @GetMapping
    public List<ProductsByCategories> getAllProductByCategory() {
        return productByCategoryService.getAllProductsByCategory();
    }

}
