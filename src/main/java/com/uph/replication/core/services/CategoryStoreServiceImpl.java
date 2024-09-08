package com.uph.replication.core.services;

import com.uph.replication.core.dto.ReqInsertUpdateCategoryStoreDTO;
import com.uph.replication.core.dto.responses.ApiResult;
import com.uph.replication.core.dto.responses.RespGetAllCategoryStore;
import com.uph.replication.core.dto.responses.RespUpdateCategoryStore;
import com.uph.replication.core.entities.MasterCategoryStore;
import com.uph.replication.core.enums.ApiResultEnums;
import com.uph.replication.core.repositories.CategoryStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryStoreServiceImpl implements CategoryStoreService {

    @Autowired
    private CategoryStoreRepository categoryStoreRepository;

    @Override
    public ApiResult<Object> insertCategory(ReqInsertUpdateCategoryStoreDTO dto) {
        MasterCategoryStore masterCategoryStore = new MasterCategoryStore();
        masterCategoryStore.setStoreCategoryName(dto.getStoreCategoryName());
        masterCategoryStore.setCreateAt(new Date());
        masterCategoryStore.setDeletedAt(null);
        masterCategoryStore.setUpdatedAt(new Date());

        categoryStoreRepository.save(masterCategoryStore);

        Map<String, String> response = new HashMap<>();
        response.put("categoryStoreId", masterCategoryStore.getId());

        return new ApiResult<>(ApiResultEnums.STORE_CATEGORY_ADDED_SUCCESS, response);
    }

    @Override
    public MasterCategoryStore getByName(String name) {
        return categoryStoreRepository.findByStoreCategoryName(name).orElse(null);
    }

    @Override
    public ApiResult<Object> getAllCategoryProduct() {
        List<RespGetAllCategoryStore> categoryStores = new ArrayList<>();
        List<MasterCategoryStore> masterCategoryStores = categoryStoreRepository.findAll();

        for (MasterCategoryStore category : masterCategoryStores) {
            RespGetAllCategoryStore respGetAllCategoryStore = new RespGetAllCategoryStore();
            if (null != category.getStoreCategoryName()) {
                respGetAllCategoryStore.setCategoryName(category.getStoreCategoryName());
                categoryStores.add(respGetAllCategoryStore);
            }
        }

        return new ApiResult<>(ApiResultEnums.SUCCESS, categoryStores);
    }

    @Override
    public ApiResult<Object> updateCategoryStore(ReqInsertUpdateCategoryStoreDTO dto) {
        MasterCategoryStore masterCategoryStore = categoryStoreRepository.findById(dto.getIdCategoryStore()).get();

        masterCategoryStore.setStoreCategoryName(dto.getStoreCategoryName());

        categoryStoreRepository.save(masterCategoryStore);

        RespUpdateCategoryStore response = new RespUpdateCategoryStore();
        response.setStoreCategoryName(masterCategoryStore.getStoreCategoryName());

        return new ApiResult<>(ApiResultEnums.SUCCESS_UPDATED, response);
    }

    @Override
    public ApiResult<Object> deleteCategoryStore(String categoryStoreName) {
        MasterCategoryStore masterCategoryStore = this.getByName(categoryStoreName);
        if (null == masterCategoryStore) {
            return new ApiResult<>(ApiResultEnums.ERROR_DATA_NOT_FOUND, null);
        }

        categoryStoreRepository.delete(masterCategoryStore);

        return new ApiResult<>(ApiResultEnums.SUCCESS, null);
    }
}
