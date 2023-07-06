package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

/**
 * Hello world!
 *
 */
public class App    
{
       public static void main(String[] args) {
        String clientId = "tu-client-id";
        String redirectUri = "tu-uri-de-redireccionamiento";
        String authorizationCode = "tu-codigo-de-autorizacion";

        // Obtener el token de acceso
        String accessToken = getAccessToken(clientId, redirectUri, authorizationCode).join();

        if (accessToken != null) {
            // Obtener el nombre del usuario
            String username = getUsername(accessToken).join();

            System.out.println("Nombre de usuario: " + username);
        } else {
            System.out.println("No se pudo obtener el token de acceso");
        }
    }

    private static CompletableFuture<String> getAccessToken(String clientId, String redirectUri, String authorizationCode) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.instagram.com/oauth/access_token"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString("client_id=" + clientId +
                        "&redirect_uri=" + redirectUri +
                        "&code=" + authorizationCode +
                        "&grant_type=authorization_code"))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(t->t.body())
                .thenApply(InstagramAPIConnection::extractAccessToken);
    }

    /**
     * @param accessToken
     * @return
     */
    private static CompletableFuture<String> getUsername(String accessToken) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://graph.instagram.com/me?fields=username&access_token=" + accessToken))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(t->t.body())
                .thenApply(InstagramAPIConnection::extractUsername);
    }

    private static String extractAccessToken(String response) {
        JSONObject jsonObject = new JSONObject(response);
        return jsonObject.getString("access_token");
    }

    private static String extractUsername(String response) {
        JSONObject jsonObject = new JSONObject(response);
        return jsonObject.getString("username");
    }
}
