package com.uph.replication.core.dto.responses;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class RespDisplayProductsDTO {
    private String productName;
    private Integer productQuantity;
    private BigDecimal productPrice;
    private String productDescription;
    private String storeName;
    private String latitude;
    private String longitude;
    private Date expired;
}
