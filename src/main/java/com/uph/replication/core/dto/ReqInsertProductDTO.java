package com.uph.replication.core.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReqInsertProductDTO {
    private String productName;
    private Integer stock;
    private Date productExpiryDate;
    private String productCode;
    private String productCategory;
}
