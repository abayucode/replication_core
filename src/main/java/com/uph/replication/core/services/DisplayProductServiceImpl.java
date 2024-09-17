package com.uph.replication.core.services;

import com.uph.replication.core.dto.ApiResult;
import com.uph.replication.core.dto.requests.ReqSearchProduct;
import com.uph.replication.core.dto.responses.RespDisplayProductsDTO;
import com.uph.replication.core.entities.*;
import com.uph.replication.core.enums.ApiResultEnums;
import com.uph.replication.core.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class DisplayProductServiceImpl implements DisplayProductStoreService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SetProductStoreRepository setProductStoreRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreByCategoryRepository storeByCategoryRepository;

    @Autowired
    private ProductByCategoryRepository productByCategoryRepository;

    @Override
    public ApiResult<List<RespDisplayProductsDTO>> displayProducts(ReqSearchProduct keyword) {
        List<RespDisplayProductsDTO> displayLists = new ArrayList<>();
        List<MasterProducts> allProduct = productRepository.findAllByProductNameContainsIgnoreCase(keyword.getValue());

        try {
            for (MasterProducts product : allProduct) {
                RespDisplayProductsDTO displayProductsDTO = new RespDisplayProductsDTO();
                ProductsByCategories productsByCategory = productByCategoryRepository.findAllByProducts(product);
                if (productsByCategory != null) {
                    SetProductsStore setProductsStore = setProductStoreRepository
                            .findSetProductsStoreByProductsByCategories(productsByCategory);
                    if (setProductsStore != null) {
                        StoresByCategories storesByCategories = storeByCategoryRepository
                                .findByStores(setProductsStore.getStoresByCategories().getStores());
                        MasterStores masterStores = storeRepository.findById(storesByCategories.getStores().getId()).get();
                        displayProductsDTO.setProductName(product.getProductName());
                        displayProductsDTO.setProductQuantity(product.getProductQuantity());
                        displayProductsDTO.setProductDescription(product.getProductDescription());
                        displayProductsDTO.setProductPrice(product.getProductPrice());
                        displayProductsDTO.setStoreName(masterStores.getStoreName());
                        displayProductsDTO.setLongitude(masterStores.getLongitude());
                        displayProductsDTO.setLatitude(masterStores.getLatitude());
                        displayProductsDTO.setExpired(product.getProductExpired());

                        displayLists.add(displayProductsDTO);
                    }

                }
            }

            return new ApiResult<>(ApiResultEnums.PRODUCT_STORE_SUCCESS_DISPLAYED, displayLists);
        } catch (Exception e) {
            return new ApiResult<>(ApiResultEnums.PRODUCT_STORE_NOT_FOUND, displayLists);
        }
    }

    @Override
    public ApiResult<List<SetProductsStore>> findAllProductsStore() {
        List<SetProductsStore> setProductsStores = setProductStoreRepository.findAll();
        return new ApiResult<>(ApiResultEnums.SUCCESS, setProductsStores);
    }
}
