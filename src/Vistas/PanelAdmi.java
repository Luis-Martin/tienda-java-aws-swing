package Vistas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// JPanel donde irán 2 paneles:
// 1. Panel superior donde irá el Panel de contendrá en Menú
// 2. Panel central donde irá el Panel que incluirá los datos del Cliente
public class PanelAdmi extends JPanel {

  public PanelAdmi(String nombre, String apellido, String dni) {
    setLayout(new BorderLayout(20, 20));
    add(new PanelMenuAdmi(), BorderLayout.NORTH);
    add(new PanelDatosAdmi(nombre, apellido, dni), BorderLayout.CENTER);
  }

}

// Panel de donde irá la barra de menú
class PanelMenuAdmi extends JPanel {
  PanelMenuAdmi() {
    setLayout(new FlowLayout(FlowLayout.LEFT));
    add(new MenuAdmi());
  }
}

// Barra de menú con las opciones para el administrador con sus acciones respectivas
class MenuAdmi extends JMenuBar {

  MenuAdmi() {

    // Creamos las opciones del menú y lo agregamos a la barra de menú
    JMenu almacen = new JMenu("Productos");
    JMenu clientes = new JMenu("Clientes");
    JMenu ventas = new JMenu("Ventas");
    add(almacen);
    add(clientes);
    add(ventas);

    // Creamos y agregamos las subopciones para PRODUCTO
    JMenuItem verProductos = new JMenuItem("Ver Productos");
    JMenuItem agregarProducto = new JMenuItem("Agregar Producto");
    JMenuItem editarProducto = new JMenuItem("Editar Producto");
    JMenuItem eliminarProducto = new JMenuItem("Eliminar Producto");
    almacen.add(verProductos);
    almacen.addSeparator();
    almacen.add(agregarProducto);
    almacen.addSeparator();
    almacen.add(editarProducto);
    almacen.addSeparator();
    almacen.add(eliminarProducto);

    // Creamos y agregamos las subopciones para CLIENTE
    JMenuItem verClientes = new JMenuItem("Ver Clientes");
    JMenuItem agregarCliente = new JMenuItem("Agregar Cliente");
    JMenuItem editarCliente = new JMenuItem("Editar Cliente");
    JMenuItem eliminarCliente = new JMenuItem("Eliminar Cliente");
    clientes.add(verClientes);
    clientes.addSeparator();
    clientes.add(agregarCliente);
    clientes.addSeparator();
    clientes.add(editarCliente);
    clientes.addSeparator();
    clientes.add(eliminarCliente);

    // Creamos y agregamos las subopciones para VENTA

    JMenuItem verVentas = new JMenuItem("Ver Ventas");
    JMenuItem verDetallesVenta = new JMenuItem("Ver Detalle de Venta");
    JMenuItem verVentasPorCliente = new JMenuItem("Ver Ventas Por Cliente");
    JMenuItem verVentasPorFecha = new JMenuItem("Ver Ventas Por Fecha");
    JMenuItem verVentasPorBoletaOFactura = new JMenuItem("Boletas / Facturas");
    JMenuItem generarVenta = new JMenuItem("Generar Venta");
    ventas.add(verVentas);
    ventas.addSeparator();
    ventas.add(verDetallesVenta);
    ventas.addSeparator();
    ventas.add(verVentasPorCliente);
    ventas.addSeparator();
    ventas.add(verVentasPorFecha);
    ventas.addSeparator();
    ventas.add(verVentasPorBoletaOFactura);
    ventas.addSeparator();
    ventas.add(generarVenta);


    ////////    ACCIONES - PRODUCTO    //////////////////////////////////////////////////////

    /*
     * Frame para ver el listado de Productos al presionar el botón "Ver Productos"
     */
    verProductos.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        
        JFrame floatingFrame = new JFrame();
        
        // Establezca la ubicación de la pantalla flotante en el centro de la pantalla
        floatingFrame.setLocationRelativeTo(null); 


        // Creamos la Tabla de los productos y lo incluimos en el JFrame
        TablaProducto tablaProducto = new TablaProducto();
        floatingFrame.add(tablaProducto);
        
        // visible la pantalla flotante
        floatingFrame.setTitle("Lista de Productos");
        floatingFrame.setVisible(true); 
        floatingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        
      }

    });

    /*
     * Frame para agregar Producto al presionar el botón "Agregar Producto"
     */
    agregarProducto.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        JFrame floatingFrame = new JFrame("Agragar Producto");
        
        // Establezca la ubicación de la pantalla flotante en el centro de la pantalla
        floatingFrame.setLocationRelativeTo(null); 
        // Establezca la operación de cierre cuando se presiona el botón de cierre
        
        // Creamos la Tabla de los productos y lo incluimos en el JFrame
        AgregarProducto agrgarProducto = new AgregarProducto();
        floatingFrame.add(agrgarProducto);
        
        // visible la pantalla flotante
        floatingFrame.setVisible(true); 
        floatingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);       
      
      }

    });

    /*
     * Frame para editar Producto al presionar el botón "Editar Producto"
     */
    editarProducto.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        JFrame floatingFrame = new JFrame("Editar Almacen");
        
        // Establezca la ubicación de la pantalla flotante en el centro de la pantalla
        floatingFrame.setLocationRelativeTo(null); 
        // Establezca la operación de cierre cuando se presiona el botón de cierre
        
        // Creamos la Tabla de los productos y lo incluimos en el JFrame
        EditarProducto editarProducto = new EditarProducto();
        floatingFrame.add(editarProducto);
        
        // visible la pantalla flotante
        floatingFrame.setVisible(true); 
        floatingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      
      }

    });

    /*
     * Frame para eliminar Producto al presionar el botón "Agregar Producto"
     */
    eliminarProducto.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        JFrame floatingFrame = new JFrame("Eliminar Producto");
        
        // Establezca la ubicación de la pantalla flotante en el centro de la pantalla
        floatingFrame.setLocationRelativeTo(null); 
        // Establezca la operación de cierre cuando se presiona el botón de cierre
        
        // Creamos la Tabla de los productos y lo incluimos en el JFrame
        EliminarProducto eliminarProducto = new EliminarProducto();
        floatingFrame.add(eliminarProducto);
        
        // visible la pantalla flotante
        floatingFrame.setVisible(true); 
        floatingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);       
      
      }

    });

    ////////    ACCIONES - CLIENTE    //////////////////////////////////////////////////////

    /*
     * Frame para ver listado de Clientes al presionar el botón "Ver Clientes"
     */
    verClientes.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
      
        JFrame floatingFrame = new JFrame("Lista de Clientes");
        
        // Establezca la ubicación de la pantalla flotante en el centro de la pantalla
        floatingFrame.setLocationRelativeTo(null); 
        // Establezca la operación de cierre cuando se presiona el botón de cierre
        
        // Creamos la Tabla de los productos y lo incluimos en el JFrame
        TablaCliente tablaCliente = new TablaCliente();
        floatingFrame.add(tablaCliente);
        
        // visible la pantalla flotante
        floatingFrame.setVisible(true); 
        floatingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      
      }

    });

    /*
     * Frame para agragar Cliente al presionar el botón "Agregar Cliente"
     */
    agregarCliente.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        
        JFrame floatingFrame = new JFrame("Agregar Cliente");
        
        // Establezca la ubicación de la pantalla flotante en el centro de la pantalla
        floatingFrame.setLocationRelativeTo(null); 
        // Establezca la operación de cierre cuando se presiona el botón de cierre
        
        // Creamos la Tabla de los productos y lo incluimos en el JFrame
        AgregarCliente agregarCliente = new AgregarCliente();
        floatingFrame.add(agregarCliente);
        
        // visible la pantalla flotante
        floatingFrame.setVisible(true); 
        floatingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);        
        
      }

    });

    /*
     * Frame para editar Cliente al presionar el botón "Agregar Cliente"
     */
    editarCliente.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        
        JFrame floatingFrame = new JFrame("Editar Cliente");
        
        // Establezca la ubicación de la pantalla flotante en el centro de la pantalla
        floatingFrame.setLocationRelativeTo(null); 
        // Establezca la operación de cierre cuando se presiona el botón de cierre
        
        // Creamos la Tabla de los productos y lo incluimos en el JFrame
        EditarCliente editarCliente = new EditarCliente();
        floatingFrame.add(editarCliente);
        
        // visible la pantalla flotante
        floatingFrame.setVisible(true); 
        floatingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);        

      }

    });

    /*
     * Frame para eliminar Cliente al presionar el botón "Agregar Cliente"
     */
    eliminarCliente.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        
        JFrame floatingFrame = new JFrame("Eliminar Cliente");
        
        // Establezca la ubicación de la pantalla flotante en el centro de la pantalla
        floatingFrame.setLocationRelativeTo(null); 
        // Establezca la operación de cierre cuando se presiona el botón de cierre
        
        // Creamos la Tabla de los productos y lo incluimos en el JFrame
        EliminarCliente eliminarCliente = new EliminarCliente();
        floatingFrame.add(eliminarCliente);
        
        // visible la pantalla flotante
        floatingFrame.setVisible(true); 
        floatingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);        
        
      }

    });

    ////////    ACCIONES - VENTA    //////////////////////////////////////////////////////

    /*
     * Frame para ver el listado de Ventas al presionar el botón "Ver Ventas"
     */
    verVentas.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        
        JFrame floatingFrame = new JFrame();
        
        // Establezca la ubicación de la pantalla flotante en el centro de la pantalla
        floatingFrame.setLocationRelativeTo(null); 


        // Creamos la Tabla de los productos y lo incluimos en el JFrame
        TablaVenta tablaVenta = new TablaVenta();
        floatingFrame.add(tablaVenta);
        
        // visible la pantalla flotante
        floatingFrame.setTitle("Lista de Ventas");
        floatingFrame.setVisible(true); 
        floatingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        
      }

    });
    
    /*
     * Frame para ver el listado de Ventas al presionar el botón "Ver Detalle Venta"
     */
    verDetallesVenta.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        
        JFrame floatingFrame = new JFrame();
        
        // Establezca la ubicación de la pantalla flotante en el centro de la pantalla
        floatingFrame.setLocationRelativeTo(null); 


        // Creamos la Tabla de los productos y lo incluimos en el JFrame
        DetalleDeVenta tablaVenta;
        try {
          tablaVenta = new DetalleDeVenta();
          floatingFrame.add(tablaVenta);
        } catch (ClassNotFoundException e1) {
          e1.printStackTrace();
        }
        
        // visible la pantalla flotante
        floatingFrame.setTitle("Detalles de Venta");
        floatingFrame.setVisible(true); 
        floatingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        
      }

    });
    
    
    /*
     * Frame para ver el listado de Ventas Por Cliente al presionar el botón "Ver Ventas Por Cliente"
     */
    verVentasPorCliente.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        
        JFrame floatingFrame = new JFrame();
        
        // Establezca la ubicación de la pantalla flotante en el centro de la pantalla
        floatingFrame.setLocationRelativeTo(null); 


        // Creamos la Tabla de los productos y lo incluimos en el JFrame
        TablaVentaPorCliente tablaVenta;
        try {
          tablaVenta = new TablaVentaPorCliente();
          floatingFrame.add(tablaVenta);
        } catch (ClassNotFoundException e1) {
          e1.printStackTrace();
        }
        
        // visible la pantalla flotante
        floatingFrame.setTitle("Lista de Ventas Por Cliente");
        floatingFrame.setVisible(true); 
        floatingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        
      }

    });
    
    /*
     * Frame para ver el listado de Ventas Por Cliente al presionar el botón "Ver Ventas Por Cliente"
     */
    verVentasPorFecha.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        
        JFrame floatingFrame = new JFrame();
        
        // Establezca la ubicación de la pantalla flotante en el centro de la pantalla
        floatingFrame.setLocationRelativeTo(null); 


        // Creamos la Tabla de los productos y lo incluimos en el JFrame
        TablaVentaPorFecha tablaVenta;
        try {
          tablaVenta = new TablaVentaPorFecha();
          floatingFrame.add(tablaVenta);
        } catch (ClassNotFoundException e1) {
          e1.printStackTrace();
        }
        
        // visible la pantalla flotante
        floatingFrame.setTitle("Lista de Ventas Por Cliente");
        floatingFrame.setVisible(true); 
        floatingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        
      }

    });
    
    
    /*
     * Frame para ver el listado de Ventas Por Boleta o Factura al presionar el botón "Boleta / Factura"
     */
    verVentasPorBoletaOFactura.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        
        JFrame floatingFrame = new JFrame();
        
        // Establezca la ubicación de la pantalla flotante en el centro de la pantalla
        floatingFrame.setLocationRelativeTo(null); 


        // Creamos la Tabla de los productos y lo incluimos en el JFrame
        TablaBoletaOFactura tablaVenta;
        try {
          tablaVenta = new TablaBoletaOFactura();
          floatingFrame.add(tablaVenta);
        } catch (ClassNotFoundException e1) {
          e1.printStackTrace();
        }
        
        // visible la pantalla flotante
        floatingFrame.setTitle("Lista de Ventas Por Cliente");
        floatingFrame.setVisible(true); 
        floatingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        
      }

    });
    

    /* 
     * Frame para generar venta y Comprobante al presionar el boton "Generar Venta"
    */
    generarVenta.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        
        JFrame floatingFrame = new JFrame("Eliminar Cliente");
        
        // Establezca la ubicación de la pantalla flotante en el centro de la pantalla
        floatingFrame.setLocationRelativeTo(null); 

        // Creamos la Tabla de los productos y lo incluimos en el JFrame
        FrameVentas generarVenta = new FrameVentas();
        floatingFrame.add(generarVenta);
        
        // visible la pantalla flotante
        floatingFrame.setVisible(true); 
        floatingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);        
        
      }

    });


  }


}

// Panel de donde van los datos del cliente
class PanelDatosAdmi extends JPanel {

  public PanelDatosAdmi(String nombre, String apellido, String dni) {

    // Configurando Layaout que centrará el texto
    setLayout(new GridBagLayout());

    // Definir las restricciones para el Título
    GridBagConstraints c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 1; // fila 0
    this.add(new JLabel("Datos del Trabajador"), c);
    
    // Definir las restricciones para el separador
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 2; // fila 0
    this.add(new JLabel("-------------------------------------"), c);
    
    // Definir las restricciones para el Nombre del administrador
    JPanel panelNombre = new JPanel();
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 3; // fila 0
    JLabel labrelNombre = new JLabel("Nombre :");
    JTextArea textNombre = new JTextArea(nombre);
    labrelNombre.setPreferredSize(new Dimension(100,20));
    textNombre.setPreferredSize(new Dimension(100,20));
    textNombre.setEditable(false);
    panelNombre.add(labrelNombre);
    panelNombre.add(textNombre);
    this.add(panelNombre, c);

    JPanel panelApellido = new JPanel();
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 4; // fila 0
    JLabel labrelApellido = new JLabel("Apellido :");
    JTextArea textApellido = new JTextArea(apellido);
    labrelApellido.setPreferredSize(new Dimension(100,20));
    textApellido.setPreferredSize(new Dimension(100,20));
    textNombre.setEditable(false);
    panelApellido.add(labrelApellido);
    panelApellido.add(textApellido);
    this.add(panelApellido, c);

    JPanel panelDNI = new JPanel();
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 5; // fila 0
    JLabel labrelDni = new JLabel("DNI :");
    JTextArea textDni = new JTextArea(dni);
    labrelDni.setPreferredSize(new Dimension(100,20));
    textDni.setPreferredSize(new Dimension(100,20));
    textDni.setEditable(false);
    panelDNI.add(labrelDni);
    panelDNI.add(textDni);
    this.add(panelDNI, c);

  }

}

