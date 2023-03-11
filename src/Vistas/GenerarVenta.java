package Vistas;
import Modelo.Cliente;
import Modelo.ClienteBinario;
import Modelo.Producto;
import Modelo.ProductoBinario;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.awt.event.*;

public class GenerarVenta extends JFrame {

  private JTextPane idCliente;
  private JTextPane nombreEditadoCliente;
  private JTextPane documentoEditado;
  private JTextPane telefonoEditado;
  private JTextPane direccionEditado;

  private JTextPane idProducto;
  private JTextPane nombreEditadoProducto;
  private JTextPane descripcionEditado;
  private JTextPane precioEditado;
  private JTextPane fechaProdEditado;
  private JTextPane fechaVendEditado;
  private JTextPane cantidadEditado;

  public GenerarVenta() {

    setTitle("Generar Venta");

    // OBTENEMOS LOS DATOS DE CLIENTES
    
    // Instancio la clase que maneja el archivo binario de clientes
    ClienteBinario clienteBinario = new ClienteBinario();
    ArrayList<Cliente> clientes_totales = new ArrayList<>();
    
    // Traemos todos los clientes del archivo binario
    try {    
      clientes_totales= clienteBinario.leerBinario();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    
    // OBTENEMOS LOS DATOS de PRODUCTO
    
    // Instancio la clase que maneja el archivo binario de productos
    ProductoBinario productoBinario = new ProductoBinario();
    ArrayList<Producto> productos_totales = new ArrayList<>();
    
    // Traemos todos los productos del archivo binario
    try {    
      productos_totales= productoBinario.leerBinario();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    // Creamos un panel para agregar la tabla y configuramos Layout
    JPanel panel = new JPanel();
    panel.setLayout(new GridBagLayout());
    GridBagConstraints c;
    
    // TÍTULO
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 0; // fila 1
    panel.add(new JLabel("Busque el Cliente y los Producos para generar la Venta"), c);
    
    // SEPARADOR
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 1; // fila 1
    panel.add(new JLabel("-------------------------------------------------------------------------------------------"), c);

    // JCOMBOBOX CLIENTE
    JPanel buscarCliente = new JPanel();
    String[] optionesClientes = new String[clientes_totales.size()];
    
    for (int i = 0; i < clientes_totales.size(); i++) {
      optionesClientes[i] = clientes_totales.get(i).getNombre();
    }
    
    JComboBox<String> comboBoxCliente = new JComboBox<>(optionesClientes);
    buscarCliente.add(new JLabel("Nombre de cliente: "));
    buscarCliente.add(comboBoxCliente);

    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 2; // fila 2
    panel.add(buscarCliente, c);

    // MANEJAMOS EL EVENTO DEL JCOMBOBOX CLIENTE
    comboBoxCliente.addActionListener(new RellenerTextoCliente());

    // JCOMBOBOX PRODUCTO
    JPanel buscarProducto = new JPanel();
    String[] optionesProductos = new String[productos_totales.size()];
    
    for (int i = 0; i < productos_totales.size(); i++) {
      optionesProductos[i] = productos_totales.get(i).getNombre();
    }
    
    JComboBox<String> comboBoxProductos = new JComboBox<>(optionesProductos);
    buscarProducto.add(new JLabel("Nombre de producto: "));
    buscarProducto.add(comboBoxProductos);

    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 3; // fila 2
    panel.add(buscarProducto, c);

    // MANEJAMOS EL EVENTO DEL JCOMBOBOX PRODUCTO
    comboBoxProductos.addActionListener(new RellenerTextoProducto());

    // SEPARADOR
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 4; // fila 1
    panel.add(new JLabel("-------------------------------------------------------------------------------------------"), c);
    
    // DATOS - CLIENTE

    // ----ID CLIENTE
    JPanel datosClienteID = new JPanel();
    idCliente = new JTextPane();
    idCliente.setEditable(false);
    JLabel labelIdCliente = new JLabel("ID Cliente: ");
    idCliente.setPreferredSize(new Dimension(200, 20));
    labelIdCliente.setPreferredSize(new Dimension(100, 20));
    datosClienteID.add(labelIdCliente);
    datosClienteID.add(idCliente);

    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 5; // fila 1
    panel.add(datosClienteID, c);

    // ----NOMBRE
    JPanel datosClienteNombre = new JPanel();
    nombreEditadoCliente = new JTextPane();
    nombreEditadoCliente.setEditable(false);
    JLabel labelNombreCliente = new JLabel("Nombre : ");
    nombreEditadoCliente.setPreferredSize(new Dimension(200, 20));
    labelNombreCliente.setPreferredSize(new Dimension(100, 20));
    datosClienteNombre.add(labelNombreCliente);
    datosClienteNombre.add(nombreEditadoCliente);
    
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 6; // fila 1
    panel.add(datosClienteNombre, c);

    // ----DOCUMENTO
    JPanel datosClienteDocumento = new JPanel();
    documentoEditado = new JTextPane();
    documentoEditado.setEditable(false);
    JLabel labelDocumentoCliente= new JLabel("Documento : ");
    documentoEditado.setPreferredSize(new Dimension(200, 20));
    labelDocumentoCliente.setPreferredSize(new Dimension(100, 20));
    datosClienteDocumento.add(labelDocumentoCliente);
    datosClienteDocumento.add(documentoEditado);
    
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 7; // fila 1
    panel.add(datosClienteDocumento, c);

    //  ----DIRECCIÓN
    JPanel datosClienteDireccion = new JPanel();
    direccionEditado = new JTextPane();
    direccionEditado.setEditable(false);
    JLabel labelDireccion = new JLabel("Dirección : ");
    direccionEditado.setPreferredSize(new Dimension(200, 20));
    labelDireccion.setPreferredSize(new Dimension(100, 20));
    datosClienteDireccion.add(labelDireccion);
    datosClienteDireccion.add(direccionEditado);
    
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 8; // fila 1
    panel.add(datosClienteDireccion, c);

    // ----TELÉFONO
    JPanel datosClienteTelefono = new JPanel();
    telefonoEditado = new JTextPane();
    telefonoEditado.setEditable(false);
    JLabel labelTelefonoCliente = new JLabel("Teléfono : ");
    telefonoEditado.setPreferredSize(new Dimension(200, 20));
    labelTelefonoCliente.setPreferredSize(new Dimension(100, 20));
    datosClienteTelefono.add(labelTelefonoCliente);
    datosClienteTelefono.add(telefonoEditado);
    
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 9; // fila 1
    panel.add(datosClienteTelefono, c);

    // DATOS - PRODUCTO

    // ----ID PRODUCTO
    JPanel datosProductoID = new JPanel();
    idProducto = new JTextPane();
    idProducto.setEditable(false);
    idProducto.setEditable(false);
    JLabel labelId = new JLabel("ID Producto: ");
    idProducto.setPreferredSize(new Dimension(200, 20));
    labelId.setPreferredSize(new Dimension(100, 20));
    datosProductoID.add(labelId);
    datosProductoID.add(idProducto);

    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 10; // fila 1
    panel.add(datosProductoID, c);

    // ----NOMBRE PRODUCTO
    JPanel datosProductoNombre = new JPanel();
    nombreEditadoProducto = new JTextPane();
    nombreEditadoProducto.setEditable(false);
    JLabel labelNombre = new JLabel("Nombre : ");
    nombreEditadoProducto.setPreferredSize(new Dimension(200, 20));
    labelNombre.setPreferredSize(new Dimension(100, 20));
    datosProductoNombre.add(labelNombre);
    datosProductoNombre.add(nombreEditadoProducto);
    
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 11; // fila 1
    panel.add(datosProductoNombre, c);

    // ----DESCRIPCION PRODUCTO
    JPanel datosProductoDescipcion = new JPanel();
    descripcionEditado = new JTextPane();
    descripcionEditado.setEditable(false);
    JLabel labelDescipcion= new JLabel("Descripción : ");
    descripcionEditado.setPreferredSize(new Dimension(200, 20));
    labelDescipcion.setPreferredSize(new Dimension(100, 20));
    datosProductoDescipcion.add(labelDescipcion);
    datosProductoDescipcion.add(descripcionEditado);
    
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 12; // fila 1
    panel.add(datosProductoDescipcion, c);
    
    // ----PRECIO
    JPanel datosProductoPrecio = new JPanel();
    precioEditado = new JTextPane();
    precioEditado.setEditable(false);
    JLabel labelPrecio = new JLabel("Precio : ");
    precioEditado.setPreferredSize(new Dimension(200, 20));
    labelPrecio.setPreferredSize(new Dimension(100, 20));
    datosProductoPrecio.add(labelPrecio);
    datosProductoPrecio.add(precioEditado);
    
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 13; // fila 1
    panel.add(datosProductoPrecio, c);

    // ----FECHA DE PRODUCCION
    JPanel datosProductoFechaProd = new JPanel();
    fechaProdEditado = new JTextPane();
    fechaProdEditado.setEditable(false);
    JLabel labelFehcaProd = new JLabel("Producción : ");
    fechaProdEditado.setPreferredSize(new Dimension(200, 20));
    labelFehcaProd.setPreferredSize(new Dimension(100, 20));
    datosProductoFechaProd.add(labelFehcaProd);
    datosProductoFechaProd.add(fechaProdEditado);
    
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 15; // fila 1
    panel.add(datosProductoFechaProd, c);

    // ----FECHA DE VENCIMIENTO
    JPanel datosProductoFechaVen = new JPanel();
    fechaVendEditado = new JTextPane();
    fechaVendEditado.setEditable(false);
    JLabel lableFechaVen = new JLabel("Vencicimento : ");
    fechaVendEditado.setPreferredSize(new Dimension(200, 20));
    lableFechaVen.setPreferredSize(new Dimension(100, 20));
    datosProductoFechaVen.add(lableFechaVen);
    datosProductoFechaVen.add(fechaVendEditado);
    
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 16; // fila 1
    panel.add(datosProductoFechaVen, c);

    // ----CANTIDAD DE PRODUCTOS A LLEVAR
    JPanel datosProductoCantidad = new JPanel();
    cantidadEditado = new JTextPane();
    JLabel cantidadProducto = new JLabel("Cantidad : ");
    cantidadEditado.setPreferredSize(new Dimension(200, 20));
    cantidadProducto.setPreferredSize(new Dimension(100, 20));
    datosProductoCantidad.add(cantidadProducto);
    datosProductoCantidad.add(cantidadEditado);
    
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 17; // fila 1
    panel.add(datosProductoCantidad, c);


    JButton agregar = new JButton("Agragr a la Lista");
    
    // MANEJAMOS EL EVENTO DEL JCOMBOBOX
    agregar.addActionListener(new AgregarDato());

    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 21; // fila 1
    panel.add(agregar, c);


    // Division
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 20; // fila 1
    panel.add(new JLabel("-------------------------------------------------------------------------------------------"), c);
    
    
    // Agregamos el JPanel al Jframe
    add(panel);

    // Configuramos la ventana
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  class RellenerTextoCliente implements ActionListener {
    
    public void actionPerformed(ActionEvent e) {
        
      JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
      String nombreSeleccionado = (String) comboBox.getSelectedItem();

      ClienteBinario clienteBinario = new ClienteBinario();
      
      try {

        Cliente cliente = clienteBinario.obtenerCliente(nombreSeleccionado);
        
        String idString = String.valueOf(cliente.getId_Cliente());

        idCliente.setText(idString);
        nombreEditadoCliente.setText(cliente.getNombre());
        documentoEditado.setText(cliente.getDocumento());
        telefonoEditado.setText(cliente.getTelefono());
        direccionEditado.setText(cliente.getDireccion());

      } catch (ClassNotFoundException error) {
        error.printStackTrace();
      }

    }

  }

  
  class RellenerTextoProducto implements ActionListener {
    
    public void actionPerformed(ActionEvent e) {
        
      JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
      String nombreSeleccionado = (String) comboBox.getSelectedItem();

      ProductoBinario productoBinario = new ProductoBinario();
      
      try {

        Producto producto = productoBinario.obtenerProducto(nombreSeleccionado);
        
        System.out.println(producto.toString());

        String idString = String.valueOf(producto.getId_Producto());

        String precioString = String.valueOf(producto.getPrecio());
        // String stockString = String.valueOf(producto.getStock());

        idProducto.setText(idString);
        nombreEditadoProducto.setText(producto.getNombre());
        descripcionEditado.setText(producto.getDetalle());
        precioEditado.setText(precioString);
        // stockEditado.setText(stockString);
        fechaProdEditado.setText(producto.getFecha_produccion());
        fechaVendEditado.setText(producto.getFecha_vencimiento());

      } catch (ClassNotFoundException error) {
        error.printStackTrace();
      }

    }

  }


  class AgregarDato implements ActionListener {
    
    public void actionPerformed(ActionEvent e) {

      // ClienteBinario clienteBinario = new ClienteBinario();

      // int id = Integer.parseInt(idCliente.getText());
      // String nombre = nombreEditadoCliente.getText();
      // String documenteo = documentoEditado.getText();
      // String telefono = telefonoEditado.getText();
      // String direccion = direccionEditado.getText();

      // Cliente ClienteEditado = new Cliente(id, nombre, documenteo, direccion, telefono);

      // try {
      //   clienteBinario.editarCliente(ClienteEditado);
      // } catch (ClassNotFoundException e1) {
      //   e1.printStackTrace();
      // }

      // dispose();
    }

  }

}
