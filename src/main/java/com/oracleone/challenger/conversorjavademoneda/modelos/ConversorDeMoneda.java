package com.oracleone.challenger.conversorjavademoneda.modelos;

import java.util.HashMap;
import java.util.Map;

public class ConversorDeMoneda {

    private String result;
    private final Map<String, Double> conversion_rates = new HashMap<>();

    public String getResult() {
        return result;
    }

    public double realizarConversion (double monto, String codigoMonedaOrigen, String codigoMonedaDestino){
        if (!conversion_rates.containsKey(codigoMonedaOrigen))
            throw new RuntimeException("Verifique el código de la moneda origen : (" + codigoMonedaOrigen + "), es inválido");

        if (!conversion_rates.containsKey(codigoMonedaDestino))
            throw new RuntimeException("Verifique el código de la moneda destino : (" + codigoMonedaDestino + "), es inválido");

        return monto * conversion_rates.get(codigoMonedaDestino) / conversion_rates.get(codigoMonedaOrigen);
    }
}