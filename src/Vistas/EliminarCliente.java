package Vistas;
import Modelo.Cliente;
import Modelo.ClienteBinario;

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

public class EliminarCliente extends JFrame {

  private JTextPane idCliente;
  private JTextPane nombreEditado;
  private JTextPane documentoEditado;
  private JTextPane telefonoEditado;
  private JTextPane direccionEditado;

  public EliminarCliente() {

    setTitle("Eliminar Cliente");

    // OBTENEMOS LOS DATOS
    
    // Instancio la clase que maneja el archivo binario de productos
    ClienteBinario productoBinario = new ClienteBinario();
    ArrayList<Cliente> clientes_totales = new ArrayList<>();
    
    // Traemos todos los productos del archivo binario
    try {    
      clientes_totales= productoBinario.leerBinario();
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
    panel.add(new JLabel("Busque el Cliente que desea eliminar"), c);
    
    // SEPARADOR
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 1; // fila 1
    panel.add(new JLabel("-------------------------------------------------------------------------------------------"), c);

    // JCOMBOBOX
    JPanel buscarProducto = new JPanel();
    String[] optiones = new String[clientes_totales.size()];
    
    for (int i = 0; i < clientes_totales.size(); i++) {
      optiones[i] = clientes_totales.get(i).getNombre();
    }
    
    JComboBox<String> comboBox = new JComboBox<>(optiones);
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
    panel.add(new JLabel("-------------------------------------------------------------------------------------------"), c);
    
    
    // Seleccionar el id del producto a agregar

    // ArrayList de los ids
    ArrayList<Integer> ids = new ArrayList<>();

    // Llenamos el arraylist con todos los ids
    for (int i = 0; i < clientes_totales.size(); i++) {
      ids.add(clientes_totales.get(i).getId_Cliente());
    }

    // Hallamos el id máximo
    int id_max = 0;
    for (int i = 0; i < ids.size(); i++) {
        
      int id = ids.get(i);
      
      if (id > id_max) {
        id_max = id;
      }
    }
    
    // El id nuevo será 1 superior al máximo
    int nueov_id = id_max + 1;
    
    // DATOS A EDITAR

    // ----ID
    JPanel datosClienteID = new JPanel();
    idCliente = new JTextPane();
    idCliente.setText(String.valueOf(nueov_id));
    idCliente.setEditable(false);
    JLabel labelId = new JLabel("ID : ");
    idCliente.setPreferredSize(new Dimension(200, 20));
    labelId.setPreferredSize(new Dimension(100, 20));
    datosClienteID.add(labelId);
    datosClienteID.add(idCliente);

    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 4; // fila 1
    panel.add(datosClienteID, c);

    // ----NOMBRE
    JPanel datosClienteNombre = new JPanel();
    nombreEditado = new JTextPane();
    JLabel labelNombre = new JLabel("Nombre : ");
    nombreEditado.setPreferredSize(new Dimension(200, 20));
    labelNombre.setPreferredSize(new Dimension(100, 20));
    datosClienteNombre.add(labelNombre);
    datosClienteNombre.add(nombreEditado);
    
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 5; // fila 1
    panel.add(datosClienteNombre, c);

    // ----DOCUMENTO
    JPanel datosClienteDocumento = new JPanel();
    documentoEditado = new JTextPane();
    JLabel labelDescipcion= new JLabel("Documento : ");
    documentoEditado.setPreferredSize(new Dimension(200, 20));
    labelDescipcion.setPreferredSize(new Dimension(100, 20));
    datosClienteDocumento.add(labelDescipcion);
    datosClienteDocumento.add(documentoEditado);
    
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 6; // fila 1
    panel.add(datosClienteDocumento, c);

    //  ----DIRECCIÓN
    JPanel datosClienteDireccion = new JPanel();
    direccionEditado = new JTextPane();
    JLabel labelStock = new JLabel("Dirección : ");
    direccionEditado.setPreferredSize(new Dimension(200, 20));
    labelStock.setPreferredSize(new Dimension(100, 20));
    datosClienteDireccion.add(labelStock);
    datosClienteDireccion.add(direccionEditado);
    
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 7; // fila 1
    panel.add(datosClienteDireccion, c);

    // ----TELÉFONO
    JPanel datosClienteTelefono = new JPanel();
    telefonoEditado = new JTextPane();
    JLabel labelPrecio = new JLabel("Teléfono : ");
    telefonoEditado.setPreferredSize(new Dimension(200, 20));
    labelPrecio.setPreferredSize(new Dimension(100, 20));
    datosClienteTelefono.add(labelPrecio);
    datosClienteTelefono.add(telefonoEditado);
    
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 8; // fila 1
    panel.add(datosClienteTelefono, c);


    // Divisiom
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 11; // fila 1
    panel.add(new JLabel("-------------------------------------------------------------------------------------------"), c);
    
    
    JButton agregar = new JButton("Eliminar");
    
    // MANEJAMOS EL EVENTO DEL JCOMBOBOX
    agregar.addActionListener(new EliminarDatos());

    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 12; // fila 1
    panel.add(agregar, c);

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

      ClienteBinario clienteBinario = new ClienteBinario();
      
      try {

        Cliente cliente = clienteBinario.obtenerCliente(nombreSeleccionado);
        
        String idString = String.valueOf(cliente.getId_Cliente());

        idCliente.setText(idString);
        nombreEditado.setText(cliente.getNombre());
        documentoEditado.setText(cliente.getDocumento());
        telefonoEditado.setText(cliente.getTelefono());
        direccionEditado.setText(cliente.getDireccion());

      } catch (ClassNotFoundException error) {
        error.printStackTrace();
      }

    }

  }

  class EliminarDatos implements ActionListener {
    
    public void actionPerformed(ActionEvent e) {

      ClienteBinario clienteBinario = new ClienteBinario();

      int id = Integer.parseInt(idCliente.getText());

      
      try {
        clienteBinario.eliminarCliente(id);
      } catch (ClassNotFoundException e1) {
        e1.printStackTrace();
      }

      dispose();
    }

  }

}
