package com.uph.replication.core.config;

import com.uph.replication.core.dto.ReqRespUpdateProduct;
import com.uph.replication.core.services.ProductService;
import com.uph.replication.core.utils.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqListener {

    private static final Logger log = LoggerFactory.getLogger(RabbitMqListener.class);

    @Autowired
    private ProductService productService;

    @RabbitListener(queues = "replication_core_rabbit")
    public void updateProduct(String message) {
        ReqRespUpdateProduct reqRespUpdateProduct = JSONUtils.jsonToObject(message, ReqRespUpdateProduct.class);
        log.info("success updated product by message : {}", message);
        productService.updateProduct(reqRespUpdateProduct);
    }
}
