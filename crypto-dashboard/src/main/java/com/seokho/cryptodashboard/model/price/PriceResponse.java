package com.seokho.cryptodashboard.model.price;

public record PriceResponse(
        Double amount,
        String base,
        String currency) { }
