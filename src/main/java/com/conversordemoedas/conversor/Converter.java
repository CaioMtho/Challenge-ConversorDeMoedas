package com.conversordemoedas.conversor;

import org.json.JSONObject;

public class Converter {
    private static final String apiKey = "629f331637b5e64d9abf4b2e";
    private final JSONObject exchangeRates;
    private String currency;

    public Converter(String currency) {
        this.currency = currency;
        ExchangeRateConnect exchangeRateConnect = new ExchangeRateConnect(apiKey);
        exchangeRates = exchangeRateConnect.getExchangeRate(currency);
    }



    public void convertCurrency(String targetCurrency, double valor) {
        Record record = new Record();
        try {
                double rate = exchangeRates.getDouble(targetCurrency);
                double finalValue = rate * valor;
                System.out.println(currency + " = " + valor + "\t\t" + targetCurrency + " = " + finalValue);
                record.registerLog(currency + " > " + targetCurrency, valor, finalValue );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERRO: Nao foi possivel converter a moeda.");
        }
    }

}
