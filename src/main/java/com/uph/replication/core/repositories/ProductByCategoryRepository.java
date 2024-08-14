package com.uph.replication.core.repositories;

import com.uph.replication.core.entities.ProductsByCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductByCategoryRepository extends JpaRepository<ProductsByCategories, String> {
}
