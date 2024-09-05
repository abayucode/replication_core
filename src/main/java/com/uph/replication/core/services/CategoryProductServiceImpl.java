package com.uph.replication.core.services;

import com.uph.replication.core.dto.ReqInsertCategoryProduct;
import com.uph.replication.core.dto.ReqInsertProductDTO;
import com.uph.replication.core.dto.responses.ApiResult;
import com.uph.replication.core.entities.MasterCategoryProducts;
import com.uph.replication.core.enums.ApiResultEnums;
import com.uph.replication.core.repositories.CategoryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryProductServiceImpl implements CategoryProductService {

    @Autowired
    private CategoryProductRepository categoryProductRepository;

    @Override
    public ApiResult<Object> insertNewCategoryProducts(ReqInsertCategoryProduct request) {
        MasterCategoryProducts masterCategoryProducts = new MasterCategoryProducts();

        if (!isCategoryProductExist(request.getCategoryName())) {
            return new ApiResult<>(ApiResultEnums.CATEGORY_PRODUCT_ALREADY_EXIST, null);
        }

        masterCategoryProducts.setCategoryName(request.getCategoryName());
        masterCategoryProducts.setCreatedAt(new Date());
        masterCategoryProducts.setUpdatedAt(new Date());
        masterCategoryProducts.setDeletedAt(null);

        categoryProductRepository.save(masterCategoryProducts);

        Map<String, String> response = new HashMap<>();
        response.put("categoryProductId", masterCategoryProducts.getId());

        return new ApiResult<>(ApiResultEnums.CATEGORY_PRODUCT_SUCCESS_ADDED, response);
    }

    @Override
    public Boolean isCategoryProductExist(String categoryName) {
        MasterCategoryProducts categoryProducts = categoryProductRepository.findMasterCategoryProductsByCategoryName(categoryName);
        return null == categoryProducts;
    }

    @Override
    public MasterCategoryProducts findByCategoryName(String categoryName) {
        return categoryProductRepository.findMasterCategoryProductsByCategoryName(categoryName);
    }
}
