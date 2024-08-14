package com.uph.replication.core.services;

import com.uph.replication.core.dto.ReqInsertStoreDTO;
import com.uph.replication.core.entities.MasterStores;
import com.uph.replication.core.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    StoreRepository storeRepository;

    @Override
    public String insertNewStore(ReqInsertStoreDTO reqInsertStoreDTO) {
        MasterStores masterStores = new MasterStores();
        masterStores.setStoreName(reqInsertStoreDTO.getStoreName());
        masterStores.setStoreAddress(reqInsertStoreDTO.getStoreName());
        masterStores.setStoreAddress(reqInsertStoreDTO.getStoreAddress());
        masterStores.setStoreCode(reqInsertStoreDTO.getStoreCode());
        masterStores.setStoreIsActive(reqInsertStoreDTO.getStoreIsActive());
        masterStores.setCreatedAt(new Date());
        masterStores.setUpdatedAt(new Date());
        storeRepository.save(masterStores);
        return masterStores.getId();
    }
}
