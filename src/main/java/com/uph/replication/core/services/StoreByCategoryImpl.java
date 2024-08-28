package com.uph.replication.core.services;

import com.uph.replication.core.entities.MasterCategoryStore;
import com.uph.replication.core.entities.MasterStores;
import com.uph.replication.core.entities.StoresByCategories;
import com.uph.replication.core.repositories.StoreByCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreByCategoryImpl implements StoreByCategoryService {

    @Autowired
    StoreByCategoryRepository storeByCategoryRepository;

    @Autowired
    StoreService storeService;

    @Override
    public void insert(MasterCategoryStore categoryStore, MasterStores stores) {
        StoresByCategories storesByCategories = new StoresByCategories();
        storesByCategories.setCategoryStore(categoryStore);
        storesByCategories.setStores(stores);

        storeByCategoryRepository.save(storesByCategories);
    }

    @Override
    public MasterCategoryStore getByName(String categoryName) {
        return storeByCategoryRepository.findById(categoryName).get().getCategoryStore();
    }
}
