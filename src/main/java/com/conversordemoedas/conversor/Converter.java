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
                // Registra a conversão bem sucedida em record.db
                record.registerLog(currency + " > " + targetCurrency, valor, finalValue );
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao converter a moeda.");
        }
    }

}
