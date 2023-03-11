package Modelo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;

// import java.io.FileNotFoundException;

// CRUD
public class VentaBinario {

    // Creamos o sobreescribe sobre el archivo binario con el ArrayList pasado como
    // parámetro
    public void crearBinario(ArrayList<Venta> ventasBase) {

        try {

            FileOutputStream archivoOut = new FileOutputStream("./src/ventas.dat");
            ObjectOutputStream ventaOutput = new ObjectOutputStream(archivoOut);

            // Rellenamos el archivo binario con el ArrayList pasado como parámetro
            for (Venta venta : ventasBase) {
                ventaOutput.writeObject(venta);
            }

            ventaOutput.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    // Leer el archivo binario y te devuelve todos los ventas en un array list de
    // ventas
    public ArrayList<Venta> leerBinario() throws ClassNotFoundException {

        // ArrayList donde se guardarán todos los ventas
        ArrayList<Venta> ventas_totales = new ArrayList<>();

        try {

            // Leyendo en el archivo binario
            FileInputStream archivoIn = new FileInputStream("./src/ventas.dat");
            ObjectInputStream ventaInput = new ObjectInputStream(archivoIn);

            // Rellenamos el ArrayList
            while (archivoIn.available() > 0) {
                Venta venta = (Venta) ventaInput.readObject();
                ventas_totales.add(venta);
            }

            ventaInput.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return ventas_totales;

    }

    // Agrega el venta pasado como parámetro al final del archibo binario
    public ArrayList<Venta> agregarVenta(Venta ventaNuevo) {

        ArrayList<Venta> ventas_totales = new ArrayList<>();

        try {
            // Escribiendo en el final de archivos binario

            // Relleno array con ventas totales

            ventas_totales = this.leerBinario();
            ventas_totales.add(ventaNuevo);

            // Sobreescribir el archivo binario
            this.crearBinario(ventas_totales);

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return ventas_totales;
    }

    // Agrega los ventas pasado como parámetro al final del archibo binario
    public ArrayList<Venta> agregarVentas(ArrayList<Venta> ventasNuevos) {

        ArrayList<Venta> ventas_totales = new ArrayList<>();

        try {
            // Escribiendo en el final de archivos binario

            // Relleno array con ventas totales

            ventas_totales = this.leerBinario();
            for (Venta venta : ventasNuevos) {

                ventas_totales.add(venta);
            }

            // Sobreescribir el archivo binario
            this.crearBinario(ventas_totales);

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return ventas_totales;
    }

    // Elimina el venta con el id_venta pasado como parámetro y te devuelve la
    // nueva lista con el venta eliminado en un ArrayLista
    public ArrayList<Venta> eliminarVenta(int id_venta) throws ClassNotFoundException {

        ArrayList<Venta> ventas_totales = new ArrayList<>();
        Venta venta = new Venta();

        // Traemos todos los ventas existentes
        ventas_totales = this.leerBinario();

        for (int i = 0; i < ventas_totales.size(); i++) {
            venta = ventas_totales.get(i);

            // Comparamos el venta para ver si es el venta a eliminar
            if (venta.getId_Venta() == id_venta) {
                ventas_totales.remove(venta);
            }
        }
        // Sobreescribimos los ventas ya habiendo eliminado el venta deseado
        this.crearBinario(ventas_totales);
        return ventas_totales;
    }

    // editar venta que se pasa por parámetro, editará el venta que tenga
    // el mismo id_venta
    public ArrayList<Venta> editarVenta(Venta ventaAEditar) throws ClassNotFoundException {

        ArrayList<Venta> ventasTotales = new ArrayList<>();
        ArrayList<Venta> ventasTotalesEditado = new ArrayList<>();
        Venta venta = new Venta();

        // Traemos todos los ventas existentes
        ventasTotales = this.leerBinario();

        for (int i = 0; i < ventasTotales.size(); i++) {

            // Traemos el venta a comparar
            venta = ventasTotales.get(i);

            // Comparamos el venta para ver si es el venta a eliminar
            if (venta.getId_Venta() == ventaAEditar.getId_Venta()) {
                ventasTotalesEditado.add(ventaAEditar);
            } else {
                ventasTotalesEditado.add(venta);
            }

        }

        // Sobreescribimos los ventas ya habiendo eliminado el venta deseado
        this.crearBinario(ventasTotalesEditado);

        return ventasTotalesEditado;

    }

}
