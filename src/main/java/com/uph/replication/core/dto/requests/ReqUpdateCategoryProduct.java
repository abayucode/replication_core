package com.uph.replication.core.dto.requests;

import lombok.Data;

import java.util.Date;

@Data
public class ReqUpdateCategoryProduct {
    private String categoryProductId;
    private String newCategoryName;
    private Date createdAt;
}
