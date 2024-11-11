package com.seokho.cryptodashboard.controller;

import com.seokho.cryptodashboard.model.crypto.CryptoPriceResponse;
import com.seokho.cryptodashboard.service.CurrencyMonitorService;
import com.seokho.cryptodashboard.service.PriceMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/crypto-prices")
public class CryptoPriceController {
    
    @Autowired private PriceMonitorService priceMonitorService;
    
    @Autowired private CurrencyMonitorService currencyMonitorService;
    
    @GetMapping("/{cryptoUnit}")
    public ResponseEntity<CryptoPriceResponse> getCryptoPrice(@PathVariable String cryptoUnit){
        var priceResponse =  priceMonitorService.getPrice(cryptoUnit); //가상자산 가격을 USD기준으로 가져옴
        var currencyResponse = currencyMonitorService.getCurrency("USD"); //USD를 KRW(원화)를 가져옴
        var cryptoPriceResponse = new CryptoPriceResponse(cryptoUnit, priceResponse.amount() * currencyResponse.rate());

        return  ResponseEntity.ok(cryptoPriceResponse);


    }
    
    
}
