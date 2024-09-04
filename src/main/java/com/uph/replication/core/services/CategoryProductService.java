package com.uph.replication.core.services;

import com.uph.replication.core.dto.responses.ApiResult;
import com.uph.replication.core.entities.MasterCategoryProducts;

public interface CategoryProductService {
    ApiResult<Object> insertNewCategoryProducts(Res masterCategoryProducts);
    MasterCategoryProducts getCategoryProductByProductName(String categoryName);
}
