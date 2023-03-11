package Vistas;

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

public class EliminarProducto extends JFrame {

  private JTextPane idProducto;
  private JTextPane nombreEditado;
  private JTextPane descripcionEditado;
  private JTextPane precioEditado;
  private JTextPane stockEditado;
  private JTextPane fechaProdEditado;
  private JTextPane fechaVendEditado;

  public EliminarProducto() {

    setTitle("Eliminar Producto");

    // Definimos las columnas de la tabla
    // String[] columnas = { "Id", "Nombre", "Descripción", "Precio", "Stock", "Producción", "Vencimiento" };

    // OBTENEMOS LOS DATOS
    
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
    panel.add(new JLabel("Seleccione producto a eliminar"), c);
    
    // SEPARADOR
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 1; // fila 1
    panel.add(new JLabel("-------------------------------------------------------------------------------------------"), c);
    
    // JCOMBOBOX
    JPanel buscarProducto = new JPanel();
    String[] optiones = new String[productos_totales.size()];
    
    for (int i = 0; i < productos_totales.size(); i++) {
      optiones[i] = productos_totales.get(i).getNombre();
    }
    
    JComboBox<String> comboBox = new JComboBox<>(optiones);
    buscarProducto.add(new JLabel("Nombre de producto: "));
    buscarProducto.add(comboBox);

    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 2; // fila 2
    panel.add(buscarProducto, c);

    // MANEJAMOS EL EVENTO DEL JCOMBOBOX
    comboBox.addActionListener(new RellenerTexto());

    // SEPARADOR
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 3; // fila 1
    panel.add(new JLabel("-------------------------------------------------------------------------------------------"), c);
    
    // DATOS A EDITAR

    JPanel datosProductoID = new JPanel();
    idProducto = new JTextPane();
    idProducto.setEditable(false);
    JLabel labelId = new JLabel("ID : ");
    idProducto.setPreferredSize(new Dimension(200, 20));
    labelId.setPreferredSize(new Dimension(100, 20));
    datosProductoID.add(labelId);
    datosProductoID.add(idProducto);

    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 4; // fila 1
    panel.add(datosProductoID, c);

    JPanel datosProductoNombre = new JPanel();
    nombreEditado = new JTextPane();
    nombreEditado.setEditable(false);
    JLabel labelNombre = new JLabel("Nombre : ");
    nombreEditado.setPreferredSize(new Dimension(200, 20));
    labelNombre.setPreferredSize(new Dimension(100, 20));
    datosProductoNombre.add(labelNombre);
    datosProductoNombre.add(nombreEditado);
    
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 5; // fila 1
    panel.add(datosProductoNombre, c);

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
    c.gridy = 6; // fila 1
    panel.add(datosProductoDescipcion, c);
    
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
    c.gridy = 7; // fila 1
    panel.add(datosProductoPrecio, c);

    JPanel datosProductoStock = new JPanel();
    stockEditado = new JTextPane();
    stockEditado.setEditable(false);
    JLabel labelStock = new JLabel("Stock : ");
    stockEditado.setPreferredSize(new Dimension(200, 20));
    labelStock.setPreferredSize(new Dimension(100, 20));
    datosProductoStock.add(labelStock);
    datosProductoStock.add(stockEditado);
    
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 8; // fila 1
    panel.add(datosProductoStock, c);

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
    c.gridy = 9; // fila 1
    panel.add(datosProductoFechaProd, c);

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
    c.gridy = 10; // fila 1
    panel.add(datosProductoFechaVen, c);

    // Divisiom
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 11; // fila 1
    panel.add(new JLabel("-------------------------------------------------------------------------------------------"), c);
    
    
    JButton eliminar = new JButton("Eliminar");
    
    // MANEJAMOS EL EVENTO DEL JCOMBOBOX
    eliminar.addActionListener(new EliminarDatos());

    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 12; // fila 1
    panel.add(eliminar, c);

    // Agregamos el JPanel al Jframe
    add(panel);

    // Configuramos la ventana
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  class RellenerTexto implements ActionListener {
    
    public void actionPerformed(ActionEvent e) {
        
      JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
      String nombreSeleccionado = (String) comboBox.getSelectedItem();

      ProductoBinario productoBinario = new ProductoBinario();
      
      try {

        Producto producto = productoBinario.obtenerProducto(nombreSeleccionado);

        System.out.println(producto.toString());

        String idString = String.valueOf(producto.getId_Producto());

        String precioString = String.valueOf(producto.getPrecio());
        String stockString = String.valueOf(producto.getStock());

        idProducto.setText(idString);
        nombreEditado.setText(producto.getNombre());
        descripcionEditado.setText(producto.getDetalle());
        precioEditado.setText(precioString);
        stockEditado.setText(stockString);
        fechaProdEditado.setText(producto.getFecha_produccion());
        fechaVendEditado.setText(producto.getFecha_vencimiento());

      } catch (ClassNotFoundException error) {
        error.printStackTrace();
      }

    }

  }

  class EliminarDatos implements ActionListener {
    
    public void actionPerformed(ActionEvent e) {

      ProductoBinario productoBinario = new ProductoBinario();

      try {
        productoBinario.eliminarProducto(Integer.parseInt(idProducto.getText()));
      } catch (ClassNotFoundException e1) {
        e1.printStackTrace();
      }

      dispose();
    }

  }

}
