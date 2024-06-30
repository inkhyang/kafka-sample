package com.inkhyang.base.dto.order;

import com.inkhyang.base.dto.user.VerificatedUserDto;

import java.util.List;

public class Order {
    private VerificatedUserDto userDto;
    private List<ProductDto> products;
    private String status;

    public Order() {
    }

    public Order(VerificatedUserDto userDto, List<ProductDto> products, String status) {
        this.userDto = userDto;
        this.products = products;
        this.status = status;
    }

    public VerificatedUserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(VerificatedUserDto userDto) {
        this.userDto = userDto;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return '\'' + "Order{" +
                "user=" + userDto.toString() +
                ", products=" + products.toString() +
                ", status='" + status + '\'' +
                '}';
    }
}
