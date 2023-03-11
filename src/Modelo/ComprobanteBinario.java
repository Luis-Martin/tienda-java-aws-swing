package Modelo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;

// import java.io.FileNotFoundException;

// CRUD
public class ComprobanteBinario {

    // Creamos o sobreescribe sobre el archivo binario con el ArrayList pasado como
    // parámetro
    public void crearBinario(ArrayList<Comprobante> comprobantesBase) {

        try {

            FileOutputStream archivoOut = new FileOutputStream("./src/comprobantes.dat");
            ObjectOutputStream comprobanteOutput = new ObjectOutputStream(archivoOut);

            // Rellenamos el archivo binario con el ArrayList pasado como parámetro
            for (Comprobante comprobante : comprobantesBase) {
                comprobanteOutput.writeObject(comprobante);
            }

            comprobanteOutput.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    // Leer el archivo binario y te devuelve todos los Comprobantes en un array list
    // de
    // Comprobantes
    public ArrayList<Comprobante> leerBinario() throws ClassNotFoundException {

        // ArrayList donde se guardarán todos los Comprobantes
        ArrayList<Comprobante> comprobantes_totales = new ArrayList<>();

        try {

            // Leyendo en el archivo binario
            FileInputStream archivoIn = new FileInputStream("./src/comprobantes.dat");
            ObjectInputStream comprobanteInput = new ObjectInputStream(archivoIn);

            // Rellenamos el ArrayList
            while (archivoIn.available() > 0) {
                Comprobante comprobante = (Comprobante) comprobanteInput.readObject();
                comprobantes_totales.add(comprobante);
            }

            comprobanteInput.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return comprobantes_totales;

    }

    // Agrega el Comprobante pasado como parámetro al final del archibo binario
    public ArrayList<Comprobante> agregarComprobante(Comprobante comprobanteNuevo) {

        ArrayList<Comprobante> comprobantes_totales = new ArrayList<>();

        try {
            // Escribiendo en el final de archivos binario

            // Relleno array con comprobantes totales

            comprobantes_totales = this.leerBinario();
            comprobantes_totales.add(comprobanteNuevo);

            // Sobreescribir el archivo binario
            this.crearBinario(comprobantes_totales);

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return comprobantes_totales;
    }

    // Agrega los Comprobantes pasado como parámetro al final del archibo binario
    public ArrayList<Comprobante> agregarComprobantes(ArrayList<Comprobante> comprobantesNuevos) {

        ArrayList<Comprobante> comprobantes_totales = new ArrayList<>();

        try {
            // Escribiendo en el final de archivos binario

            // Relleno array con Comprobantes totales

            comprobantes_totales = this.leerBinario();
            for (Comprobante comprobante : comprobantesNuevos) {

                comprobantes_totales.add(comprobante);
            }

            // Sobreescribir el archivo binario
            this.crearBinario(comprobantes_totales);

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return comprobantes_totales;
    }

    // Elimina el Comprobante con el id_venta pasado como parámetro y te devuelve la
    // nueva lista con el Comprobante eliminado en un ArrayLista
    public ArrayList<Comprobante> eliminarComprobante(int id_venta) throws ClassNotFoundException {

        ArrayList<Comprobante> comprobantes_totales = new ArrayList<>();
        Comprobante comprobante = new Comprobante();

        // Traemos todos los Comprobantes existentes
        comprobantes_totales = this.leerBinario();
        for (int i = 0; i < comprobantes_totales.size(); i++) {

            // Traemos el Comprobante a comparar
            comprobante = comprobantes_totales.get(i);

            // Comparamos el Comprobante para ver si es el Comprobante a eliminar
            if (comprobante.getId_venta() == id_venta) {
                comprobantes_totales.remove(comprobante);
            }

        }

        // Sobreescribimos los Comprobantes ya habiendo eliminado el Comprobante deseado
        this.crearBinario(comprobantes_totales);

        return comprobantes_totales;

    }

    // editar Comprobante que se pasa por parámetro, editará el Comprobante que
    // tenga el mismo id_venta
    public ArrayList<Comprobante> editarComprobante(Comprobante comprobanteAEditar) throws ClassNotFoundException {

        ArrayList<Comprobante> comprobantesTotales = new ArrayList<>();
        ArrayList<Comprobante> comprobantesTotalesEditado = new ArrayList<>();
        Comprobante comprobante = new Comprobante();
        // Traemos todos los Comprobantes existentes
        comprobantesTotales = this.leerBinario();

        for (int i = 0; i < comprobantesTotales.size(); i++) {

            // Traemos el Comprobante a comparar
            comprobante = comprobantesTotales.get(i);

            // Comparamos el Comprobante para ver si es el Comprobante a eliminar
            if (comprobante.getId_venta() == comprobanteAEditar.getId_venta()) {
                comprobantesTotalesEditado.add(comprobanteAEditar);
            } else {
                comprobantesTotalesEditado.add(comprobante);
            }

        }

        // Sobreescribimos los Comprobantes ya habiendo eliminado el Comprobante deseado
        this.crearBinario(comprobantesTotalesEditado);

        return comprobantesTotalesEditado;

    }

    // optener comprobante por el idVenta
    public Comprobante optenerComprobante(int idVenta) throws ClassNotFoundException {
    
        ArrayList<Comprobante> comprobantesTotales = this.leerBinario();
        
        Comprobante comprobanteSeleccionado = new Comprobante();

        for (Comprobante comprobante : comprobantesTotales) {
            if(idVenta == comprobante.getId_venta()) {
                comprobanteSeleccionado.setId_venta(comprobante.getId_venta());
                comprobanteSeleccionado.setTipo_comprobante(comprobante.getTipo_comprobante());
                comprobanteSeleccionado.setFecha_emision(comprobante.getFecha_emision());
            }
        }

        return comprobanteSeleccionado;
    
    }

}
