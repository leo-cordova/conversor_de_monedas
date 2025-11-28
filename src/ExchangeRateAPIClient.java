import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

public class ExchangeRateAPIClient {
    private static final String CLAVE_API;

    static {
        Properties propiedades = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            propiedades.load(fis);
            CLAVE_API = propiedades.getProperty("api.key");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public TasaDeCambio resultadoDeConversion (String baseCode, String targetCode, double amount) throws IOException, InterruptedException {
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/" + CLAVE_API + "/pair/" + baseCode + "/" + targetCode + "/" + amount);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        HttpResponse<String> respuesta = client
                .send(solicitud, HttpResponse.BodyHandlers.ofString());

        return new Gson().fromJson(respuesta.body(), TasaDeCambio.class);
    }
}
