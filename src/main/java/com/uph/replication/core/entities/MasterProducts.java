package com.uph.replication.core.entities;

import com.uph.replication.core.constants.EntityConstants;
import com.uph.replication.core.enums.UnitSizeEnums;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Entity(name = EntityConstants.MST_PRODUCTS)
@NoArgsConstructor
public class MasterProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String productName;
    private Integer productQuantity;
    private Date productExpired;
    private String productCode;
    private BigDecimal productPrice;
    private String productDescription;
    private Double productSize;
    private UnitSizeEnums productUnitSize;
    private Date createdAt;
    private Date deletedAt;
    private Date updatedAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = EntityConstants.PRODUCTS, cascade = CascadeType.ALL)
    private List<ProductsByCategories> productsByCategories;
}
