package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class JSONObject {
    public static void main(String[] args) {
        String clientId = "727512385844188";
        String clientSecret = "fdb427c695fd6babae4e51a2a313cdf6";
        String redirectUri = "https://aracelypl.github.io/";
        String authorizationCode = "AQB2anyuBb_DAzL74dRNe0NolDoXguJ0M7SCL_1mjj-7zekIq_HtvOtekbTDDIbUxNWlsiGyCLCLtArreYdBWd6Ge8jdHvNlIyicjoRiv8Baf_0banvU_F5VP5N0tGyGD7pXpOLJMLsxtPezGTDw3AlfRFzAu6iPOfuWp8YOmlO5j1ZQRTBWa8AblV5Kx0nJxTacKuAWRSOeXx_YT3ic2znl4s1tzn0pg1oixLYmBZCQrg";

        String accessToken = getAccessToken(clientId, clientSecret, redirectUri, authorizationCode);

        if (accessToken != null) {

            String username = getUsername(accessToken);

            System.out.println("Nombre de usuario: " + username);
        } else {
            System.out.println("No se pudo obtener el token de acceso");
        }
    }

    public static String getAccessToken(String clientId, String clientSecret, String redirectUri, String authorizationCode) {
        try {
            // URL para obtener el token de acceso
            String tokenUrl = "https://api.instagram.com/oauth/access_token";

            // Construir los parámetros de la solicitud
            String parameters = "client_id=" + clientId +
                    "&client_secret=" + clientSecret +
                    "&grant_type=authorization_code" +
                    "&redirect_uri=" + redirectUri +
                    "&code=" + authorizationCode;

           
            URL url = new URL(tokenUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(parameters.getBytes());
            outputStream.flush();
            outputStream.close();

            
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Procesar la respuesta y obtener el token de acceso
            // Aquí debes implementar la lógica adecuada para extraer el token de acceso de la respuesta JSON

            return null; // Aquí debes retornar el token de acceso obtenido

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getUsername(String accessToken) {
        try {
            String apiUrl = "https://graph.instagram.com/me?fields=username&access_token=" + accessToken;

            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Obtener la respuesta de la solicitud
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Procesar la respuesta y obtener el nombre de usuario
            // Aquí debes implementar la lógica adecuada para extraer el nombre de usuario de la respuesta JSON

            return null; // Aquí debes retornar el nombre de usuario obtenido

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
