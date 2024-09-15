package com.uph.replication.core.services;

import com.uph.replication.core.dto.ApiResult;
import com.uph.replication.core.dto.requests.ReqInsertSetProductStoreDTO;
import com.uph.replication.core.dto.responses.RespDisplayProductsDTO;
import com.uph.replication.core.enums.ApiResultEnums;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SetProductStoreServiceImpl implements SetProductStoreService {
    @Override
    public ApiResult<RespDisplayProductsDTO> insertSetProductStore(ReqInsertSetProductStoreDTO request) {
//        SetProductsStore setProductsStore = new SetProductsStore();
//        StoresByCategories storesByCategories = new StoresByCategories();
//        ProductsByCategories productsByCategories = new ProductsByCategories();
//
//        setProductsStore.setProductsByCategories(storesByCategories);
//        setProductsStore.setProductsByCategories();

        return null;
    }

    @Override
    public ApiResult<List<RespDisplayProductsDTO>> getProductByCategory() {
        List<RespDisplayProductsDTO> resultData = new ArrayList<>();
        return new ApiResult<>(ApiResultEnums.PRODUCT_STORE_SUCCESS_DISPLAYED, resultData);
    }
}
