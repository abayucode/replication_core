package com.uph.replication.core.services;

import com.uph.replication.core.dto.requests.ReqInsertStoreDTO;
import com.uph.replication.core.dto.ApiResult;
import com.uph.replication.core.dto.ReqRespUpdateStore;
import com.uph.replication.core.entities.MasterCategoryStore;
import com.uph.replication.core.entities.MasterStores;
import com.uph.replication.core.enums.ApiResultEnums;
import com.uph.replication.core.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Validated
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
            masterStores.setLatitude(reqInsertStoreDTO.getLatitude());
            masterStores.setLongitude(reqInsertStoreDTO.getLongitude());
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

    @Override
    public ApiResult<ReqRespUpdateStore> updateStore(ReqRespUpdateStore reqRespUpdateStore) {
        ReqRespUpdateStore reqUpdateStore = new ReqRespUpdateStore();
        try {
            MasterStores stores = storeRepository.findMasterStoresByStoreCode(reqRespUpdateStore.getStoreCode()).get();
            stores.setStoreName(reqRespUpdateStore.getStoreName());
            stores.setStoreAddress(reqRespUpdateStore.getStoreAddress());
            stores.setLatitude(reqRespUpdateStore.getLatitude());
            stores.setLongitude(reqRespUpdateStore.getLongitude());
            stores.setUpdatedAt(new Date());

            storeRepository.save(stores);

            reqUpdateStore.setStoreName(stores.getStoreName());
            reqUpdateStore.setStoreCode(stores.getStoreCode());
            reqUpdateStore.setLatitude(stores.getLatitude());
            reqUpdateStore.setLongitude(stores.getLongitude());
            reqUpdateStore.setStoreAddress(stores.getStoreAddress());
            reqUpdateStore.setStoreIsActive(stores.getStoreIsActive());


            return new ApiResult<>(ApiResultEnums.SUCCESS_UPDATED, reqUpdateStore);
        } catch (Exception e) {
            return new ApiResult<>(ApiResultEnums.ERROR_UPDATED, reqRespUpdateStore);
        }
    }
}
