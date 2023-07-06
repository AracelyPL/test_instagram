package com.example;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class ConexionAlaAplicacion {
    public static void main(String[] args) {
        try {
            // Credenciales de la aplicación
            String clientId = "727512385844188";
            String clientSecret = "fdb427c695fd6babae4e51a2a313cdf6";

            // Realizar la conexión utilizando las credenciales
            boolean isConnected = connectToAPI(clientId, clientSecret);

            if (isConnected) {
                System.out.println("Conexión exitosa a la API de Instagram.");
            } else {
                System.out.println("No se pudo conectar a la API de Instagram.");
            }

        } catch (IOException e) {
            System.out.println("Error al conectar a la API de Instagram: " + e.getMessage());
        }
    }

    private static boolean connectToAPI(String clientId, String clientSecret) throws IOException {
        // Construir la URL de conexión
        String apiUrl = "https://api.instagram.com/";

        // Realizar la conexión HTTP
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        // Establecer las credenciales de cliente en el encabezado de autenticación
        String credentials = clientId + ":" + clientSecret;
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
        conn.setRequestProperty("Authorization", "Basic " + encodedCredentials);

        // Verificar si la conexión fue exitosa (código de respuesta 200)
        return conn.getResponseCode() == 200;
    }

    public boolean nombreusuario() throws IOException{
        String usuaString = "https://graph.instagram.com/9560229894019604?fields=id,username&access_token=IGQVJWcnFOYS0xbFNHaUZAOa0t5elA4RXEtcHBoc29SWGtHWkdkSXpPTTVWSVpSMjh4dnRONDZA1MTlTdVVlLWk0MmZAtdENRLW1qbEw3c25RUXZAsN0NpTDQ4YVFydlkwYjJVc1J3blEtMlBtN0wtaEJPai1PMndOT08yS3hN";

        URL ur = new URL(usuaString);
        HttpURLConnection conn = (HttpURLConnection) ur.openConnection();
        conn.setRequestMethod("GET");

        return conn.getResponseCode() == 200;

    }
}
