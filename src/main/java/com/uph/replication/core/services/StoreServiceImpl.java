package com.uph.replication.core.services;

import com.uph.replication.core.dto.requests.ReqInsertStoreDTO;
import com.uph.replication.core.dto.ApiResult;
import com.uph.replication.core.entities.MasterCategoryStore;
import com.uph.replication.core.entities.MasterStores;
import com.uph.replication.core.enums.ApiResultEnums;
import com.uph.replication.core.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    CategoryStoreService categoryStoreService;

    @Autowired
    StoreByCategoryService storeByCategoryService;

    @Override
    public ApiResult<Object> insertNewStore(ReqInsertStoreDTO reqInsertStoreDTO) {

        MasterCategoryStore categoryStore = categoryStoreService.getByName(reqInsertStoreDTO.getStoreCategory());
        MasterStores masterStores = new MasterStores();
        Map<String, String > response = new HashMap<>();

        if (isStoreExist(reqInsertStoreDTO.getStoreCode())) {
            return new ApiResult<>(ApiResultEnums.ERROR, "Store already exists");
        }

        if (null != categoryStore) {
            masterStores.setStoreName(reqInsertStoreDTO.getStoreName());
            masterStores.setStoreAddress(reqInsertStoreDTO.getStoreName());
            masterStores.setStoreAddress(reqInsertStoreDTO.getStoreAddress());
            masterStores.setStoreCode(reqInsertStoreDTO.getStoreCode());
            masterStores.setStoreIsActive(reqInsertStoreDTO.getStoreIsActive());
            masterStores.setCreatedAt(new Date());
            masterStores.setUpdatedAt(new Date());

            storeRepository.save(masterStores);
            storeByCategoryService.insert(categoryStore, masterStores);

            response.put("storeId", masterStores.getId());

        } else {
            return new ApiResult<>(ApiResultEnums.STORE_ADDED_FAILED, null);
        }
        return new ApiResult<>(ApiResultEnums.STORE_ADDED_SUCCESS, response);
    }

    @Override
    public Boolean isStoreExist(String storeCode) {
        return storeRepository.findMasterStoresByStoreCode(storeCode).isPresent();
    }
}
