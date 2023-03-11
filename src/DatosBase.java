import java.util.ArrayList;

import Modelo.Cliente;
import Modelo.ClienteBinario;
import Modelo.Comprobante;
import Modelo.ComprobanteBinario;
import Modelo.Detalle_Venta;
import Modelo.Detalle_Venta_Binario;
import Modelo.Producto;
import Modelo.ProductoBinario;
import Modelo.Trabajador;
import Modelo.TrabajadorBinario;
import Modelo.Venta;
import Modelo.VentaBinario;

public class DatosBase {

  public static void main(String[] args) {

    /*
     * INGRESAMOS DATOS DE PRODUCTOS
     */

    // Datos base de Productos
    Object[][] datosProductos = {
        { 101, "arroz", "1 kg de arroz", 4.50, 20, "2022-10-10", "2023-10-10" },
        { 102, "papa", "1 kg de papa", 2.50, 40, "2022-11-11", "2023-04-10" },
        { 103, "huevo", "1 kg de huevo", 9.50, 8, "2022-09-01", "2023-10-10" },
        { 104, "aceite", "1 ltr de aceite", 9.20, 26, "2022-10-10", "2023-10-10" },
        { 105, "camote", "1kg de camote", 3.20, 21, "2022-12-10", "2023-06-10" },
        { 106, "leche1", "tarro de leche", 4.5, 20, "2022-10-10", "2023-11-10" },
        { 107, "leche2", "leche en bolsa", 4.2, 12, "2022-12-10", "2023-12-10" },
        { 108, "azucar", "1kg de azucar", 6.0, 20, "2022-10-10", "2023-10-10" },
        { 109, "sal", "1kg de sal", 4.5, 20, "2022-10-10", "2023-10-10" },
        { 110, "cebolla", "1kg de cebolla", 2.5, 20, "2022-10-10", "2023-10-10" }
    };

    // Arrayliste que será pasado como parámetro al para introducir los datos al
    // archivo binario
    ArrayList<Producto> productosBase = new ArrayList<Producto>();

    // Recorremos el array para llenar el array list de productos Base
    for (Object[] objetoProducto : datosProductos) {

      Producto producto = new Producto();
      // Rellenemos los atributos de la clase producto
      producto.setId_Producto((int) objetoProducto[0]);
      producto.setNombre((String) objetoProducto[1]);
      producto.setDetalle((String) objetoProducto[2]);
      producto.setPrecio((double) objetoProducto[3]);
      producto.setStock((int) objetoProducto[4]);
      producto.setFecha_produccion((String) objetoProducto[5]);
      producto.setFecha_vencimiento((String) objetoProducto[6]);

      // Agreagamos el producto al arraylist
      productosBase.add(producto);
    }

    System.out.println("--Introducimos Productos Base---------");

    // Imprimimos los datos que vamos a introducir
    for (Producto productoEnArrayList : productosBase) {
      System.out.println(productoEnArrayList.toString());
    }

    // Instanciamos la clase ProductoBinario y lo llenamos en el archivo binario
    ProductoBinario productoBinario = new ProductoBinario();
    productoBinario.crearBinario(productosBase);

    /*
     * INGRESAMOS DATOS DE CLIENTES
     */

    // Datos base de Productos
    Object[][] datosCliente = {
        { 201, "Francisco Caqui", "76025525", "Salamanca", "981453194" },
        { 202, "José Morales", "76025526", "Salaverry", "981453195" },
        { 203, "MORCA S.A.C", "11111111111", "Alfonso Ugarte", "981453196" },
        { 204, "UNFV CORP", "22222222222", "Cieza de Leon", "981453197" },
        { 205, "Manuel Arias", "76025529", "Arica", "981453198" },
        { 206, "Diego Fuentes", "76025530", "Tarapaca", "981453199" },
        { 207, "David Jesus", "76025531", "Quechuas", "981453200" },
        { 208, "MASS TIENDAS S.A.C", "33333333333", "Ate", "981453201" },
        { 209, "PLAZA VEA PERU", "44444444444", "Sicuany", "981453202" },
        { 210, "Pedro Pascal", "76025534", "San luis", "981453203" }
    };

    // Arrayliste que será pasado como parámetro al para introducir los datos al
    // archivo binario
    ArrayList<Cliente> clientesBase = new ArrayList<Cliente>();

    // Recorremos el array para llenar el array list de productos Base
    for (Object[] objetoProducto : datosCliente) {

      Cliente cliente = new Cliente();
      // Rellenemos los atributos de la clase producto
      cliente.setId_Cliente((int) objetoProducto[0]);
      cliente.setNombre((String) objetoProducto[1]);
      cliente.setDocumento((String) objetoProducto[2]);
      cliente.setDireccion((String) objetoProducto[3]);
      cliente.setTelefono((String) objetoProducto[4]);

      // Agreagamos el producto al arraylist
      clientesBase.add(cliente);
    }

    System.out.println("--Introducimos Clientes Base---------");

    // Imprimimos los datos que vamos a introducir
    for (Cliente productoEnArrayList : clientesBase) {
      System.out.println(productoEnArrayList.toString());
    }

    // Instanciamos la clase ProductoBinario y llenamos el archivo binario
    ClienteBinario clienteBinario = new ClienteBinario();
    clienteBinario.crearBinario(clientesBase);

    /*
     * INGRESAMOS DATOS DE VENTA
     */

    // Datos base de Productos
    Object[][] datosVenta = {
        { 301, 201, "103.73", "126.5", "2023-02-07",  "tarjeta" },
        { 302, 202, "84.9", "100.2", "2023-02-08", "tarjeta" },
        { 303, 203, "75.8", "89.4", "2023-02-09" ,"efectivo" },
        { 304, 204, "86.9", "102.6", "2023-02-10", "tarjeta" },
        { 305, 205, "85.6", "101.0", "2023-02-07", "tarjeta" },
        { 306, 206, "25.3", "32.0", "2023-02-07", "efectivo" }
    };

    // Arrayliste que será pasado como parámetro al para introducir los datos al
    // archivo binario
    ArrayList<Venta> ventasBase = new ArrayList<Venta>();

    // Recorremos el array para llenar el array list de productos Base
    for (Object[] objetoVenta : datosVenta) {

      Venta venta = new Venta();
      // Rellenemos los atributos de la clase producto
      venta.setId_Venta((int) objetoVenta[0]);
      venta.setId_cliente((int) objetoVenta[1]);
      venta.setMonto_sin_igv(Double.parseDouble((String) objetoVenta[2]));
      venta.setMonto_con_igv(Double.parseDouble((String) objetoVenta[3]));
      venta.setFecha_venta((String) objetoVenta[4]);
      venta.setModalidad((String) objetoVenta[5]);

      // Agreagamos el producto al arraylist
      ventasBase.add(venta);
    }

    System.out.println("--Introducimos Ventas Base---------");

    // Imprimimos los datos que vamos a introducir
    for (Venta ventaEnArrayList : ventasBase) {
      System.out.println(ventaEnArrayList.toString());
    }

    // Instanciamos la clase ProductoBinario y llenamos el archivo binario
    VentaBinario ventaBinario = new VentaBinario();
    ventaBinario.crearBinario(ventasBase);

    /*
     * INGRESAMOS DATOS DE DETALLE VENTA
     */

    Object[][] datosDetalleVenta = {
        { 301, 101, 5 },
        { 301, 102, 2 },
        { 301, 106, 12 },
        { 302, 108, 10 },
        { 302, 109, 10 },
        { 302, 107, 6 },
        { 303, 106, 7 },
        { 303, 108, 10 },
        { 304, 107, 23 },
        { 304, 109, 4 },
        { 305, 107, 2 },
        { 305, 106, 7 },
        { 305, 110, 5 },
        { 305, 108, 6 },
        { 306, 105, 10 }
    };

    // Arrayliste que será pasado como parámetro al para introducir los datos al
    // archivo binario
    ArrayList<Detalle_Venta> detalleventasBase = new ArrayList<>();

    // Recorremos el array para llenar el array list de productos Base
    for (Object[] objetoDellateVenta : datosDetalleVenta) {

      Detalle_Venta detalle_venta = new Detalle_Venta();
      // Rellenemos los atributos de la clase producto
      detalle_venta.setId_venta((int) objetoDellateVenta[0]);
      detalle_venta.setId_Producto((int) objetoDellateVenta[1]);
      detalle_venta.setCantidad((int) objetoDellateVenta[2]);

      // Agreagamos el producto al arraylist
      detalleventasBase.add(detalle_venta);
    }

    System.out.println("--Introducimos Dettalle Venta Base---------");

    // Imprimimos los datos que vamos a introducir
    for (Detalle_Venta detalleVentaEnArrayList : detalleventasBase) {
      System.out.println(detalleVentaEnArrayList.toString());
    }

    // Instanciamos la clase ProductoBinario y llenamos el archivo binario
    Detalle_Venta_Binario detalleVentaBinario = new Detalle_Venta_Binario();
    detalleVentaBinario.crearBinario(detalleventasBase);

    /*
     * INGRESAMOS DATOS DE COMPROBANTE
     */

    Object[][] datosComprobante = {
      {301,"boleta","2023-03-07"},
      {302,"boleta","2023-03-08"},
      {303,"factura","2023-03-09"},
      {304,"factura","2023-03-10"},
      {305,"boleta","2023-03-11"},
      {306,"boleta","2023-03-12"}
    };

    // Arrayliste que será pasado como parámetro al para introducir los datos al
    // archivo binario
    ArrayList<Comprobante> comprobanteBase = new ArrayList<>();

    // Recorremos el array para llenar el array list de productos Base
    for (Object[] objetoComprobante : datosComprobante) {

      Comprobante comprobante = new Comprobante();
      // Rellenemos los atributos de la clase producto
      comprobante.setId_venta((int) objetoComprobante[0]);
      comprobante.setTipo_comprobante((String) objetoComprobante[1]);
      comprobante.setFecha_emision((String) objetoComprobante[2]);

      // Agreagamos el producto al arraylist
      comprobanteBase.add(comprobante);
    }

    System.out.println("--Introducimos Comprobante Base---------");

    // Imprimimos los datos que vamos a introducir
    for (Comprobante comprobanteEnArrayList : comprobanteBase) {
      System.out.println(comprobanteEnArrayList.toString());
    }

    // Instanciamos la clase ProductoBinario y llenamos el archivo binario
    ComprobanteBinario comprobanteBinario = new ComprobanteBinario();
    comprobanteBinario.crearBinario(comprobanteBase);

    /*
     * INGRESAMOS DATOS DE Los TRABAJADOEs
     */

    Object[][] datosTrabajadores = {
      {"Luis", "Martin", "75345684", "luis123"},
      {"Victoria", "Huerta", "7459864", "victoria123"},
      {"Jose", "Morales", "71864962", "jose123"},
    };

    // Arrayliste que será pasado como parámetro al para introducir los datos al
    // archivo binario
    ArrayList<Trabajador> TrabajadoresBase = new ArrayList<>();

    // Recorremos el array para llenar el array list de productos Base
    for (Object[] objetoTrabajador : datosTrabajadores) {

      Trabajador comprobante = new Trabajador();
      // Rellenemos los atributos de la clase producto
      comprobante.setNombre((String)objetoTrabajador[0]);
      comprobante.setApellido((String) objetoTrabajador[1]);
      comprobante.setDni((String) objetoTrabajador[2]);
      comprobante.setContrasenia((String) objetoTrabajador[3]);

      // Agreagamos el producto al arraylist
      TrabajadoresBase.add(comprobante);
    }

    System.out.println("--Introducimos Comprobante Base---------");

    // Imprimimos los datos que vamos a introducir
    for (Trabajador TrabajadorEnArrayList : TrabajadoresBase) {
      System.out.println(TrabajadorEnArrayList.toString());
    }

    // Instanciamos la clase ProductoBinario y llenamos el archivo binario
    TrabajadorBinario trabajadorBinario = new TrabajadorBinario();
    trabajadorBinario.crearBinario(TrabajadoresBase);


  }

}
