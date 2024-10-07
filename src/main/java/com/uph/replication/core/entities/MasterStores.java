package com.uph.replication.core.entities;

import com.uph.replication.core.constants.EntityConstants;
import com.uph.replication.core.enums.StoreEnums;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity(name = EntityConstants.MST_STORE)
@Data
public class MasterStores {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    private String id;
    private String storeName;
    private String storeAddress;
    private StoreEnums storeIsActive;
    private String storeCode;
    private String latitude;
    private String longitude;
    private String linkLocation;
    private Date createdAt;
    private Date deletedAt;
    private Date updatedAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = EntityConstants.STORES, cascade = CascadeType.ALL)
    private List<StoresByCategories> storesByCategories;
}
