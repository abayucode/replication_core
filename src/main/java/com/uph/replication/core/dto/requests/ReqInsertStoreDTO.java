package com.uph.replication.core.dto.requests;

import com.uph.replication.core.enums.StoreEnums;
import lombok.Data;

@Data
public class ReqInsertStoreDTO {
    private String storeName;
    private String storeAddress;
    private StoreEnums storeIsActive;
    private String storeCode;
    private String storeCategory;
    private String latitude;
    private String longitude;
    private String linkLocation;
}
