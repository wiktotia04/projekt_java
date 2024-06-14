package org.example;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class ApiClient {
    public static void main(String[] args) {
        String apiUrl = "https://openexchangerates.org/api/latest.json?app_id=facffc6984e348ce9569adc7c1ddaa7c";
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(ApiClient::deserializeJson) // Deserializujemy JSON
                .thenAccept(ApiClient::saveToDatabase) // Zapisujemy do bazy danych
                .join();
    }

    private static Deserialized deserializeJson(String responseBody) {
        Gson gson = new Gson();
        return gson.fromJson(responseBody, Deserialized.class);
    }

    private static void saveToDatabase(Deserialized deserialized) {
        DataBaseController.createTable();

        for (Map.Entry<String, Float> entry : deserialized.getRates().entrySet()) {
            info_walut info = new info_walut();
            info.setWalutaDb(entry.getKey());
            info.setWartoscDb(entry.getValue());

            DataBaseController.saveInfoWalut(info);
        }
    }
}
