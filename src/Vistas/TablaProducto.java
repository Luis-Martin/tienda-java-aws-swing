package Vistas;

import Modelo.Producto;
import Modelo.ProductoBinario;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.GridBagLayout;
import java.util.ArrayList;;

public class TablaProducto extends JFrame {
  
  public TablaProducto() {

    setTitle("Lista de Productos");

    // Definimos las columnas de la tabla
    String[] columnas = { "Id", "Nombre", "Descripción", "Precio", "Stock", "Producción", "Vencimiento" };

    // Obtenemos los datos 
    
    // Instancio la clase que maneja el archivo binario de productos
    ProductoBinario productoBinario = new ProductoBinario();
    ArrayList<Producto> productos_totales = new ArrayList<>();
    
    // Traemos todos los productos del archivo binario
    try {    
      productos_totales= productoBinario.leerBinario();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    // Creamoes el modelo de la tabla y agreamos las columnas
    DefaultTableModel modeloTablaProducto = new DefaultTableModel();

    for (String columna : columnas) {
      modeloTablaProducto.addColumn(columna);
    }

    // Llenamos el model de la tabla con los datos en productos_totales ( agragamos filas) 
    for (int i = 0; i < productos_totales.size(); i++) {
      
      Producto producto = productos_totales.get(i);
      
      System.out.print(producto.toString());

      Object[] fila = {
          producto.getId_Producto(), 
          producto.getNombre(), 
          producto.getDetalle(),
          producto.getPrecio(),
          producto.getStock(),
          producto.getFecha_produccion(), 
          producto.getFecha_vencimiento()
      };

      modeloTablaProducto.addRow(fila);
    }
    // Creamos la tabla a la que le pasamos el model
    JTable tabla = new JTable(modeloTablaProducto);

    // Creamos un renderer para centrar el contenido de las celdas
    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    renderer.setHorizontalAlignment(SwingConstants.CENTER);
    tabla.setDefaultRenderer(Object.class, renderer);

    // Agregamos la tabla a un panel para que tenga barras de desplazamiento
    JScrollPane scrollPane = new JScrollPane(tabla);

    // Creamos un panel para agregar la tabla y lo agregamos al frame
    JPanel panel = new JPanel();
    panel.setLayout(new GridBagLayout());
    panel.add(scrollPane);
    add(panel);

    // Configuramos la ventana
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  public static void main(String[] args) {
    new TablaProducto();
  }
}
