package com.uph.replication.core.services;

import com.uph.replication.core.dto.ReqInsertProductDTO;
import com.uph.replication.core.dto.responses.ApiResult;
import com.uph.replication.core.entities.MasterCategoryProducts;
import com.uph.replication.core.entities.MasterProducts;
import com.uph.replication.core.enums.ApiResultEnums;
import com.uph.replication.core.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryProductService categoryProductService;

    @Override
    public ApiResult<Object> insertNewProduct(ReqInsertProductDTO reqInsertProductDTO) {
        MasterProducts masterProducts = new MasterProducts();

        if (!categoryProductService.isCategoryProductExist(reqInsertProductDTO.getProductCategory())) {
            return new ApiResult<>(ApiResultEnums.CATEGORY_PRODUCT_IS_NOT_FOUND, null);
        }

        masterProducts.setProductName(reqInsertProductDTO.getProductName());
        masterProducts.setProductCode(reqInsertProductDTO.getProductCode());
        masterProducts.setProductQuantity(reqInsertProductDTO.getStock());
        masterProducts.setProductExpired(reqInsertProductDTO.getProductExpiryDate());
        masterProducts.setCreatedAt(new Date());
        masterProducts.setUpdatedAt(new Date());
        masterProducts.setDeletedAt(null);

        productRepository.save(masterProducts);

        Map<String, String> response = new HashMap<>();
        response.put("productId", masterProducts.getId());

        return new ApiResult<>(ApiResultEnums.PRODUCT_SUCCESS_ADDED, response);
    }
}
