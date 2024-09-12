package com.uph.replication.core.repositories;

import com.uph.replication.core.entities.MasterCategoryProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryProductRepository extends JpaRepository<MasterCategoryProducts, String> {
    MasterCategoryProducts findMasterCategoryProductsByCategoryNameContainingIgnoreCase(String categoryName);
}
