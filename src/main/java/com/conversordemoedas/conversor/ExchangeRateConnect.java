package com.conversordemoedas.conversor;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.List;

public class ExchangeRateConnect {
    private final String apiKey;

    public ExchangeRateConnect(String apiKey){
        this.apiKey = apiKey;
    }

    public JSONObject getExchangeRate(String currency){
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + currency;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                JSONObject jsonResponse = new JSONObject(result);
                return jsonResponse.getJSONObject("conversion_rates");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isValidIsoCode(String code) {
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/codes";
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                JSONObject jsonResponse = new JSONObject(result);
                return jsonResponse.getJSONArray("supported_codes")
                        .toList()
                        .stream()
                        .anyMatch(item -> ((List<?>) item).get(0).equals(code));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}
