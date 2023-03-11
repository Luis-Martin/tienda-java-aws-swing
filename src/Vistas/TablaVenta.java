package Vistas;

import Modelo.Cliente;
import Modelo.ClienteBinario;
import Modelo.Venta;
import Modelo.VentaBinario;

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

public class TablaVenta extends JFrame {

  ArrayList<Venta> ventas_totales;
  JTable tablaDetalleVenta;
  DefaultTableCellRenderer rendererDetalleVenta;
  DefaultTableModel modeloTablaDetalleVenta;
  JScrollPane scrollPaneDetalle;

  public TablaVenta() {

    setTitle("Lista de Ventas");

    // Definimos las columnas de la tabla
    // String[] columnas = { "Id", "Id Cliente", "Nombre Cliente", "Monto sin IGV", "Monto con IGV", "Modalidad" };

    // Creamos un panel para agregar todos los elemento
    JPanel panel = new JPanel();
    panel.setLayout(new GridBagLayout());
    GridBagConstraints c;

    // Obtenemos los datos para llenar la tabla de Ventas

    // Instancio la clase que maneja el archivo binario de productos
    VentaBinario ventaBinario = new VentaBinario();
    ventas_totales = new ArrayList<>();

    // Traemos todos los productos del archivo binario
    try {
      ventas_totales = ventaBinario.leerBinario();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    // TÍTULO
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 0; // fila 1
    panel.add(new JLabel("Listado General de Ventas Realizadas"), c);

    // SEPARADOR
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 1; // fila 1
    panel.add(new JLabel("-------------------------------------------------------------------------------------------"),
        c);

    // TABLA
    JScrollPane scrollPane = this.obtenerTablaDeVentas();
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 2; // fila 1
    JPanel panelSJPanel = new JPanel();
    panelSJPanel.add(scrollPane);
    panel.add(panelSJPanel, c);

    add(panel);

    // Configuramos la ventana
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  class TablaDeVentas {
    public TablaDeVentas() {

    }
  }

  private JScrollPane obtenerTablaDeVentas() {

    String[] columnas = { "Id", "Id Cliente", "Nombre Cliente", "Monto sin IGV", "Monto con IGV","Fecha", "Modalidad" };

    // Creamoes el modelo de la tabla y agreamos las columnas
    DefaultTableModel modeloTablaVenta = new DefaultTableModel();

    for (String columna : columnas) {
      modeloTablaVenta.addColumn(columna);
    }

    // Llenamos el model de la tabla con los datos en ventas_totales ( agragamos
    // filas )
    for (int i = 0; i < ventas_totales.size(); i++) {

      Venta venta = ventas_totales.get(i);

      int idCliente = venta.getId_cliente();
      ClienteBinario clienteBinario = new ClienteBinario();
      try {

        Cliente cliente = clienteBinario.obtenerCliente(idCliente);

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

      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }

    }
    
    // Creamos la tabla a la que le pasamos el model
    JTable tabla = new JTable(modeloTablaVenta);
    tabla.setPreferredScrollableViewportSize(new Dimension(700, 150));

    // Creamos un renderer para centrar el contenido de las celdas
    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    renderer.setHorizontalAlignment(SwingConstants.CENTER);
    tabla.setDefaultRenderer(Object.class, renderer);

    // Agregamos la tabla a un panel para que tenga barras de desplazamiento
    JScrollPane scrollPane = new JScrollPane(tabla);

    return scrollPane;

  }

}
