package com.seokho.cryptodashboard.model.crypto;

public record CryptoPriceResponse(
        String unit,
        Double price
) {
}
