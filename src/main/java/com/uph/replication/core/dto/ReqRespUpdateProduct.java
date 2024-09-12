package com.uph.replication.core.dto;

import lombok.Data;

@Data
public class ReqRespUpdateProduct {
    private String productId;
    private String productName;
    private String productDescription;
    private String productPrice;
    private String productQuantity;
}
