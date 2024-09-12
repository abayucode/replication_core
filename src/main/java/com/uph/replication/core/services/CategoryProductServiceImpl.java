package com.uph.replication.core.services;

import com.uph.replication.core.dto.requests.ReqGetCategoryProductById;
import com.uph.replication.core.dto.requests.ReqInsertCategoryProduct;
import com.uph.replication.core.dto.ApiResult;
import com.uph.replication.core.dto.requests.ReqUpdateCategoryProduct;
import com.uph.replication.core.dto.responses.RespGetCategoryProducts;
import com.uph.replication.core.dto.responses.RespUpdateCategoryProduct;
import com.uph.replication.core.entities.MasterCategoryProducts;
import com.uph.replication.core.enums.ApiResultEnums;
import com.uph.replication.core.repositories.CategoryProductRepository;
import com.uph.replication.core.utils.JSONUtils;
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
        List<MasterCategoryProducts> categoryProducts = categoryProductRepository.findAll();

        for (MasterCategoryProducts masterCategoryProduct: categoryProducts) {
            if (masterCategoryProduct.getCategoryName().equalsIgnoreCase(request.getCategoryName())) {
                return new ApiResult<>(ApiResultEnums.CATEGORY_PRODUCT_ALREADY_EXIST, null);
            }
        }

        masterCategoryProducts.setCategoryName(request.getCategoryName().toUpperCase());
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
        MasterCategoryProducts categoryProducts = categoryProductRepository.findMasterCategoryProductsByCategoryNameContainingIgnoreCase(categoryName);
        return categoryName.equalsIgnoreCase(categoryProducts.getCategoryName());
    }

    @Override
    public MasterCategoryProducts findByCategoryName(String categoryName) {
        return categoryProductRepository.findMasterCategoryProductsByCategoryNameContainingIgnoreCase(categoryName);
    }

    @Override
    public ApiResult<List<RespGetCategoryProducts>> findAllCategoryProduct() {
        List<MasterCategoryProducts> categoryProducts = categoryProductRepository.findAll();
        List<RespGetCategoryProducts> respGetCategoryProducts = new ArrayList<>();

        for (MasterCategoryProducts masterCategoryProduct: categoryProducts) {
            RespGetCategoryProducts respGetAllCategoryProduct = new RespGetCategoryProducts();
            respGetAllCategoryProduct.setProductCategoryName(masterCategoryProduct.getCategoryName());
            respGetCategoryProducts.add(respGetAllCategoryProduct);
        }

        return new ApiResult<>(ApiResultEnums.SUCCESS, respGetCategoryProducts);
    }

    @Override
    public ApiResult<Object> deleteCategoryProduct(ReqGetCategoryProductById request) {

        if (categoryProductRepository.findById(request.getCategoryProductId()).isPresent()) {
            MasterCategoryProducts masterCategoryProducts = categoryProductRepository
                    .findById(request.getCategoryProductId())
                    .get();
            categoryProductRepository.delete(masterCategoryProducts);
            return new ApiResult<>(ApiResultEnums.SUCCESS_DELETED, null);
        }

        return new ApiResult<>(ApiResultEnums.ERROR_DATA_NOT_FOUND, null);
    }

    @Override
    public ApiResult<RespUpdateCategoryProduct> updateCategoryProduct(ReqUpdateCategoryProduct request) {
        RespUpdateCategoryProduct respUpdateCategoryProduct = new RespUpdateCategoryProduct();
        if (categoryProductRepository.existsById(request.getCategoryProductId())) {
            MasterCategoryProducts masterCategoryProducts = categoryProductRepository
                    .findById(request.getCategoryProductId())
                    .get();
            masterCategoryProducts.setCategoryName(request.getNewCategoryName().toUpperCase());
            masterCategoryProducts.setUpdatedAt(new Date());
            masterCategoryProducts.setCreatedAt(request.getCreatedAt());
            categoryProductRepository.save(masterCategoryProducts);
            respUpdateCategoryProduct.setCategoryProductName(masterCategoryProducts.getCategoryName());
            return new ApiResult<>(ApiResultEnums.SUCCESS_UPDATED, respUpdateCategoryProduct);
        }
        return new ApiResult<>(ApiResultEnums.ERROR_UPDATED, respUpdateCategoryProduct);
    }
}
