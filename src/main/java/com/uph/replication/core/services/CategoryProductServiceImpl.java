package com.uph.replication.core.services;

import com.uph.replication.core.entities.MasterCategoryProducts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryProductServiceImpl implements CategoryProductService {

    @Autowired
    private CategoryProductService categoryProductService;

    @Override
    public MasterCategoryProducts getCategoryProductByProductName(String categoryName) {
        return categoryProductService.getCategoryProductByProductName(categoryName);
    }
}
