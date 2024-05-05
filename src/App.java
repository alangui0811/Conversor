import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int opcion;
        double mount;
        
        do {
            System.out.println("Ingresa el monto a convertir: ");
            mount = scanner.nextDouble();
            System.out.println("Ingresa la conversion que deseas realizar: ");
            System.out.println("1) Dolar a Peso Argentino\n2) Peso Argentino a Dolar\n3) Dolar a Real Brasileño\n4) Real Brasileño a Dolar\n5) Dolar a Peso Colombiano\n6) Peso Colombiano a Dolar\n7) Dolar a Peso Méxicano\n8) Peso Méxicano a Dolar\n9) Salir");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    http("USD", "ARS",mount);
                    break;
                case 2:
                    http("ARS", "USD",mount);
                    break;
                case 3:
                    http("USD", "BRL",mount);
                    break;
                case 4:
                    http("BRL", "USD",mount);
                    break;
                case 5:
                    http("USD", "COP",mount);
                    break;
                case 6:
                    http("COP", "USD",mount);
                    break;
                case 7:
                    http("USD", "MXN",mount);
                    break;
                case 8:
                    http("MXN", "USD",mount);
                    break;
                case 9:
                    System.out.println("Adios!");
                    break;
            
                default:
                    break;
            }
            System.out.println("Presiona Enter para continuar: ");
            String sTexto = br.readLine();
        } while (opcion < 9);
        scanner.close();
    }

    public static void http(String base, String target, double quantity) throws IOException, InterruptedException{
        String url = "https://v6.exchangerate-api.com/v6/f57c12f9061e9e095c74b7bd/pair/" + base + "/" + target + "/" + quantity;

        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
             .uri(URI.create(url))
             .build();
             HttpResponse<String> response = client
             .send(request, HttpResponse.BodyHandlers.ofString());

             String json = response.body();

             Gson gson = new Gson();
             Conversion1 conversion = gson.fromJson(json, Conversion1.class);
            Conversion conversion2 = new Conversion(conversion);
            System.out.println(conversion2);

        }catch(IllegalArgumentException e){
            if (e instanceof IllegalArgumentException) {
                System.out.println("Error con la URL");
            } else {
                e.printStackTrace();
            }
    }
    
}
}