package com.uph.replication.core.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReqRespUpdateProduct {
    private String productCode;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private Integer productQuantity;
}
