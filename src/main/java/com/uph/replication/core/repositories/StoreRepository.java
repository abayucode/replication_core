package com.uph.replication.core.repositories;

import com.uph.replication.core.entities.MasterStores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<MasterStores, String> {
}
