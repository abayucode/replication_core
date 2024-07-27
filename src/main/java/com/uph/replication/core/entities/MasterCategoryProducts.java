package com.uph.replication.core.entities;

import com.uph.replication.core.constants.EntityConstants;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity(name = EntityConstants.MST_CATEGORY_PRODUCTS)
public class MasterCategoryProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String categoryName;
    private Date createdAt;
    private Date deletedAt;
    private Date updatedAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = EntityConstants.CATEGORY_PRODUCTS, cascade = CascadeType.ALL)
    private List<ProductsByCategories> productsByCategories;
}
