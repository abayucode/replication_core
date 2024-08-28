package com.uph.replication.core.repositories;

import com.uph.replication.core.entities.MasterCategoryStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryStoreRepository extends JpaRepository<MasterCategoryStore, String> {

    Optional<MasterCategoryStore> findByStoreCategoryName(String name);
}
