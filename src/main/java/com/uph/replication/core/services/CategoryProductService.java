package com.uph.replication.core.services;

import com.uph.replication.core.dto.requests.ReqGetCategoryProductById;
import com.uph.replication.core.dto.requests.ReqInsertCategoryProduct;
import com.uph.replication.core.dto.ApiResult;
import com.uph.replication.core.dto.requests.ReqUpdateCategoryProduct;
import com.uph.replication.core.dto.responses.RespGetCategoryProducts;
import com.uph.replication.core.dto.responses.RespUpdateCategoryProduct;
import com.uph.replication.core.entities.MasterCategoryProducts;

import java.util.List;

public interface CategoryProductService {
    ApiResult<Object> insertNewCategoryProducts(ReqInsertCategoryProduct request);
    Boolean isCategoryProductExist(String categoryName);
    MasterCategoryProducts findByCategoryName(String categoryName);
    ApiResult<List<RespGetCategoryProducts>> findAllCategoryProduct();
    ApiResult<Object> deleteCategoryProduct(ReqGetCategoryProductById request);
    ApiResult<RespUpdateCategoryProduct> updateCategoryProduct(ReqUpdateCategoryProduct request);
}
