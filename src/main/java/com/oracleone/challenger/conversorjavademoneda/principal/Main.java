package com.oracleone.challenger.conversorjavademoneda.principal;

import com.oracleone.challenger.conversorjavademoneda.modelos.ConversorDeMoneda;
import com.oracleone.challenger.conversorjavademoneda.modelos.TasasDeCambio;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ConversorDeMoneda conversorMonedas = TasasDeCambio.consultarTasasCambio();

        Scanner scanner = new Scanner(System.in);
        int opcion;
        double montoAConvertir;
        double montoConvertido;
        String[] codigosMonedas = new String[2];

        // Se realiza el bucle para conversión de monedas
        while (true){
            System.out.print("""
                    ************************************************
                    Sea bienvenido/a al Conversor de monedas =)
                    1. Dólar           ==> Peso argentino
                    2. Peso argentino  ==> Dólar
                    3. Dólar           ==> Real brasileño
                    4. Real Brasileño  ==> Dólar
                    5. Dólar           ==> Peso colombiano
                    6. Peso colombiano ==> Dólar
                    7. Salir (U otro número inválido)
                    ************************************************
                    """);

            try {
                System.out.print("Elige una opción válida: ");
                opcion = Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar una opción válida :(, corrija");
                continue;
            }

            if (opcion < 1 || opcion >=7 ) {
                System.out.println("\nGracias por utilizar el conversor de monedas\n");
                break;
            }

            try {
                System.out.print("Ingresa el valor que deseas convertir: ");
                montoAConvertir = Double.parseDouble(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un monto válido :(, corrija");
                continue;
            }

            switch (opcion) {
                case 1 -> {
                    codigosMonedas = new String[]{"USD", "ARS"};
                }
                case 2 -> {
                    codigosMonedas = new String[]{"ARS", "USD"};
                }
                case 3 -> {
                    codigosMonedas = new String[]{"USD", "BRL"};
                }
                case 4 -> {
                    codigosMonedas = new String[]{"BRL", "USD"};
                }
                case 5 -> {
                    codigosMonedas = new String[]{"USD", "COP"};
                }
                case 6 -> {
                    codigosMonedas = new String[]{"COP", "USD"};
                }
            }

            try {
                montoConvertido = conversorMonedas.realizarConversion(montoAConvertir, codigosMonedas[0], codigosMonedas[1]);
            } catch (Exception e) {
                System.out.println("Valide los códigos de las monedas\n" + e.getMessage());
                continue;
            }

            System.out.println("\n*** El valor " + montoAConvertir + " [" + codigosMonedas[0] + "] " +
                    "corresponde al valor final de ==>> " + montoConvertido +  " [" + codigosMonedas[1] + "] ***\n");
        }

        System.out.println("Gracias por utilizar el conversor de monedas");
    }
}