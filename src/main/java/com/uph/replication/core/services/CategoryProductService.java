package com.uph.replication.core.services;

import com.uph.replication.core.dto.ReqInsertCategoryProduct;
import com.uph.replication.core.dto.ReqInsertProductDTO;
import com.uph.replication.core.dto.responses.ApiResult;
import com.uph.replication.core.entities.MasterCategoryProducts;

public interface CategoryProductService {
    ApiResult<Object> insertNewCategoryProducts(ReqInsertCategoryProduct request);
    Boolean isCategoryProductExist(String categoryName);
}
