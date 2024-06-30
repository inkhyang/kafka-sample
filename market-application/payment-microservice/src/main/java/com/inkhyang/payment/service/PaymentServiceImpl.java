package com.inkhyang.payment.service;

import com.inkhyang.base.dto.order.Order;
import com.inkhyang.base.dto.user.VerificatedUserDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private static final Logger LOG = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Override
    public void payment(Order order) {
        VerificatedUserDto userDto = order.getUserDto();
        LOG.info("Payment attempt: {}", order);
    }
}
