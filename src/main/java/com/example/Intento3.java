package com.example;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Intento3 {
    public static void main(String[] args) {
        try {
            // URL de la API de visualización básica de Instagram
            String apiUrl = "https://api.instagram.com/";

            boolean isConnected = connectToAPI(apiUrl);

            if (isConnected) {
                System.out.println("Conexión exitosa a la API de Instagram.");
            } else {
                System.out.println("No se pudo conectar a la API de Instagram.");
            }

        } catch (IOException e) {
            System.out.println("Error al conectar a la API de Instagram: " + e.getMessage());
        }
    }

    private static boolean connectToAPI(String apiUrl) throws IOException {
        // Crear la conexión HTTP
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        // Obtener el código de respuesta
        int responseCode = conn.getResponseCode();

        // Cerrar la conexión
        conn.disconnect();

        // Verificar si la conexión fue exitosa (código de respuesta 200)
        return responseCode == 200;
    }
    
}
