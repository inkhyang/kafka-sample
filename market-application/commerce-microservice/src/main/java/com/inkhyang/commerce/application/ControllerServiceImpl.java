package com.inkhyang.commerce.application;

import com.inkhyang.base.dto.order.ProductDto;
import com.inkhyang.base.dto.user.VerificatedUserDto;
import com.inkhyang.commerce.domain.CashBox;
import org.springframework.stereotype.Service;

@Service
public class ControllerServiceImpl implements ControllerService {
    @Override
    public void sell(ProductDto productDto, VerificatedUserDto userDto) {
        if(productDto.adult()) {
            validateUser(userDto);
        }
    }


    @Override
    public void validateUser(VerificatedUserDto userDto) {
        int age = Integer.parseInt(userDto.idCard()
                .split("-")[0]);
        if(age < 17) {
            throw new UnsupportedOperationException("too young");
        }
    }

    @Override
    public CashBox regProfit(CashBox cashBox) {
        //todo
        return cashBox;
    }
}
