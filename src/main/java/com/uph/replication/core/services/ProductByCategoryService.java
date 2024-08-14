package com.uph.replication.core.services;

import com.uph.replication.core.entities.ProductsByCategories;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductByCategoryService {
    List<ProductsByCategories> getAllProductsByCategory();
}
