package com.uph.replication.core.services;

import com.uph.replication.core.entities.ProductsByCategories;
import com.uph.replication.core.repositories.ProductByCategoryRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductByCategoryServiceImpl implements ProductByCategoryService {

    @Resource
    ProductByCategoryRepository productByCategoryRepository;

    @Override
    public List<ProductsByCategories> getAllProductsByCategory() {
        return productByCategoryRepository.findAll();
    }
}
