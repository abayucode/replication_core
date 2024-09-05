package com.uph.replication.core.entities;

import com.uph.replication.core.constants.EntityConstants;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity(name = EntityConstants.TBL_PRODUCT_BY_CATEGORY)
public class ProductsByCategories {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = EntityConstants.PRODUCT_ID)
    private MasterProducts products;

    @ManyToOne
    @JoinColumn(name = EntityConstants.CATEGORY_PRODUCT_ID)
    private MasterCategoryProducts categoryProducts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = EntityConstants.PRODUCT_BY_CATEGORIES, cascade = CascadeType.ALL)
    private List<SetProductsStore> setProductsStores;
}
