import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConversorDeMonedas {
    static List<TasaDeCambio> registro = new ArrayList<TasaDeCambio>();

    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        ExchangeRateAPIClient clienteAPI = new ExchangeRateAPIClient();
        IO.println("*** BIENVENIDO AL CONVERSOR DE MONEDAS. ***");
        String menu = """
                Por favor, seleccione una opción:
                1. Convertir de Dólares a Pesos argentinos.
                2. Convertir de Pesos argentinos a Dólares.
                3. Convertir de Dólares a pesos mexicanos.
                4. Convertir de pesos mexicanos a Dólares.
                5. Convertir de Real brasileño a Pesos argentinos.
                6. Convertir de Pesos argentinos a Real brasileño .
                7. Salir
                """;
        int opcionDelUsuario = 0;
        double cantidad = 0.0;

        while (opcionDelUsuario != 7) {
            IO.println(menu);
            opcionDelUsuario = lectura.nextInt();
            switch (opcionDelUsuario) {
                case 1:
                    String base1 = "USD";
                    String target1 = "ARS";
                    IO.println("Ingrese la cantidad en Dólares a convertir:");
                    cantidad = lectura.nextDouble();
                    try {
                        TasaDeCambio resultado = clienteAPI.resultadoDeConversion(base1, target1, cantidad);
                        IO.println("Resultado: " + resultado.conversion_result() + " Pesos argentinos.");
                        registro.add(resultado);
                    } catch (IOException | InterruptedException e) {
                        IO.println("Lo sentimos, ha ocurrido un error.");
                        System.exit(0);
                    }
                    break;
                case 2:
                    String base2 = "ARS";
                    String target2 = "USD";
                    IO.println("Ingrese la cantidad en Pesos argentinos a convertir:");
                    cantidad = lectura.nextDouble();
                    try {
                        TasaDeCambio resultado = clienteAPI.resultadoDeConversion(base2, target2, cantidad);
                        IO.println("Resultado: " + resultado.conversion_result() + " Dólares.");
                        registro.add(resultado);
                    } catch (IOException | InterruptedException e) {
                        IO.println("Lo sentimos, ha ocurrido un error.");
                        System.exit(0);
                    }
                    break;
                case 3:
                    String base3 = "USD";
                    String target3 = "MXN";
                    IO.println("Ingrese la cantidad en Dólares a convertir:");
                    cantidad = lectura.nextDouble();
                    try {
                        TasaDeCambio resultado = clienteAPI.resultadoDeConversion(base3, target3, cantidad);
                        IO.println("Resultado: " + resultado.conversion_result() + " Pesos mexicanos.");
                        registro.add(resultado);
                    } catch (IOException |InterruptedException e) {
                        IO.println("Lo sentimos, ha ocurrido un error.");
                        System.exit(0);
                    }
                    break;
                case 4:
                    String base4 = "MXN";
                    String target4 = "USD";
                    IO.println("Ingrese la cantidad en Pesos mexicanos a convertir:");
                    cantidad = lectura.nextDouble();
                    try {
                        TasaDeCambio resultado = clienteAPI.resultadoDeConversion(base4, target4, cantidad);
                        IO.println("Resultado: " + resultado.conversion_result() + " Dólares.");
                        registro.add(resultado);
                    } catch (IOException | InterruptedException e) {
                        IO.println("Lo sentimos, ha ocurrido un error.");
                        System.exit(0);
                    }
                    break;
                case 5:
                    String base5 = "BRL";
                    String target5 = "ARS";
                    IO.println("Ingrese la cantidad en Real brasileño a convertir:");
                    cantidad = lectura.nextDouble();
                    try {
                        TasaDeCambio resultado = clienteAPI.resultadoDeConversion(base5, target5, cantidad);
                        IO.println("Resultado: " + resultado.conversion_result() + " Pesos argentinos.");
                        registro.add(resultado);
                    } catch (IOException | InterruptedException e) {
                        IO.println("Lo sentimos, ha ocurrido un error.");
                        System.exit(0);
                    }
                    break;
                case 6:
                    String base6 = "ARS";
                    String target6 = "BRL";
                    IO.println("Ingrese la cantidad en Pesos argentinos a convertir:");
                    cantidad = lectura.nextDouble();
                    try {
                        TasaDeCambio resultado = clienteAPI.resultadoDeConversion(base6, target6, cantidad);
                        IO.println("Resultado: " + resultado.conversion_result() + " Real brasileño.");
                        registro.add(resultado);
                    } catch (IOException | InterruptedException e) {
                        IO.println("Lo sentimos, ha ocurrido un error.");
                        System.exit(0);
                    }
                    break;
            }
        }
        HistorialDeConverciones historial = new HistorialDeConverciones();
        historial.guardarHistorial(registro);
        IO.println("Su historial de conversiones: ");
        historial.mostrarHistorial(registro);
        IO.println("Gracias por usar el conversor de monedas. ¡Hasta luego!");
    }
}
