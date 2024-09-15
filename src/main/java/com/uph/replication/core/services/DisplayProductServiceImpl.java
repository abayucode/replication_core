package com.uph.replication.core.services;

import com.uph.replication.core.dto.ApiResult;
import com.uph.replication.core.dto.requests.ReqSearchProduct;
import com.uph.replication.core.dto.responses.RespDisplayProductsDTO;
import com.uph.replication.core.entities.MasterProducts;
import com.uph.replication.core.enums.ApiResultEnums;
import com.uph.replication.core.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DisplayProductServiceImpl implements DisplayProductStoreService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public ApiResult<List<RespDisplayProductsDTO>> displayProducts(ReqSearchProduct keyword) {
        RespDisplayProductsDTO displayProductsDTO = new RespDisplayProductsDTO();
        List<RespDisplayProductsDTO> displayLists = new ArrayList<>();

        List<MasterProducts> allProduct = productRepository.findAllByProductNameContainsIgnoreCase(keyword.getValue());

        for (MasterProducts product : allProduct) {
            displayProductsDTO.setProductName(product.getProductName());
            displayProductsDTO.setProductPrice(product.getProductPrice());
            displayProductsDTO.setProductDescription(product.getProductDescription());
            displayProductsDTO.setProductQuantity(product.getProductQuantity());

            displayLists.add(displayProductsDTO);
        }

        return new ApiResult<>(ApiResultEnums.PRODUCT_STORE_SUCCESS_DISPLAYED, displayLists);
    }
}
