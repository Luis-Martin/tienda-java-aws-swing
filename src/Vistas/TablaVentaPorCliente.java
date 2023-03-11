package Vistas;

import Modelo.Cliente;
import Modelo.ClienteBinario;
import Modelo.Venta;
import Modelo.VentaBinario;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.awt.event.*;

public class TablaVentaPorCliente extends JFrame {

  ArrayList<Venta> ventas_totales;
  DefaultTableModel modeloTablaVenta;
  JTable tabla;
  DefaultTableCellRenderer renderer;
  JScrollPane scrollPanel;
  VentaBinario ventaBinario;
  JTextArea textTotalConsumo;

  public TablaVentaPorCliente() throws ClassNotFoundException {

    setTitle("Lista de Ventas por Cliente");

    // Definimos las columnas de la tabla
    String[] columnas = { "Id Venta", "Id Cliente", "Nombre Cliente", "Monto sin IGV", "Monto con IGV", "Fecha" , "Modalidad" };

    // Obtenemos los datos

    // Instancio la clase que maneja el archivo binario de productos
    ventaBinario = new VentaBinario();
    ventas_totales = new ArrayList<>();

    // Traemos todos los productos del archivo binario
    try {
      ventas_totales = ventaBinario.leerBinario();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    // Creamoes el modelo de la tabla y agreamos las columnas
    modeloTablaVenta = new DefaultTableModel();

    for (String columna : columnas) {
      modeloTablaVenta.addColumn(columna);
    }

    // Llenamos el model de la tabla con los datos en ventas_totales ( agragamos
    // filas )
    for (int i = 0; i < ventas_totales.size(); i++) {

      Venta venta = ventas_totales.get(i);

      ClienteBinario clienteBinario = new ClienteBinario();

      Cliente cliente = clienteBinario.obtenerCliente(venta.getId_cliente());

      Object[] fila = {
          venta.getId_Venta(),
          venta.getId_cliente(),
          cliente.getNombre(),
          venta.getMonto_sin_igv(),
          venta.getMonto_con_igv(),
          venta.getFecha_venta(),
          venta.getModalidad()
      };

      modeloTablaVenta.addRow(fila);
    }
    // Creamos la tabla a la que le pasamos el model
    tabla = new JTable(modeloTablaVenta);
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
    panel.add(new JLabel("Busque el Cliente del cual quiere ver sus compras"), c);

    // SEPARADOR
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 1; // fila 1
    panel.add(new JLabel("-------------------------------------------------------------------------------------------"),c);

    // JCOMBOBOX
    JPanel buscarProducto = new JPanel();
    String[] optiones = new String[ventas_totales.size()];

    for (int i = 0; i < ventas_totales.size(); i++) {

      int idCliente = ventas_totales.get(i).getId_cliente();
      ClienteBinario clienteBinario = new ClienteBinario();
      Cliente cliente;

      cliente = clienteBinario.obtenerCliente(idCliente);
      optiones[i] = cliente.getNombre();

    }

    Set<String> conjunto = new HashSet<>(Arrays.asList(optiones));
    String[] optionesSinRepetir = conjunto.toArray(new String[0]);

    JComboBox<String> comboBox = new JComboBox<>(optionesSinRepetir);
    buscarProducto.add(new JLabel("Nombre de cliente: "));
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

    // SCROLLPANEL
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 4; // fila 1
    panel.add(scrollPanel, c);

    // SEPARADOR
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 5; // fila 1
    panel.add(new JLabel("-------------------------------------------------------------------------------------------"),
        c);

    // TOTAL
    JPanel totalPanel = new JPanel();
    textTotalConsumo = new JTextArea();
    textTotalConsumo.setEditable(false);
    textTotalConsumo.setPreferredSize(new Dimension(50, 20));
    totalPanel.add(new JLabel("Total de Consumo: "));
    totalPanel.add(textTotalConsumo);
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 6; // fila 1
    panel.add(totalPanel, c);    

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
      String nombreSeleccionado = (String) comboBox.getSelectedItem();

      ClienteBinario clienteBinario = new ClienteBinario();

      // Creamos la tabla a la que le pasamos el model
      tabla = new JTable(modeloTablaVenta);
      modeloTablaVenta.setRowCount(0);

      // Creamos un renderer para centrar el contenido de las celdas
      renderer = new DefaultTableCellRenderer();
    
      renderer.setHorizontalAlignment(SwingConstants.CENTER);
      tabla.setDefaultRenderer(Object.class, renderer);


      double totalConsumoPorCliente = 0;

      // Llenamos el model de la tabla con los datos de compras de un cliente ( agragamos
      // filas )
      for (int i = 0; i < ventas_totales.size(); i++) {

        Venta venta = ventas_totales.get(i);
        
        int id_cliente = venta.getId_cliente();
        Cliente cliente;
        Boolean condicion = false;

        try {
          cliente = clienteBinario.obtenerCliente(id_cliente);
          condicion = nombreSeleccionado.equals(cliente.getNombre());

        } catch (ClassNotFoundException e1) {
          e1.printStackTrace();
        }


        if(condicion) {
        
          totalConsumoPorCliente += (double) venta.getMonto_con_igv();

          Object[] fila = {
            venta.getId_Venta(),
            venta.getId_cliente(),
            nombreSeleccionado,
            venta.getMonto_sin_igv(),
            venta.getMonto_con_igv(),
            venta.getFecha_venta(),
            venta.getModalidad()
          };

          modeloTablaVenta.addRow(fila);

        }
      }

      // Agregamos la tabla a un panel para que tenga barras de desplazamiento
      scrollPanel = new JScrollPane(tabla);

      textTotalConsumo.setText(String.valueOf(totalConsumoPorCliente));
    }

  }


}
