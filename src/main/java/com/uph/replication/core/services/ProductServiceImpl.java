package com.uph.replication.core.services;

import com.uph.replication.core.dto.requests.ReqInsertProductDTO;
import com.uph.replication.core.dto.ApiResult;
import com.uph.replication.core.dto.ReqRespUpdateProduct;
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

        if (null == categoryProducts) {
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
        masterProducts.setProductQuantity(reqInsertProductDTO.getProductQuantity());
        masterProducts.setProductExpired(reqInsertProductDTO.getProductExpiryDate());
        masterProducts.setProductDescription(reqInsertProductDTO.getProductDescription());
        masterProducts.setProductSize(reqInsertProductDTO.getProductSize());
        masterProducts.setProductUnitSize(reqInsertProductDTO.getProductUnitSize());
        masterProducts.setProductPrice(reqInsertProductDTO.getProductPrice());
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

    @Override
    public ApiResult<ReqRespUpdateProduct> updateProduct(ReqRespUpdateProduct reqUpdateProduct) {
        ReqRespUpdateProduct reqRespUpdateProduct = new ReqRespUpdateProduct();
        if (productRepository.findByProductCode(reqUpdateProduct.getProductCode()).isPresent()) {
            MasterProducts masterProducts = productRepository.findByProductCode(reqUpdateProduct.getProductCode()).get();
            masterProducts.setProductName(reqUpdateProduct.getProductName());
            masterProducts.setProductQuantity(reqUpdateProduct.getProductQuantity());
            masterProducts.setProductDescription(reqUpdateProduct.getProductDescription());
            masterProducts.setProductPrice(reqUpdateProduct.getProductPrice());
            masterProducts.setUpdatedAt(new Date());
            productRepository.save(masterProducts);

            reqRespUpdateProduct.setProductCode(masterProducts.getProductCode());
            reqRespUpdateProduct.setProductName(masterProducts.getProductName());
            reqRespUpdateProduct.setProductQuantity(masterProducts.getProductQuantity());
            reqRespUpdateProduct.setProductPrice(masterProducts.getProductPrice());
            reqRespUpdateProduct.setProductDescription(masterProducts.getProductDescription());
            return new ApiResult<>(ApiResultEnums.SUCCESS_UPDATED, reqRespUpdateProduct);
        }

        return new ApiResult<>(ApiResultEnums.ERROR_UPDATED, reqRespUpdateProduct);
    }
}
