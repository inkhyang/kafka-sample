package com.inkhyang.payment.service;

import com.inkhyang.base.dto.order.Order;

public interface PaymentService {
    void payment(Order order);
}
