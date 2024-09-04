package com.uph.replication.core.services;

import com.uph.replication.core.dto.ReqInsertProductDTO;
import com.uph.replication.core.dto.responses.ApiResult;
import com.uph.replication.core.entities.MasterCategoryProducts;
import com.uph.replication.core.entities.MasterProducts;
import com.uph.replication.core.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryProductService categoryProductService;

    @Override
    public ApiResult<Object> insertNewProduct(ReqInsertProductDTO reqInsertProductDTO) {
        MasterProducts masterProducts = new MasterProducts();
        MasterCategoryProducts categoryProduct = categoryProductService.getCategoryProductByProductName(reqInsertProductDTO.getProductCode());

        masterProducts.setProductName(reqInsertProductDTO.getProductName());
        masterProducts.setProductCode(reqInsertProductDTO.getProductCode());
        masterProducts.setProductQuantity(reqInsertProductDTO.getStock());
        masterProducts.setProductExpired(reqInsertProductDTO.getProductExpiryDate());
        masterProducts.setCreatedAt(new Date());
        masterProducts.setUpdatedAt(new Date());
        masterProducts.setDeletedAt(null);

        productRepository.save(masterProducts);

        return null;
    }
}
