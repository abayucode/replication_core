package com.uph.replication.core.repositories;

import com.uph.replication.core.entities.MasterStores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<MasterStores, String> {
    Optional<MasterStores> findMasterStoresByStoreCode(String storeCode);
}
