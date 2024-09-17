package com.uph.replication.core.dto.responses;

import com.uph.replication.core.enums.StoreEnums;
import lombok.Data;

@Data
public class ReqRespUpdateStore {
    private String storeName;
    private String latitude;
    private String longitude;
    private String storeAddress;
    private StoreEnums storeIsActive;
    private String storeCode;
}
