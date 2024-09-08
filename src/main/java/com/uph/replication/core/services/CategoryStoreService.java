package com.uph.replication.core.services;

import com.uph.replication.core.dto.ReqInsertUpdateCategoryStoreDTO;
import com.uph.replication.core.dto.responses.ApiResult;
import com.uph.replication.core.entities.MasterCategoryStore;

public interface CategoryStoreService {
    ApiResult<Object> insertCategory(ReqInsertUpdateCategoryStoreDTO dto);
    MasterCategoryStore getByName(String name);
    ApiResult<Object> getAllCategoryProduct();
    ApiResult<Object> updateCategoryStore(ReqInsertUpdateCategoryStoreDTO dto);
    ApiResult<Object> deleteCategoryStore(String categoryStoreName);
}
