package Modelo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;

// import java.io.FileNotFoundException;

// CRUD
public class Detalle_Venta_Binario {

    // Creamos o sobreescribe sobre el archivo binario con el ArrayList pasado como
    // parámetro
    public void crearBinario(ArrayList<Detalle_Venta> detalleVentasBase) {

        try {

            FileOutputStream archivoOut = new FileOutputStream("./src/detalleVentas.dat");
            ObjectOutputStream detalleVentaOutput = new ObjectOutputStream(archivoOut);

            // Rellenamos el archivo binario con el ArrayList pasado como parámetro
            for (Detalle_Venta detalleVenta : detalleVentasBase) {
                detalleVentaOutput.writeObject(detalleVenta);
            }

            detalleVentaOutput.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    // Leer el archivo binario y te devuelve todos los detalleVentass en un array
    // list de
    // detalleVentass
    public ArrayList<Detalle_Venta> leerBinario() throws ClassNotFoundException {

        // ArrayList donde se guardarán todos los detalleVentass
        ArrayList<Detalle_Venta> detalleVentas_totales = new ArrayList<>();

        try {

            // Leyendo en el archivo binario
            FileInputStream archivoIn = new FileInputStream("./src/detalleVentas.dat");
            ObjectInputStream detalleVentaInput = new ObjectInputStream(archivoIn);

            // Rellenamos el ArrayList
            while (archivoIn.available() > 0) {
                Detalle_Venta pago = (Detalle_Venta) detalleVentaInput.readObject();
                detalleVentas_totales.add(pago);
            }

            detalleVentaInput.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return detalleVentas_totales;

    }

    // Agrega el detalleVentas pasado como parámetro al final del archibo binario
    public ArrayList<Detalle_Venta> agregarDetalle_Venta(Detalle_Venta detalleVentaNuevo) {

        ArrayList<Detalle_Venta> detalleVentas_totales = new ArrayList<>();

        try {
            // Escribiendo en el final de archivos binario

            // Relleno array con detalleVentas totales

            detalleVentas_totales = this.leerBinario();
            detalleVentas_totales.add(detalleVentaNuevo);

            // Sobreescribir el archivo binario
            this.crearBinario(detalleVentas_totales);

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return detalleVentas_totales;
    }

    // Agrega los detalleVentass pasado como parámetro al final del archibo binario
    public ArrayList<Detalle_Venta> agregarDetalle_Ventas(ArrayList<Detalle_Venta> detalleVentasNuevos) {

        ArrayList<Detalle_Venta> detalleVentas_totales = new ArrayList<>();

        try {
            // Escribiendo en el final de archivos binario

            // Relleno array con detalleVentas totales

            detalleVentas_totales = this.leerBinario();
            for (Detalle_Venta detalleVenta : detalleVentasNuevos) {

                detalleVentas_totales.add(detalleVenta);
            }

            // Sobreescribir el archivo binario
            this.crearBinario(detalleVentas_totales);

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return detalleVentas_totales;
    }

    // Elimina el detalleVentas con el id_venta pasado como parámetro y te devuelve
    // la
    // nueva lista con el detalleVentas eliminado en un ArrayLista

    public ArrayList<Detalle_Venta> eliminarDetalle_Venta(int id_venta) throws ClassNotFoundException {

        ArrayList<Detalle_Venta> detalleVentas_totales = new ArrayList<>();
        Detalle_Venta detalleVenta = new Detalle_Venta();

        // Traemos todos los detalleVentass existentes
        detalleVentas_totales = this.leerBinario();

        for (int i = 0; i < detalleVentas_totales.size(); i++) {

            // Traemos el detalleVentas a comparar
            detalleVenta = detalleVentas_totales.get(i);

            // Comparamos el detalleVentas para ver si es el detalleVentas a eliminar
            if (detalleVenta.getId_venta() == id_venta) {
                detalleVentas_totales.remove(detalleVenta);
            }

        }
        // Sobreescribimos los detalleVentass ya habiendo eliminado el detalleVentas
        // deseado
        this.crearBinario(detalleVentas_totales);

        return detalleVentas_totales;

    }

    // editar detalleVentas que se pasa por parámetro, editará el detalleVentas que
    // tenga
    // el mismo id_venta
    public ArrayList<Detalle_Venta> editarDetalle_Venta(Detalle_Venta detalleVentaAEditar)
            throws ClassNotFoundException {

        ArrayList<Detalle_Venta> detalleVentasTotales = new ArrayList<>();
        ArrayList<Detalle_Venta> detalleVentasTotalesEditado = new ArrayList<>();
        Detalle_Venta detalleVenta = new Detalle_Venta();

        // Traemos todos los detalleVentass existentes
        detalleVentasTotales = this.leerBinario();

        for (int i = 0; i < detalleVentasTotales.size(); i++) {

            // Traemos el detalleVentas a comparar
            detalleVenta = detalleVentasTotales.get(i);

            // Comparamos el detalleVentas para ver si es el detalleVentas a eliminar
            if (detalleVenta.getId_venta() == detalleVentaAEditar.getId_venta()) {
                detalleVentasTotalesEditado.add(detalleVentaAEditar);
            } else {
                detalleVentasTotalesEditado.add(detalleVenta);
            }

        }

        // Sobreescribimos los detalleVentass ya habiendo eliminado el detalleVentas
        // deseado
        this.crearBinario(detalleVentasTotalesEditado);

        return detalleVentasTotalesEditado;

    }

}
