package org.spmul.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 随便定义的一个实体类，为了测试一些接口
 */
@Data
public class OrderEntity {

    private Long id;

    private String orderNo;

    private BigDecimal price;

    private String status;

    private String username;

}
