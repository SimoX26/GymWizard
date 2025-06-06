package ispwproject.gymwizard.controller.app;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.Properties;

public class PagamentoController {

    private final String CLIENT_ID;
    private final String CLIENT_SECRET;
    private final String BASE_URL;

    public PagamentoController() throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream("paypal.properties"));

        CLIENT_ID = props.getProperty("paypal.clientId");
        CLIENT_SECRET = props.getProperty("paypal.clientSecret");
        BASE_URL = props.getProperty("paypal.baseUrl");
    }

    private String getAccessToken() throws Exception {
        String auth = CLIENT_ID + ":" + CLIENT_SECRET;
        String encoded = Base64.getEncoder().encodeToString(auth.getBytes());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/v1/oauth2/token"))
                .header("Authorization", "Basic " + encoded)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString("grant_type=client_credentials"))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body().split("\"access_token\":\"")[1].split("\"")[0];
    }

    public String creaOrdine(int prezzoCentesimi) throws Exception {
        String token = getAccessToken();
        double prezzoEuro = prezzoCentesimi / 100.0;

        String body = """
        {
          "intent": "CAPTURE",
          "purchase_units": [{
            "amount": {
              "currency_code": "EUR",
              "value": "%s"
            }
          }],
          "application_context": {
            "return_url": "https://example.com/successo",
            "cancel_url": "https://example.com/annullato"
          }
        }
        """.formatted(String.format("%.2f", prezzoEuro).replace(",", "."));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/v2/checkout/orders"))
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        int index = response.body().indexOf("\"href\":\"https://www.sandbox.paypal.com/checkoutnow?");
        int end = response.body().indexOf("\"", index + 8);
        return response.body().substring(index + 8, end).replace("\\u0026", "&");
    }
}
