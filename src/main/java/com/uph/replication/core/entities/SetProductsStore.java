package com.uph.replication.core.entities;

import com.uph.replication.core.constants.EntityConstants;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Entity(name = EntityConstants.TBL_SET_PRODUCTS_STORES)
@Data
public class SetProductsStore {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = EntityConstants.STORE_BY_CATEGORY_ID)
    private StoresByCategories storesByCategories;

    @ManyToOne
    @JoinColumn(name = EntityConstants.PRODUCT_BY_CATEGORY_ID)
    private ProductsByCategories productsByCategories;

    private Date createdAt;
    private Date deletedAt;
    private Date updatedAt;
}
