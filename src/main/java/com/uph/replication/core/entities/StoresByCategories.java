package com.uph.replication.core.entities;

import com.uph.replication.core.constants.EntityConstants;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = EntityConstants.TBL_STORE_BY_CATEGORY)
@Data
public class StoresByCategories {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = EntityConstants.STORE_ID)
    private MasterStores stores;

    @ManyToOne
    @JoinColumn(name = EntityConstants.STORE_CATEGORY_ID)
    private MasterCategoryStore categoryStore;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = EntityConstants.STORE_BY_CATEGORIES, cascade = CascadeType.ALL)
    private List<SetProductsStore> setProductsStores;
}
