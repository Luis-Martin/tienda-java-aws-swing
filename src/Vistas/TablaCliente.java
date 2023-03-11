package Vistas;

import Modelo.Cliente;
import Modelo.ClienteBinario;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.GridBagLayout;
import java.util.ArrayList;;

public class TablaCliente extends JFrame {
  
  public TablaCliente() {

    setTitle("Lista de Clientes");

    // Definimos las columnas de la tabla
    String[] columnas = { "Id", "Nombre", "Documento", "Dirección", "Teléfon" };

    // Obtenemos los datos 
    
    // Instancio la clase que maneja el archivo binario de productos
    ClienteBinario clienteBinario = new ClienteBinario();
    ArrayList<Cliente> clientes_totales = new ArrayList<>();
    
    // Traemos todos los productos del archivo binario
    try {    
      clientes_totales = clienteBinario.leerBinario();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    // Creamoes el modelo de la tabla y agreamos las columnas
    DefaultTableModel modeloTablaCliente = new DefaultTableModel();

    for (String columna : columnas) {
      modeloTablaCliente.addColumn(columna);
    }

    // Llenamos el model de la tabla con los datos en clientes_totales ( agragamos filas ) 
    for (int i = 0; i < clientes_totales.size(); i++) {
      
      Cliente cliente = clientes_totales.get(i);
      
      Object[] fila = {
          cliente.getId_Cliente(), 
          cliente.getNombre(), 
          cliente.getDocumento(),
          cliente.getDireccion(),
          cliente.getTelefono() 
      };

      modeloTablaCliente.addRow(fila);
    }
    // Creamos la tabla a la que le pasamos el model
    JTable tabla = new JTable(modeloTablaCliente);

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

}
