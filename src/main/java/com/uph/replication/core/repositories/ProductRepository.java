package com.uph.replication.core.repositories;

import com.uph.replication.core.entities.MasterProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<MasterProducts, String> {

    Optional<MasterProducts> findByProductCode(String productCode);

}
