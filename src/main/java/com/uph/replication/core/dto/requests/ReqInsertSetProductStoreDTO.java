package com.uph.replication.core.dto.requests;

import lombok.Data;

@Data
public class ReqInsertSetProductStoreDTO {
    private String storeByCategoryId;
    private String productByCategoryId;
}
