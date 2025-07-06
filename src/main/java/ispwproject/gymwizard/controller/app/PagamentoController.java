package ispwproject.gymwizard.controller.app;

import ispwproject.gymwizard.util.exception.PagamentoInitException;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.Properties;


public class PagamentoController {

    private final String clientId;
    private final String clientSecret;
    private final String baseUrl;

    public PagamentoController() {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/paypal.properties")) {
            props.load(fis);
        } catch (IOException e) {
            throw new PagamentoInitException("Errore nella lettura del file paypal.properties. File mancante o corrotto.", e);
        }



        clientId = props.getProperty("paypal.clientId");
        clientSecret = props.getProperty("paypal.clientSecret");
        baseUrl = props.getProperty("paypal.baseUrl");

        if (clientId == null || clientSecret == null || baseUrl == null) {
            throw new PagamentoInitException("Parametri mancanti in paypal.properties");
        }
    }

    private String getAccessToken() {
        try {
            String auth = clientId + ":" + clientSecret;
            String encoded = Base64.getEncoder().encodeToString(auth.getBytes());

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl + "/v1/oauth2/token"))
                    .header("Authorization", "Basic " + encoded)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString("grant_type=client_credentials"))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body().split("\"access_token\":\"")[1].split("\"")[0];

        } catch (IOException e) {
            throw new PagamentoInitException("Errore durante il recupero dell'access token da PayPal.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // ✅ Re-interrompe il thread correttamente
            throw new PagamentoInitException("Il thread è stato interrotto durante il recupero del token.", e);
        }

    }

    public String creaOrdine(int prezzoCentesimi) {
        try {
            String token = getAccessToken();
            double prezzoEuro = prezzoCentesimi / 100.0;

            String body = """
            {
              "intent": "CAPTURE",
              "purchase_units": [ {
                "amount": {
                  "currency_code": "EUR",
                  "value": "%s"
                }
              } ],
              "application_context": {
                "return_url": "https://example.com/successo",
                "cancel_url": "https://example.com/annullato"
              }
            }
            """.formatted(String.format("%.2f", prezzoEuro).replace(",", "."));

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl + "/v2/checkout/orders"))
                    .header("Authorization", "Bearer " + token)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            int index = response.body().indexOf("\"href\":\"https://www.sandbox.paypal.com/checkoutnow?");
            if (index == -1) {
                throw new PagamentoInitException("URL di checkout PayPal non trovato nella risposta.");
            }

            int end = response.body().indexOf("\"", index + 8);
            return response.body().substring(index + 8, end).replace("\\u0026", "&");

        } catch (IOException e) {
            throw new PagamentoInitException("Errore nella creazione dell’ordine PayPal.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // ✅ Re-interrompe il thread correttamente
            throw new PagamentoInitException("Il thread è stato interrotto durante il recupero del token.", e);
        }

    }
}

