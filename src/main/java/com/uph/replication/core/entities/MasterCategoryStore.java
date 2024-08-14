package com.uph.replication.core.entities;

import com.uph.replication.core.constants.EntityConstants;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity(name = EntityConstants.MST_CATEGORY_STORE)
@Data
public class MasterCategoryStore {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String storeCategoryName;
    private Date createAt;
    private Date deletedAt;
    private Date updatedAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = EntityConstants.CATEGORY_STORE, cascade = CascadeType.ALL)
    private List<StoresByCategories> storesByCategories;
}
