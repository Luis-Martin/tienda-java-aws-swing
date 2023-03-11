package Modelo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;

// import java.io.FileNotFoundException;

// CRUD
public class ClienteBinario {

    // Creamos o sobreescribe sobre el archivo binario con el ArrayList pasado como
    // parámetro
    public void crearBinario(ArrayList<Cliente> clientesBase) {

        try {

            FileOutputStream archivoOut = new FileOutputStream("./src/clientes.dat");
            ObjectOutputStream clienteOutput = new ObjectOutputStream(archivoOut);

            // Rellenamos el archivo binario con el ArrayList pasado como parámetro
            for (Cliente cliente : clientesBase) {
                clienteOutput.writeObject(cliente);
            }

            clienteOutput.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    // Leer el archivo binario y te devuelve todos los clientes en un array list de clientes
    public ArrayList<Cliente> leerBinario() throws ClassNotFoundException {

        // ArrayList donde se guardarán todos los clientes
        ArrayList<Cliente> clientes_totales = new ArrayList<>();

        try {

            // Leyendo en el archivo binario
            FileInputStream archivoIn = new FileInputStream("./src/clientes.dat");
            ObjectInputStream clienteInput = new ObjectInputStream(archivoIn);

            // Rellenamos el ArrayList
            while (archivoIn.available() > 0) {
                Cliente cliente = (Cliente) clienteInput.readObject();
                clientes_totales.add(cliente);
            }

            clienteInput.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return clientes_totales;

    }

    // Agrega el cliente pasado como parámetro al final del archibo binario
    public ArrayList<Cliente> agregarCliente(Cliente clienteNuevo) {

        ArrayList<Cliente> clientes_totales = new ArrayList<>();

        try {
            // Escribiendo en el final de archivos binario

            // Relleno array con clientes totales

            clientes_totales = this.leerBinario();
            clientes_totales.add(clienteNuevo);

            // Sobreescribir el archivo binario
            this.crearBinario(clientes_totales);

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return clientes_totales;
    }

    // Agrega los clientes pasado como parámetro al final del archibo binario
    public ArrayList<Cliente> agregarClientes(ArrayList<Cliente> clientesNuevos) {

        ArrayList<Cliente> clientes_totales = new ArrayList<>();

        try {
            // Escribiendo en el final de archivos binario

            // Relleno array con clientes totales

            clientes_totales = this.leerBinario();
            for (Cliente cliente : clientesNuevos) {

                clientes_totales.add(cliente);
            }

            // Sobreescribir el archivo binario
            this.crearBinario(clientes_totales);

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return clientes_totales;
    }

    // Elimina el cliente con el id_cliente pasado como parámetro y te devuelve la
    // nueva lista con el cliente eliminado en un ArrayLista
    public ArrayList<Cliente> eliminarCliente(int id_cliente) throws ClassNotFoundException {

        ArrayList<Cliente> clientes_totales = new ArrayList<>();
        Cliente cliente = new Cliente();

        // Traemos todos los clientes existentes
        clientes_totales = this.leerBinario();

        for (int i = 0; i < clientes_totales.size(); i++) {

            // Traemos el cliente a comparar
            cliente = clientes_totales.get(i);

            // Comparamos el cliente para ver si es el cliente a eliminar
            if (cliente.getId_Cliente() == id_cliente) {
                clientes_totales.remove(cliente);
            }

        }

        // Sobreescribimos los clientes ya habiendo eliminado el cliente deseado
        this.crearBinario(clientes_totales);

        return clientes_totales;

    }

    // Editar cliente que se pasa por parámetro, editará el cliente que tenga el mismo id_cliente
    public ArrayList<Cliente> editarCliente(Cliente clienteAEditar) throws ClassNotFoundException {

        ArrayList<Cliente> clientesTotales = new ArrayList<>();
        ArrayList<Cliente> clientesTotalesEditado = new ArrayList<>();
        Cliente cliente = new Cliente();

        // Traemos todos los clientes existentes
        clientesTotales = this.leerBinario();

        for (int i = 0; i < clientesTotales.size(); i++) {

            // Traemos el cliente a comparar
            cliente = clientesTotales.get(i);

            // Comparamos el cliente para ver si es el cliente a eliminar
            if (cliente.getId_Cliente() == clienteAEditar.getId_Cliente()) {
                clientesTotalesEditado.add(clienteAEditar);
            } else {
                clientesTotalesEditado.add(cliente);
            }

        }

        // Sobreescribimos los clientes ya habiendo eliminado el cliente deseado
        this.crearBinario(clientesTotalesEditado);

        return clientesTotalesEditado;

    }

    // Obtener datos de un CLiente mediante su nombre
    public Cliente obtenerCliente(String nombre) throws ClassNotFoundException {

        ArrayList<Cliente> clientes = this.leerBinario();
        
        Cliente ClienteABuscar = new Cliente();

        for (Cliente cliente : clientes) {
            if (cliente.getNombre().equals(nombre)){
                ClienteABuscar.setId_Cliente(cliente.getId_Cliente());
                ClienteABuscar.setNombre(cliente.getNombre());
                ClienteABuscar.setDocumento(cliente.getDocumento());
                ClienteABuscar.setDireccion(cliente.getDireccion());
                ClienteABuscar.setTelefono(cliente.getTelefono());
            }
        }

        return ClienteABuscar;
    
    }

    // Obtener datos de un CLiente mediante su id
    public Cliente obtenerCliente(int id) throws ClassNotFoundException {

        ArrayList<Cliente> clientes = this.leerBinario();
        
        Cliente ClienteABuscar = new Cliente();

        for (Cliente cliente : clientes) {
            if (cliente.getId_Cliente() == id){
                ClienteABuscar.setId_Cliente(cliente.getId_Cliente());
                ClienteABuscar.setNombre(cliente.getNombre());
                ClienteABuscar.setDocumento(cliente.getDocumento());
                ClienteABuscar.setDireccion(cliente.getDireccion());
                ClienteABuscar.setTelefono(cliente.getTelefono());
            }
        }

        return ClienteABuscar;
    
    }



}
