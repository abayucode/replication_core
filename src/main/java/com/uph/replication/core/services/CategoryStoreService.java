package com.uph.replication.core.services;

import com.uph.replication.core.dto.ReqInsertCategoryStoreDTO;
import com.uph.replication.core.entities.MasterCategoryStore;

import java.util.Optional;

public interface CategoryStoreService {
    String insertCategory(ReqInsertCategoryStoreDTO dto);
    MasterCategoryStore getByName(String name);
}
