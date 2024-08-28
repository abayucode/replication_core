package com.uph.replication.core.repositories;

import com.uph.replication.core.entities.StoresByCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreByCategoryRepository extends JpaRepository<StoresByCategories, String> {
}
