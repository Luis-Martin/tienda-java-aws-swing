package Vistas;

import Modelo.Producto;
import Modelo.ProductoBinario;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.*;
import java.time.LocalDate;

public class EditarProducto extends JFrame {

  public static void main(String[] args) {
    new EditarProducto();
  }

  private JTextPane idProducto;
  private JTextPane nombreEditadoProducto;
  private JTextPane descripcionEditado;
  private JTextPane precioEditado;
  private JTextPane stockEditado;
  private JTextPane fechaProdEditado;
  private JTextPane fechaVendEditado;

  public EditarProducto() {

    setTitle("Editar Producto");

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
    panel.add(new JLabel("Busque producto que desea editar"), c);
    
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
    nombreEditadoProducto = new JTextPane();
    JLabel labelNombre = new JLabel("Nombre : ");
    nombreEditadoProducto.setPreferredSize(new Dimension(200, 20));
    labelNombre.setPreferredSize(new Dimension(100, 20));
    datosProductoNombre.add(labelNombre);
    datosProductoNombre.add(nombreEditadoProducto);
    
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 5; // fila 1
    panel.add(datosProductoNombre, c);

    JPanel datosProductoDescipcion = new JPanel();
    descripcionEditado = new JTextPane();
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
    
    
    JButton editar = new JButton("Editar");
    
    // MANEJAMOS EL EVENTO DEL JCOMBOBOX
    editar.addActionListener(new ActualizarDatos());

    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 12; // fila 1
    panel.add(editar, c);

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
        nombreEditadoProducto.setText(producto.getNombre());
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

  class ActualizarDatos implements ActionListener {
    
    public void actionPerformed(ActionEvent e) {

      ProductoBinario productoBinario = new ProductoBinario();

      int id = Integer.parseInt(idProducto.getText());
      String nombre = nombreEditadoProducto.getText();
      String descripcion = descripcionEditado.getText();
      double precio = Double.parseDouble(precioEditado.getText());
      int stock = Integer.parseInt(stockEditado.getText());
      String fechaDeProduccion = fechaProdEditado.getText();
      String fechaDeVencimiento = fechaVendEditado.getText();

      Producto productoEditado = new Producto(id , nombre, descripcion, precio, stock, fechaDeProduccion,fechaDeVencimiento);

      LocalDate fechaDeProd = LocalDate.parse(fechaDeProduccion);
      LocalDate fechaDeVen = LocalDate.parse(fechaDeVencimiento);

      int anioProduccion = fechaDeProd.getYear();
      int mesProduccion = fechaDeProd.getMonthValue();
      int diaProduccion = fechaDeProd.getDayOfMonth();

      int anioVencimiento = fechaDeVen.getYear();
      int mesVencimiento = fechaDeVen.getMonthValue();
      int diaVencimiento = fechaDeVen.getDayOfMonth();

      Calendar fechaInicio = new GregorianCalendar(anioProduccion, mesProduccion, diaProduccion);
      Calendar fechaFin = new GregorianCalendar(anioVencimiento, mesVencimiento, diaVencimiento);

      int resultado = fechaFin.compareTo(fechaInicio);


      // VALIDACIONES
      
      if (precio <= 0 || stock <= 0 || resultado <= 0 || nombre.isEmpty()) {
        
        JFrame frameAlerta = new JFrame();
        frameAlerta.setTitle("Alerta de Validación");
        frameAlerta.setSize(new Dimension(400,180));
        GridBagConstraints cAlerta;
        JPanel panelGeneral = new JPanel();
        panelGeneral.setLayout(new GridBagLayout());
        
        //Validaciones
        // SEPARADOR
        cAlerta = new GridBagConstraints();
        cAlerta.gridx = 0; // columna 0
        cAlerta.gridy = 0; // fila 1
        JLabel titulo = new JLabel("Verificar las siguientes validaciones");
        titulo.setForeground(Color.RED);
        panelGeneral.add(titulo, cAlerta);

        // SEPARADOR
        cAlerta = new GridBagConstraints();
        cAlerta.gridx = 0; // columna 0
        cAlerta.gridy = 1; // fila 1
        JLabel separdor = new JLabel("-------------------------------------------------------------------------------------------");
        separdor.setForeground(Color.RED);
        panelGeneral.add(separdor, cAlerta);
        
        // Nombre no puede ser nulo
        JPanel panelAertaN = new JPanel();;
        JLabel textoAlertaN = new JLabel("Nombre no tiene que ser nulo");
        textoAlertaN.setForeground(Color.RED);
        panelAertaN.add(textoAlertaN);
        cAlerta = new GridBagConstraints();
        cAlerta.gridx = 0; // columna 0
        cAlerta.gridy = 2; // fila 1
        panelGeneral.add(textoAlertaN, cAlerta);

        //Precio y Stock deben ser mayor a 0
        JPanel panelAertaPS = new JPanel();;
        JLabel textoAlertaPS = new JLabel("Precio y el Stock tiene que se mayor a 0");
        textoAlertaPS.setForeground(Color.RED);
        panelAertaPS.add(textoAlertaPS);
        cAlerta = new GridBagConstraints();
        cAlerta.gridx = 0; // columna 0
        cAlerta.gridy = 3; // fila 1
        panelGeneral.add(textoAlertaPS, cAlerta);
    
        // Fecha de vencimiento debe ser mayor a fecha de producción
        JPanel panelAertaF = new JPanel();;
        JLabel textoAlertaF = new JLabel("Fecha de Vencimiento debe ser posterior a fecha de producción");
        textoAlertaF.setForeground(Color.RED);
        panelAertaF.add(textoAlertaF);
        cAlerta = new GridBagConstraints();
        cAlerta.gridx = 0; // columna 0
        cAlerta.gridy = 4; // fila 1
        panelGeneral.add(textoAlertaF, cAlerta);
        
        // Agragamos el panel y terminamos de configurarlo
        frameAlerta.add(panelGeneral);
        frameAlerta.setVisible(true);
        frameAlerta.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameAlerta.setLocationRelativeTo(null);
      
      } else {

        try {
          productoBinario.editarProducto(productoEditado);
        } catch (ClassNotFoundException e1) {
          e1.printStackTrace();
        }
  
        dispose();
       
      }

    }

  }

}
