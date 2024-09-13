package com.uph.replication.core.dto.requests;

import com.uph.replication.core.enums.UnitSizeEnums;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ReqInsertProductDTO {
    private String productName;
    private Integer productQuantity;
    private Date productExpiryDate;
    private String productCode;
    private String productCategory;
    private String productDescription;
    private Double productSize;
    private UnitSizeEnums productUnitSize;
    private BigDecimal productPrice;
}
