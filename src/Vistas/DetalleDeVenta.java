package Vistas;

import Modelo.Detalle_Venta;
import Modelo.Detalle_Venta_Binario;
import Modelo.Producto;
import Modelo.ProductoBinario;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.awt.event.*;

public class DetalleDeVenta extends JFrame {

  Detalle_Venta_Binario detalleVentaBinario;
  ArrayList<Detalle_Venta> detalle_venta_totales;
  DefaultTableModel modeloTablaDetalleVenta;
  JTable tabla;
  DefaultTableCellRenderer renderer;
  JScrollPane scrollPanel;

  public DetalleDeVenta() throws ClassNotFoundException {

    setTitle("Detalle de Venta");

    // Definimos las columnas de la tabla
    String[] columnas = { "Id Venta", "Id Producto", "Nombre Producto", "Cantidad" };

    // Creamoes el modelo de la tabla y agreamos las columnas
    modeloTablaDetalleVenta = new DefaultTableModel();

    for (String columna : columnas) {
      modeloTablaDetalleVenta.addColumn(columna);
    }

    // Obtenemos los datos

    // Instancio la clase que maneja el archivo binario de productos
    detalleVentaBinario = new Detalle_Venta_Binario();
    detalle_venta_totales = new ArrayList<>();

    System.out.println("HERE1");

    // Traemos todos los productos del archivo binario
    try {
      detalle_venta_totales = detalleVentaBinario.leerBinario();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    
    System.out.println("HERE2");

    // Llenamos el model de la tabla con los datos en detalle_venta_totales ( agragamos
    // filas )
    for (int i = 0; i < detalle_venta_totales.size(); i++) {

      Detalle_Venta detalle_venta = detalle_venta_totales.get(i);

      ProductoBinario productoBinario = new ProductoBinario();

      Producto producto = productoBinario.obtenerProducto(detalle_venta.getId_Producto());

      Object[] fila = {
          detalle_venta.getId_venta(),
          detalle_venta.getId_Producto(),
          producto.getNombre(),
          detalle_venta.getCantidad()
      };

      modeloTablaDetalleVenta.addRow(fila);
    }
    // Creamos la tabla a la que le pasamos el model
    tabla = new JTable(modeloTablaDetalleVenta);
    tabla.setPreferredScrollableViewportSize(new Dimension(700, 150));

    // Creamos un renderer para centrar el contenido de las celdas
    renderer = new DefaultTableCellRenderer();
    renderer.setHorizontalAlignment(SwingConstants.CENTER);
    tabla.setDefaultRenderer(Object.class, renderer);

    // Agregamos la tabla a un panel para que tenga barras de desplazamiento
    scrollPanel = new JScrollPane(tabla);

    // Creamos un panel donde agrenaremos todo
    JPanel panel = new JPanel();
    panel.setLayout(new GridBagLayout());
    GridBagConstraints c;

    // TÍTULO
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 0; // fila 1
    panel.add(new JLabel("Busque el ID de la venta que desea ver los detalles"), c);

    // SEPARADOR
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 1; // fila 1
    panel.add(new JLabel("-------------------------------------------------------------------------------------------"),c);

    // JCOMBOBOX
    JPanel buscarProducto = new JPanel();
    String[] optiones = new String[detalle_venta_totales.size()];

    // Llenamos las opciones
    for (int i = 0; i < detalle_venta_totales.size(); i++) {

      System.out.println(detalle_venta_totales.get(i).toString());
      int idVenta = detalle_venta_totales.get(i).getId_venta();
      optiones[i] = String.valueOf(idVenta);

    }

    Set<String> conjunto = new HashSet<>(Arrays.asList(optiones));
    String[] optionesSinRepetir = conjunto.toArray(new String[0]);

    JComboBox<String> comboBox = new JComboBox<>(optionesSinRepetir);
    buscarProducto.add(new JLabel("ID de la venta: "));
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
    panel.add(new JLabel("-------------------------------------------------------------------------------------------"),
        c);

    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 4; // fila 1
    panel.add(scrollPanel, c);

    add(panel);

    // Configuramos la ventana
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  class RellenerTexto implements ActionListener  {

    public void actionPerformed(ActionEvent e) {

      JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
      String idSeleccionadoStr = (String) comboBox.getSelectedItem();
      int idVentaSeleccionado = Integer.parseInt(idSeleccionadoStr);

      // Creamos la tabla a la que le pasamos el model
      tabla = new JTable(modeloTablaDetalleVenta);
      modeloTablaDetalleVenta.setRowCount(0);

      // Creamos un renderer para centrar el contenido de las celdas
      renderer = new DefaultTableCellRenderer();
    
      renderer.setHorizontalAlignment(SwingConstants.CENTER);
      tabla.setDefaultRenderer(Object.class, renderer);


      // Llenamos el model de la tabla con los datos de compras de un cliente ( agragamos
      // filas )
      for (int i = 0; i < detalle_venta_totales.size(); i++) {

        Detalle_Venta detalle_venta = detalle_venta_totales.get(i);
        
        int id_venta = detalle_venta.getId_venta();

        if ( idVentaSeleccionado == id_venta ) {
          
          ProductoBinario productoBinario = new ProductoBinario();
          Producto producto;
          try {
            
            producto = productoBinario.obtenerProducto(detalle_venta.getId_Producto());
          
            Object[] fila = {
              detalle_venta.getId_venta(),
              detalle_venta.getId_Producto(),
              producto.getNombre(),
              detalle_venta.getCantidad()
            };
  
            modeloTablaDetalleVenta.addRow(fila);

          } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
          }
          

        }
      }

      // Agregamos la tabla a un panel para que tenga barras de desplazamiento
      scrollPanel = new JScrollPane(tabla);

    }

  }


}
