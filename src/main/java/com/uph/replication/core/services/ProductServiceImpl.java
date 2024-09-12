package com.uph.replication.core.services;

import com.uph.replication.core.dto.requests.ReqInsertProductDTO;
import com.uph.replication.core.dto.ApiResult;
import com.uph.replication.core.entities.MasterCategoryProducts;
import com.uph.replication.core.entities.MasterProducts;
import com.uph.replication.core.entities.ProductsByCategories;
import com.uph.replication.core.enums.ApiResultEnums;
import com.uph.replication.core.repositories.ProductByCategoryRepository;
import com.uph.replication.core.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryProductService categoryProductService;

    @Autowired
    private ProductByCategoryRepository productByCategoryRepository;

    @Override
    public ApiResult<Object> insertNewProduct(ReqInsertProductDTO reqInsertProductDTO) {
        MasterProducts masterProducts = new MasterProducts();
        ProductsByCategories productsByCategories = new ProductsByCategories();
        MasterCategoryProducts categoryProducts = categoryProductService.findByCategoryName(reqInsertProductDTO.getProductCategory());

        if (categoryProductService.isCategoryProductExist(reqInsertProductDTO.getProductCategory())) {
            return new ApiResult<>(ApiResultEnums.CATEGORY_PRODUCT_IS_NOT_FOUND, null);
        }

        List<MasterProducts> products = productRepository.findAll();
        for (MasterProducts product: products) {
            if (reqInsertProductDTO.getProductCode().equals(product.getProductCode())) {
                return new ApiResult<>(ApiResultEnums.PRODUCT_CODE_IS_EXIST, product.getProductCode());
            }
        }

        masterProducts.setProductName(reqInsertProductDTO.getProductName());
        masterProducts.setProductCode(reqInsertProductDTO.getProductCode());
        masterProducts.setProductQuantity(reqInsertProductDTO.getStock());
        masterProducts.setProductExpired(reqInsertProductDTO.getProductExpiryDate());
        masterProducts.setCreatedAt(new Date());
        masterProducts.setUpdatedAt(new Date());
        masterProducts.setDeletedAt(null);

        productRepository.save(masterProducts);
        productsByCategories.setProducts(masterProducts);
        productsByCategories.setCategoryProducts(categoryProducts);
        productByCategoryRepository.save(productsByCategories);

        Map<String, String> response = new HashMap<>();
        response.put("productId", masterProducts.getId());

        return new ApiResult<>(ApiResultEnums.PRODUCT_SUCCESS_ADDED, response);
    }
}
