package com.uph.replication.core.services;

import com.uph.replication.core.entities.MasterCategoryStore;
import com.uph.replication.core.entities.MasterStores;

public interface StoreByCategoryService {

    void insert(MasterCategoryStore categoryStore, MasterStores stores);
    MasterCategoryStore getByName(String categoryName);

}
