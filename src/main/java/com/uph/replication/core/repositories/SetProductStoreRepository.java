package com.uph.replication.core.repositories;

import com.uph.replication.core.entities.SetProductsStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetProductStoreRepository extends JpaRepository<SetProductsStore, String> {

}
