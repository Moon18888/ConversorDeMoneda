import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) throws IOException, InterruptedException {
        ConsultarMoneda consulta = new ConsultarMoneda();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .create();

        ConversionHistory history = new ConversionHistory();

        String menu = """
                ** Escriba el número de la opción deseada **
                
                1) Dólar >>>>>> Real brasileño
                2) Real brasileño >>>>>> Dólar
                3) Dólar >>>>>> Peso Colombiano
                4) Peso Colombiano >>>>>> Dólar
                5) Dólar >>>>>> Peso Mexicano
                6) Peso Mexicano >>>>>>  Dólar
                7) Dólar >>>>>> Peso Argentino
                8) Peso Argentino >>>>>> Dólar
                9) Ver historial de conversiones
                0) Salir
                
                """;
        System.out.println("Bienvenido/a al conversor de Monedas by Alfredo");

        Scanner teclado = new Scanner(System.in);

        while (true) {
            System.out.println(menu);
            int opcion = 0;

            try {
                opcion = teclado.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Opción no válida. Por favor, ingrese un número.");
                teclado.next();
                continue;
            }

            if (opcion == 0) {
                System.out.println("Finalizando el programa. Muchas gracias por usar el servicio de conversión de monedas.");
                break;
            }

            if (opcion == 9) {
                history.printHistory();
                continue;
            }

            if (opcion < 0 || opcion > 9) {
                System.out.println("Opción invalida");
                continue;
            }

            double cantidad = 0;
            System.out.println("Ingresa una cantidad que deseas convertir: ");
            try {
                cantidad = teclado.nextDouble();
                if (cantidad > 9999999) {
                    System.out.println("Cantidad demasiado grande. Por favor ingresa una cifra de maximo 7 dígitos.");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("¡Entrada invalida!. Por favor, ingrese un número.");
                teclado.next();
                continue;
            }

            String base = "";
            String target = "";

            switch (opcion) {
                case 1:
                    base = "USD";
                    target = "BRL";
                    break;
                case 2:
                    base = "BRL";
                    target = "USD";
                    break;
                case 3:
                    base = "USD";
                    target = "COP";
                    break;
                case 4:
                    base = "COP";
                    target = "USD";
                    break;
                case 5:
                    base = "USD";
                    target = "MXN";
                    break;
                case 6:
                    base = "MXN";
                    target = "USD";
                    break;
                case 7:
                    base = "USD";
                    target = "ARS";
                    break;
                case 8:
                    base = "ARS";
                    target = "USD";
                    break;
            }

            String direccion = "https://v6.exchangerate-api.com/v6/f12ffb9a096c4da9e585e821/pair/"
                    + base + "/" + target + "/" + cantidad;
            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(direccion))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();


                if (json.trim().startsWith("{")) {
                    Respuesta respuesta = gson.fromJson(json, Respuesta.class);
                    double conversionResult = respuesta.getConversionResult();


                    BigDecimal roundedResult = new BigDecimal(conversionResult).setScale(2, RoundingMode.HALF_UP);
                    System.out.println("$" + cantidad + " " + base + " equivalen a $" + roundedResult + " " + target);


                    history.addRecord(cantidad, base, target, roundedResult.doubleValue());


                    history.saveHistoryToFile("hisorial_conversiones.txt");
                } else {
                    System.out.println("Respuesta inesperada: ");
                }

            } catch (JsonSyntaxException e) {
                System.out.println("Error de sintaxis JSON: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Solo números");
            } catch (IOException | InterruptedException e) {
                System.out.println("Error en la conexión. Por favor, intente de nuevo más tarde.");
            }
        }
    }
}

