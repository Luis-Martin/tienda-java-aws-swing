package Vistas;

import Modelo.Cliente;
import Modelo.ClienteBinario;
import Modelo.Venta;
import Modelo.VentaBinario;

import javax.swing.JButton;
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
import java.util.Calendar;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.GregorianCalendar;

public class TablaVentaPorFecha extends JFrame {

  ArrayList<Venta> ventas_totales;
  DefaultTableModel modeloTablaVenta;
  JTable tabla;
  DefaultTableCellRenderer renderer;
  JScrollPane scrollPanel;
  VentaBinario ventaBinario;

  JTextArea textFechaDeInicio;
  JTextArea textFechaDeFin;

  public TablaVentaPorFecha() throws ClassNotFoundException {

    setTitle("Lista de Ventas por Cliente");

    // Definimos las columnas de la tabla
    String[] columnas = { "Id Venta", "Id Cliente", "Nombre Cliente", "Monto sin IGV", "Monto con IGV", "Fecha",
        "Modalidad" };

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
    panel.add(new JLabel("Coloque el rango de fecha del cual desea ver las Ventas"), c);

    // SEPARADOR
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 1; // fila 1
    panel.add(new JLabel("-------------------------------------------------------------------------------------------"),
        c);

    // FECHA DE INICIO
    JPanel fechaDeInicio = new JPanel();

    textFechaDeInicio = new JTextArea();
    textFechaDeInicio.setPreferredSize(new Dimension(100, 20));
    JLabel labelFechaDeInicio = new JLabel("Fecha de inicio (incluido) : ");
    labelFechaDeInicio.setPreferredSize(new Dimension(150, 20));
    fechaDeInicio.add(labelFechaDeInicio);
    fechaDeInicio.add(textFechaDeInicio);

    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 2; // fila 2
    panel.add(fechaDeInicio, c);

    // FECHA DE FIN
    JPanel fechaDeFin = new JPanel();

    textFechaDeFin = new JTextArea();
    textFechaDeFin.setPreferredSize(new Dimension(100, 20));
    JLabel labelFechaDeFin = new JLabel("Fecha de fin (no incluido) : ");
    labelFechaDeFin.setPreferredSize(new Dimension(150, 20));
    fechaDeFin.add(labelFechaDeFin);
    fechaDeFin.add(textFechaDeFin);

    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 3; // fila 2
    panel.add(fechaDeFin, c);

    // BOTON
    JButton buscarEnRando = new JButton("Buscar en rango");
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 4; // fila 2
    panel.add(buscarEnRando, c);

    // Controlar el evento
    buscarEnRando.addActionListener(new ActualizarTabla());

    // SEPARADOR
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 5; // fila 1
    panel.add(new JLabel("-------------------------------------------------------------------------------------------"),
        c);

    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 6; // fila 1
    panel.add(scrollPanel, c);

    add(panel);

    // Configuramos la ventana
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);

  }

  class ActualizarTabla implements ActionListener {

    public void actionPerformed(ActionEvent e) {

      ClienteBinario clienteBinario = new ClienteBinario();

      String strFechaDeInicio = textFechaDeInicio.getText();
      String strFechaDeFin = textFechaDeFin.getText();

      LocalDate fechaDeInicio = LocalDate.parse(strFechaDeInicio);
      LocalDate fechaDeFin = LocalDate.parse(strFechaDeFin);

      int anioInicio = fechaDeInicio.getYear();
      int mesInicio = fechaDeInicio.getMonthValue();
      int diaInicio = fechaDeInicio.getDayOfMonth();

      int anioFin = fechaDeFin.getYear();
      int mesFin = fechaDeFin.getMonthValue();
      int diaFin = fechaDeFin.getDayOfMonth();


      // Creamos la tabla a la que le pasamos el model
      tabla = new JTable(modeloTablaVenta);
      modeloTablaVenta.setRowCount(0);

      // Creamos un renderer para centrar el contenido de las celdas
      renderer = new DefaultTableCellRenderer();

      renderer.setHorizontalAlignment(SwingConstants.CENTER);
      tabla.setDefaultRenderer(Object.class, renderer);

      try {
        ventas_totales = ventaBinario.leerBinario();
      } catch (ClassNotFoundException e1) {
        e1.printStackTrace();
      }
      // Llenamos el model de la tabla con los datos de compras de un cliente
      // ( agragamos filas )

      for (Venta venta : ventas_totales) {

        System.out.println(anioInicio + "/" + mesInicio + "/" + diaInicio);
        System.out.println(anioFin + "/" + mesFin + "/" + diaFin);

        System.out.println(venta.toString());

        int id_cliente = venta.getId_cliente();
        Cliente cliente;
        String nombreCliente = "";

        try {
          cliente = clienteBinario.obtenerCliente(id_cliente);
          nombreCliente = cliente.getNombre();

        } catch (ClassNotFoundException e1) {
          System.out.println("ClassNotFoundException");
          e1.printStackTrace();
        }

        Calendar fechaInicio = new GregorianCalendar(anioInicio, mesInicio, diaInicio);
        Calendar fechaFin = new GregorianCalendar(anioFin, mesFin, diaFin);

        LocalDate fechaL = LocalDate.parse(venta.getFecha_venta());

        int anio = fechaL.getYear();
        int mes = fechaL.getMonthValue();
        int dia = fechaL.getDayOfMonth();

        System.out.println(anio + "/" + mes + "/" + dia);

        Calendar fecha = new GregorianCalendar(anio, mes, dia);

        int resultado1 = fecha.compareTo(fechaInicio);
        int resultado2 = fecha.compareTo(fechaFin);

        // Condición para agregar valor a tabla
        if (resultado1 >= 0 && resultado2 < 0) {

          Object[] fila = {
              venta.getId_Venta(),
              venta.getId_cliente(),
              nombreCliente,
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

    }

  }

}
