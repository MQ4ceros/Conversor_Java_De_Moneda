package com.oracleone.challenger.conversorjavademoneda.modelos;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TasasDeCambio {

    public static ConversorDeMoneda consultarTasasCambio () {
        URI url = URI.create("https://v6.exchangerate-api.com/v6/31b79b39c8413f98c267db6f/latest/USD");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();

            Gson gson = new Gson();
            ConversorDeMoneda conversorMonedas = gson.fromJson(json, ConversorDeMoneda.class);

            if (!conversorMonedas.getResult().equalsIgnoreCase("success"))
                throw new RuntimeException("La ejecución de la API no fue exitosa, valide la respuesta del body: \n" + json);

            return conversorMonedas;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error en ejecución de API, no se puede continuar con la ejecución del programa");
        }
    }
}