package Modelo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;

// import java.io.FileNotFoundException;

// CRUD
public class ProductoBinario {

    // Creamos o sobreescribe sobre el archivo binario con el ArrayList pasado como
    // parámetro
    public void crearBinario(ArrayList<Producto> productosBase) {

        try {

            FileOutputStream archivoOut = new FileOutputStream("./src/productos.dat");
            ObjectOutputStream productoOutput = new ObjectOutputStream(archivoOut);

            // Rellenamos el archivo binario con el ArrayList pasado como parámetro
            for (Producto producto : productosBase) {
                productoOutput.writeObject(producto);
            }

            productoOutput.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    // Leer el archivo binario y te devuelve todos los productos en un array list de
    // productos
    public ArrayList<Producto> leerBinario() throws ClassNotFoundException {

        // ArrayList donde se guardarán todos los productos
        ArrayList<Producto> productos_totales = new ArrayList<>();

        try {

            // Leyendo en el archivo binario
            FileInputStream archivoIn = new FileInputStream("./src/productos.dat");
            ObjectInputStream productoInput = new ObjectInputStream(archivoIn);

            // Rellenamos el ArrayList
            while (archivoIn.available() > 0) {
                Producto producto = (Producto) productoInput.readObject();
                productos_totales.add(producto);
            }

            productoInput.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return productos_totales;

    }

    // Agrega el producto pasado como parámetro al final del archibo binario
    public ArrayList<Producto> agregarProducto(Producto productoNuevo) {

        ArrayList<Producto> productos_totales = new ArrayList<>();

        try {
            // Escribiendo en el final de archivos binario

            // Relleno array con productos totales

            productos_totales = this.leerBinario();
            productos_totales.add(productoNuevo);

            // Sobreescribir el archivo binario
            this.crearBinario(productos_totales);

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return productos_totales;
    }

    // Agrega los productos pasado como parámetro al final del archibo binario
    public ArrayList<Producto> agregarProductos(ArrayList<Producto> productosNuevo) {

        ArrayList<Producto> productos_totales = new ArrayList<>();

        try {
            // Escribiendo en el final de archivos binario

            // Relleno array con productos totales

            productos_totales = this.leerBinario();
            for (Producto producto : productosNuevo) {

                productos_totales.add(producto);
            }

            // Sobreescribir el archivo binario
            this.crearBinario(productos_totales);

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return productos_totales;
    }

    // Elimina el producto con el id_producto pasado como parámetro y te devuelve la
    // nueva lista con el producto eliminado en un ArrayLista
    public ArrayList<Producto> eliminarProducto(int id_producto) throws ClassNotFoundException {

        ArrayList<Producto> productos_totales = new ArrayList<>();
        Producto producto = new Producto();

        // Traemos todos los productos existentes
        productos_totales = this.leerBinario();

        for (int i = 0; i < productos_totales.size(); i++) {

            // Traemos el producto a comparar
            producto = productos_totales.get(i);

            // Comparamos el producto para ver si es el producto a eliminar
            if (producto.getId_Producto() == id_producto) {
                productos_totales.remove(producto);
            }

        }

        // Sobreescribimos los productos ya habiendo eliminado el producto deseado
        this.crearBinario(productos_totales);

        return productos_totales;

    }

    // editar productucto que se pasa por parámetro, editará el producto que tenga
    // el mismo id_producto
    public ArrayList<Producto> editarProducto(Producto productoAEditar) throws ClassNotFoundException {

        ArrayList<Producto> productosTotales = new ArrayList<>();
        ArrayList<Producto> productosTotalesEditado = new ArrayList<>();
        Producto producto = new Producto();

        // Traemos todos los productos existentes
        productosTotales = this.leerBinario();

        for (int i = 0; i < productosTotales.size(); i++) {

            // Traemos el producto a comparar
            producto = productosTotales.get(i);

            // Comparamos el producto para ver si es el producto a eliminar
            if (producto.getId_Producto() == productoAEditar.getId_Producto()) {
                productosTotalesEditado.add(productoAEditar);
            } else {
                productosTotalesEditado.add(producto);
            }

        }

        // Sobreescribimos los productos ya habiendo eliminado el producto deseado
        this.crearBinario(productosTotalesEditado);

        return productosTotalesEditado;

    }

    // Obtener producto por su nombre
    public Producto obtenerProducto(String nombre) throws ClassNotFoundException {

        ArrayList<Producto> productos = this.leerBinario();
        
        Producto productoABuscar = new Producto();

        for (Producto producto : productos) {
            if (producto.getNombre().equals(nombre)) {
                productoABuscar.setId_Producto(producto.getId_Producto());
                productoABuscar.setNombre(producto.getNombre());
                productoABuscar.setDetalle(producto.getDetalle());
                productoABuscar.setPrecio(producto.getPrecio());
                productoABuscar.setStock(producto.getStock());
                productoABuscar.setFecha_produccion(producto.getFecha_produccion());
                productoABuscar.setFecha_vencimiento(producto.getFecha_vencimiento());
            }
        }

        return productoABuscar;
    
    }

    // Obtener producto por su ID
    public Producto obtenerProducto(int id) throws ClassNotFoundException {

        ArrayList<Producto> productos = this.leerBinario();
        
        Producto productoABuscar = new Producto();

        for (Producto producto : productos) {
            if (id == producto.getId_Producto()) {
                productoABuscar.setId_Producto(producto.getId_Producto());
                productoABuscar.setNombre(producto.getNombre());
                productoABuscar.setDetalle(producto.getDetalle());
                productoABuscar.setPrecio(producto.getPrecio());
                productoABuscar.setStock(producto.getStock());
                productoABuscar.setFecha_produccion(producto.getFecha_produccion());
                productoABuscar.setFecha_vencimiento(producto.getFecha_vencimiento());
            }
        }

        return productoABuscar;
    
    }

}
