package com.inkhyang.storage.dto;

public record ProductDto(String name, String article, Integer availableAmt, Integer reservedAmt, Double price, Boolean adult) {
}
