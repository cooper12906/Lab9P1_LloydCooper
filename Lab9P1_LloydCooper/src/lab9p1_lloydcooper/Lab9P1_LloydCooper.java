package lab9p1_lloydcooper;

import java.util.Random;
import java.util.*;
        
public class Lab9P1_LloydCooper {

    public static void main(String[] args) {
        char resp = 's';
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("Elija una opción:");
            System.out.println("1. Pase a la ventanilla N... ");
            System.out.println("2. Salida ");
            
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el número de clientes a generar: ");
                        int numClientes = scanner.nextInt();
                        
                        System.out.print("Ingrese el número de ventanillas disponibles: ");
                        int numVentanillas = scanner.nextInt();
                       // scanner.nextLine();

                        Random random = new Random();
                        Ventanilla ventanilla = new Ventanilla(numVentanillas);
                        for (int i = 0; i < numClientes; i++) {
                            int tiempoConsulta = random.nextInt(5) + 1; // genera un número aleatorio entre 1 y 5
                            if (random.nextDouble() < 0.5) {
//                                System.out.println("primer if");
                                ventanilla.agregarClienteRegular(tiempoConsulta);
                            } else {
                                ventanilla.agregarClientePreferencial(tiempoConsulta);
                            }
                        }
//                        System.out.println(ventanilla.getColaRegular());
//                        System.out.println(ventanilla.getColaPrioridad());
                        int tiempoTotal = ventanilla.calcularTiempoTotal();
                        System.out.print("El tiempo total: ");
                        System.out.println(tiempoTotal);
                        System.out.println(ventanilla.getColaPrioridad());
                        System.out.println(ventanilla.getColaRegular());
                        ventanilla.correrSimulacion(tiempoTotal);
                    break;
                case 2:
                    resp = 'n';
                    break;
                default:
                    System.out.println("Opción inválida, por favor intente de nuevo.");
            }
 
        System.out.println("Desea continuar? [s/n]");
        resp = scanner.next().charAt(0);
        scanner.nextLine();
       } while (resp == 's' || resp == 'S');   
    }   
}
