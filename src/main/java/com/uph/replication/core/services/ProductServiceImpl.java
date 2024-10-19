package com.uph.replication.core.services;

import com.uph.replication.core.dto.requests.ReqInsertProductDTO;
import com.uph.replication.core.dto.ApiResult;
import com.uph.replication.core.dto.ReqRespUpdateProduct;
import com.uph.replication.core.entities.*;
import com.uph.replication.core.enums.ApiResultEnums;
import com.uph.replication.core.repositories.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryProductService categoryProductService;

    @Autowired
    private ProductByCategoryRepository productByCategoryRepository;

    @Autowired
    private SetProductStoreRepository setProductStoreRepository;

    @Autowired
    private StoreByCategoryRepository storeByCategoryRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public ApiResult<Object> insertNewProduct(ReqInsertProductDTO reqInsertProductDTO) {
        MasterProducts masterProducts = new MasterProducts();
        MasterCategoryProducts categoryProducts = categoryProductService.findByCategoryName(reqInsertProductDTO.getProductCategory());

        MasterStores masterStores = storeRepository.findById(reqInsertProductDTO.getStoreId()).get();
        ProductsByCategories productsByCategories = new ProductsByCategories();
        StoresByCategories storesByCategories = storeByCategoryRepository.findByStores(masterStores);

        SetProductsStore setProductsStore = new SetProductsStore();

        if (null == categoryProducts) {
            return new ApiResult<>(ApiResultEnums.CATEGORY_PRODUCT_IS_NOT_FOUND, null);
        }

        List<MasterProducts> products = productRepository.findAll();
        for (MasterProducts product: products) {
            if (reqInsertProductDTO.getProductCode().equals(product.getProductCode())) {
                return new ApiResult<>(ApiResultEnums.PRODUCT_CODE_IS_EXIST, product.getProductCode());
            }
        }

        /*
        * save into mst_products table
        * */
        masterProducts.setProductName(reqInsertProductDTO.getProductName());
        masterProducts.setProductCode(reqInsertProductDTO.getProductCode());
        masterProducts.setProductQuantity(reqInsertProductDTO.getProductQuantity());
        masterProducts.setProductExpired(reqInsertProductDTO.getProductExpiryDate());
        masterProducts.setProductDescription(reqInsertProductDTO.getProductDescription());
        masterProducts.setProductSize(reqInsertProductDTO.getProductSize());
        masterProducts.setProductUnitSize(reqInsertProductDTO.getProductUnitSize());
        masterProducts.setProductPrice(reqInsertProductDTO.getProductPrice());
        masterProducts.setCreatedAt(new Date());
        masterProducts.setUpdatedAt(new Date());
        masterProducts.setDeletedAt(null);

        productRepository.save(masterProducts);


        /*
        * save into tbl_product_by_category table
        * */
        productsByCategories.setProducts(masterProducts);
        productsByCategories.setCategoryProducts(categoryProducts);

        productByCategoryRepository.save(productsByCategories);


        /*
        * save into tbl_set_products_stores table
        * */
        setProductsStore.setProductsByCategories(productsByCategories);
        setProductsStore.setStoresByCategories(storesByCategories);
        setProductsStore.setCreatedAt(new Date());
        setProductsStore.setUpdatedAt(new Date());
        setProductStoreRepository.save(setProductsStore);


        Map<String, String> response = new HashMap<>();
        response.put("productId", masterProducts.getId());

        return new ApiResult<>(ApiResultEnums.PRODUCT_SUCCESS_ADDED, response);
    }

    @Override
    public ApiResult<ReqRespUpdateProduct> updateProduct(ReqRespUpdateProduct reqUpdateProduct) {
        ReqRespUpdateProduct reqRespUpdateProduct = new ReqRespUpdateProduct();

        if (productRepository.findByProductCode(reqUpdateProduct.getProductCode()).isPresent()) {
            MasterProducts masterProducts = productRepository.findByProductCode(reqUpdateProduct.getProductCode()).get();
            masterProducts.setProductName(reqUpdateProduct.getProductName());
            masterProducts.setProductQuantity(reqUpdateProduct.getProductQuantity());
            masterProducts.setProductDescription(reqUpdateProduct.getProductDescription());
            masterProducts.setProductPrice(reqUpdateProduct.getProductPrice());
            masterProducts.setUpdatedAt(new Date());
            productRepository.save(masterProducts);

            reqRespUpdateProduct.setProductCode(masterProducts.getProductCode());
            reqRespUpdateProduct.setProductName(masterProducts.getProductName());
            reqRespUpdateProduct.setProductQuantity(masterProducts.getProductQuantity());
            reqRespUpdateProduct.setProductPrice(masterProducts.getProductPrice());
            reqRespUpdateProduct.setProductDescription(masterProducts.getProductDescription());
            return new ApiResult<>(ApiResultEnums.SUCCESS_UPDATED, reqRespUpdateProduct);
        }

        return new ApiResult<>(ApiResultEnums.ERROR_UPDATED, reqRespUpdateProduct);
    }
}
