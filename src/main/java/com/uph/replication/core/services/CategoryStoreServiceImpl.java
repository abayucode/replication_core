package com.uph.replication.core.services;

import com.uph.replication.core.dto.ReqInsertCategoryStoreDTO;
import com.uph.replication.core.dto.responses.ApiResult;
import com.uph.replication.core.entities.MasterCategoryStore;
import com.uph.replication.core.enums.ApiResultEnums;
import com.uph.replication.core.repositories.CategoryStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CategoryStoreServiceImpl implements CategoryStoreService {

    @Autowired
    private CategoryStoreRepository categoryStoreRepository;

    @Override
    public ApiResult<Object> insertCategory(ReqInsertCategoryStoreDTO dto) {
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
}
