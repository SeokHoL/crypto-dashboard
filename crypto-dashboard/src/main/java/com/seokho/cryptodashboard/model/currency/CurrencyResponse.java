package com.seokho.cryptodashboard.model.currency;

public record CurrencyResponse(
        String unit,
        String name,
        Double rate) {}
