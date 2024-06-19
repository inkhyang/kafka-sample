package com.inkhyang.commerce.application;

import com.inkhyang.base.dto.order.ProductDto;
import com.inkhyang.base.dto.user.VerificatedUserDto;
import com.inkhyang.commerce.domain.CashBox;

public interface ControllerService {
    void sell(ProductDto productDto, VerificatedUserDto userDto);
    void validateUser(VerificatedUserDto userDto);
    CashBox regProfit(CashBox cashBox);
}
