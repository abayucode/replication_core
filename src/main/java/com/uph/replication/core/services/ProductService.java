package com.uph.replication.core.services;

import com.uph.replication.core.dto.requests.ReqInsertProductDTO;
import com.uph.replication.core.dto.ApiResult;
import com.uph.replication.core.dto.ReqRespUpdateProduct;
import com.uph.replication.core.entities.MasterProducts;

public interface ProductService {
    ApiResult<Object> insertNewProduct(ReqInsertProductDTO reqInsertProductDTO);
    ApiResult<ReqRespUpdateProduct> updateProduct(ReqRespUpdateProduct reqRespUpdateProduct);
    Boolean isSameData(ReqRespUpdateProduct reqRespUpdateProduct, MasterProducts masterProducts);
}
