package Modelo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;

// import java.io.FileNotFoundException;

// CRUD
public class TrabajadorBinario {

    // Creamos o sobreescribe sobre el archivo binario con el ArrayList pasado como
    // parámetro
    public void crearBinario(ArrayList<Trabajador> clientesBase) {

        try {

            FileOutputStream archivoOut = new FileOutputStream("./src/trabajadores.dat");
            ObjectOutputStream trabajadorOutput = new ObjectOutputStream(archivoOut);

            // Rellenamos el archivo binario con el ArrayList pasado como parámetro
            for (Trabajador cliente : clientesBase) {
                trabajadorOutput.writeObject(cliente);
            }

            trabajadorOutput.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    // Leer el archivo binario y te devuelve todos los clientes en un array list de clientes
    public ArrayList<Trabajador> leerBinario() throws ClassNotFoundException {

        // ArrayList donde se guardarán todos los clientes
        ArrayList<Trabajador> trabajadores_totales = new ArrayList<>();

        try {

            // Leyendo en el archivo binario
            FileInputStream archivoIn = new FileInputStream("./src/trabajadores.dat");
            ObjectInputStream trabajadorInput = new ObjectInputStream(archivoIn);

            // Rellenamos el ArrayList
            while (archivoIn.available() > 0) {
                Trabajador trabajador = (Trabajador) trabajadorInput.readObject();
                trabajadores_totales.add(trabajador);
            }

            trabajadorInput.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return trabajadores_totales;

    }

    // Agrega el trabajador pasado como parámetro al final del archibo binario
    public ArrayList<Trabajador> agregarTrabajador(Trabajador trabajadorNuevo) {

        ArrayList<Trabajador> trabajadores_totales = new ArrayList<>();

        try {
            // Escribiendo en el final de archivos binario

            // Relleno array con clientes totales

            trabajadores_totales = this.leerBinario();
            trabajadores_totales.add(trabajadorNuevo);

            // Sobreescribir el archivo binario
            this.crearBinario(trabajadores_totales);

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return trabajadores_totales;
    }

    // Agrega los trabajadores pasado como parámetro al final del archibo binario
    public ArrayList<Trabajador> agregarTrabajador(ArrayList<Trabajador> trabajadoresNuevos) {

        ArrayList<Trabajador> trabajadores_totales = new ArrayList<>();

        try {
            // Escribiendo en el final de archivos binario

            // Relleno array con trabajadores totales

            trabajadores_totales = this.leerBinario();
            for (Trabajador trabajador : trabajadoresNuevos) {

                trabajadores_totales.add(trabajador);
            }

            // Sobreescribir el archivo binario
            this.crearBinario(trabajadores_totales);

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return trabajadores_totales;
    }

    // Elimina el trabajar con el nombre pasado como parámetro y te devuelve la
    // nueva lista con el trabajador eliminado en un ArrayLista
    public ArrayList<Trabajador> eliminarTrabajador(String nombre) throws ClassNotFoundException {

        ArrayList<Trabajador> trabajadores_totales = new ArrayList<>();
        Trabajador trabajador = new Trabajador();

        // Traemos todos los trabajadors existentes
        trabajadores_totales = this.leerBinario();

        for (int i = 0; i < trabajadores_totales.size(); i++) {

            // Traemos el trabajador a comparar
            trabajador = trabajadores_totales.get(i);

            // Comparamos el trabajador para ver si es el trabajador a eliminar
            if (trabajador.getNombre().equals(nombre)) {
                trabajadores_totales.remove(trabajador);
            }

        }

        // Sobreescribimos los clientes ya habiendo eliminado el cliente deseado
        this.crearBinario(trabajadores_totales);

        return trabajadores_totales;

    }

    // Editar trabajador que se pasa por parámetro, editará el trabajador que tenga el mismo id_cliente
    public ArrayList<Trabajador> editarTrabajador(Trabajador trabajadorAEditar) throws ClassNotFoundException {

        ArrayList<Trabajador> trabajadoresTotales = new ArrayList<>();
        ArrayList<Trabajador> trabajadoresTotalesEditado = new ArrayList<>();
        Trabajador trabajador = new Trabajador();

        // Traemos todos los trabajadors existentes
        trabajadoresTotales = this.leerBinario();

        for (int i = 0; i < trabajadoresTotales.size(); i++) {

            // Traemos el trabajador a comparar
            trabajador = trabajadoresTotales.get(i);

            // Comparamos el trabajador para ver si es el trabajador a eliminar
            if (trabajador.getNombre() == trabajadorAEditar.getNombre()) {
                trabajadoresTotalesEditado.add(trabajadorAEditar);
            } else {
                trabajadoresTotalesEditado.add(trabajador);
            }

        }

        // Sobreescribimos los clientes ya habiendo eliminado el cliente deseado
        this.crearBinario(trabajadoresTotalesEditado);

        return trabajadoresTotalesEditado;

    }

    // Obtener datos de un CLiente mediante su nombre
    public Trabajador obtenerCliente(String nombre) throws ClassNotFoundException {

        ArrayList<Trabajador> trabajador = this.leerBinario();
        
        Trabajador trabajadorABuscar = new Trabajador();

        for (Trabajador cliente : trabajador) {
            if (cliente.getNombre().equals(nombre)){
                trabajadorABuscar.setNombre(cliente.getNombre());
                trabajadorABuscar.setApellido(cliente.getApellido());
                trabajadorABuscar.setDni(cliente.getDni());
                trabajadorABuscar.setContrasenia(cliente.getContrasenia());
            }
        }

        return trabajadorABuscar;
    
    }

}
