package com.seokho.currencymonitor.service;

import com.seokho.currencymonitor.model.exchange.ExchangeResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

@Service
public class ExchangeService {

    private static final RestClient restClient = RestClient.create(); //외부api를 호출할수 있게끔 만들어주는 RestClient

    private final String apiUri;
    private final String authKey;

    //@Value 외부설정값(yaml에서 설정한값)을 매개변수에 대입해준다.
    public ExchangeService(
            @Value("${kexim.api-uri}") String apiUri,
            @Value("${kexim.auth-key}") String authKey
    ){
        this.apiUri = apiUri;
        this.authKey = authKey;
    }

    public ExchangeResponse getExchangeByCurrency(String currency){
        var exchangesResponse =
                restClient
                .get()
                .uri(apiUri + authKey)
                .retrieve()
                .body(ExchangeResponse[].class); //json이 여러개여서 []로 array(배열)로 받음

        if (exchangesResponse == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return Arrays.stream(exchangesResponse)
                .filter(exchangeResponse -> exchangeResponse.cur_unit().equals(currency.toUpperCase())) //cur_unit 이게 currency와 같은것만 찾을것.
                .findFirst()
                .orElse(new ExchangeResponse("USD","미국달러","2000"));

    }



}
