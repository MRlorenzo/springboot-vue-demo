package org.spmul.service.impl;

import org.spmul.common.base.BaseDao;
import org.spmul.dao.OrderDao;
import org.spmul.entity.OrderEntity;
import org.spmul.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orderService")
public class OrderServiceImpl extends BaseServiceImpl<OrderEntity> implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public BaseDao<OrderEntity> getBaseDao() {
        return orderDao;
    }
}
