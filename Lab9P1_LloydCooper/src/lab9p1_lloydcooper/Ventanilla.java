package lab9p1_lloydcooper;

import java.util.ArrayList;
import java.util.Arrays;

public class Ventanilla {
    
    private ArrayList<Integer> colaPrioridad;
    private ArrayList<Integer> colaRegular;
    private int[] ventanilla;
    private char[] clientesEnVentanilla;
    
    public Ventanilla(int tamano) {
        //System.out.println("constructor");
        colaPrioridad = new ArrayList<>();
        colaRegular = new ArrayList<>();
        ventanilla = new int[tamano];
        clientesEnVentanilla = new char[tamano];
        Arrays.fill(ventanilla, 0);
        Arrays.fill(clientesEnVentanilla, ' ');
    }
    
    public ArrayList<Integer> getColaPrioridad() {
        return colaPrioridad;
    }
    
    public void setColaPrioridad(ArrayList<Integer> colaPrioridad) {
        this.colaPrioridad = colaPrioridad;
    }
    
    public ArrayList<Integer> getColaRegular() {
        return colaRegular;
    }
    
    public void setColaRegular(ArrayList<Integer> colaRegular) {
        this.colaRegular = colaRegular;
    }
    
    public int[] getVentanilla() {
        return ventanilla;
    }
    
    public void setVentanilla(int[] ventanilla) {
        this.ventanilla = ventanilla;
    }
    
    public char[] getClientesEnVentanilla() {
        return clientesEnVentanilla;
    }
    
    public void setClientesEnVentanilla(char[] clientesEnVentanilla) {
        this.clientesEnVentanilla = clientesEnVentanilla;
    }
    
    public void agregarClienteRegular(int tiempoConsulta) {
        colaRegular.add(tiempoConsulta);
    }
    
    public void agregarClientePreferencial(int tiempoConsulta) {
        colaPrioridad.add(tiempoConsulta);
    }
    
    public int encontrarVentanillaDisponible() {
        for (int i = 0; i < ventanilla.length; i++) {
            if (ventanilla[i] == 0) {
                return i;
            }
        }
        return -1;
    }
    
    public void actualizarVentanillas() {
        for (int i = 0; i < ventanilla.length; i++) {
            if (ventanilla[i] > 0) {
                ventanilla[i]--;
                if (ventanilla[i] == 0) {
                    clientesEnVentanilla[i] = ' ';
                }
            }
        }
    }
    
    public void correrSimulacion(int tiempoTotal) {
        //System.out.println("corriendo sim");
        int tiempoActual = 0;
        while (tiempoActual < tiempoTotal && (!colaPrioridad.isEmpty() || !colaRegular.isEmpty())) {
            
          //  System.out.println("entro al while");
            actualizarVentanillas();
            int ventanillaDisponible = encontrarVentanillaDisponible();
            if (ventanillaDisponible != -1) {
              //  System.out.println("entro al if");
              
                while (!colaPrioridad.isEmpty() && ventanillaDisponible != -1) {
                 //  System.out.println("segundo if");
                    int tiempoConsulta = colaPrioridad.remove(0);
                    ventanilla[ventanillaDisponible] = tiempoConsulta;
                    clientesEnVentanilla[ventanillaDisponible] = 'P';
                    ventanillaDisponible = encontrarVentanillaDisponible();
                    
                }
                
//                    System.out.println(clientesEnVentanilla);
                while(!colaRegular.isEmpty() && ventanillaDisponible != -1) {
                 //   System.out.println("else");
                    int tiempoConsulta = colaRegular.remove(0);
                    ventanilla[ventanillaDisponible] = tiempoConsulta;
                    clientesEnVentanilla[ventanillaDisponible] = 'R';
                    ventanillaDisponible = encontrarVentanillaDisponible();

                }
            }
            print2();
            print();
            tiempoActual++;
            System.out.println("");
            System.out.println("");
        }
    }

    public void print() {
        for (int i = 0; i < ventanilla.length; i++) {
            System.out.print("[" + ventanilla[i] + "]");
        }
        System.out.println("");
    }
    public void print2() {
        for (int i = 0; i < clientesEnVentanilla.length; i++) {
            System.out.print("[" + clientesEnVentanilla[i] + "]");
        }
        System.out.println("");
    }
    
    public int calcularTiempoTotal() {
        int tiempoTotal = 0;
        for (int i = 0; i < colaPrioridad.size(); i++) {
            // System.out.println("for ventanilla");
            print();

            //System.out.println("el if en tiempo");
            tiempoTotal += colaPrioridad.get(i);
            
        }
        
        for (int i = 0; i < colaRegular.size(); i++) {
            
            tiempoTotal += colaRegular.get(i);
        }
        return tiempoTotal;
    }
}
