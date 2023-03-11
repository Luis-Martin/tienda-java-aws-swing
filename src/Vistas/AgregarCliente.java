package Vistas;

import Modelo.Cliente;
import Modelo.ClienteBinario;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.awt.event.*;

public class AgregarCliente extends JFrame {

  private JTextPane idCliente;
  private JTextPane nombreEditado;
  private JTextPane documentoEditado;
  private JTextPane telefonoEditado;
  private JTextPane direccionEditado;
  ArrayList<Cliente> clientes_totales;

  public AgregarCliente() {

    setTitle("Agregar Cliente");

    // OBTENEMOS LOS DATOS

    // Instancio la clase que maneja el archivo binario de productos
    ClienteBinario productoBinario = new ClienteBinario();
    clientes_totales = new ArrayList<>();

    // Traemos todos los productos del archivo binario
    try {
      clientes_totales = productoBinario.leerBinario();
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
    panel.add(new JLabel("Ingrese los datos del Cliente a agregar"), c);

    // SEPARADOR
    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 1; // fila 1
    panel.add(new JLabel("-------------------------------------------------------------------------------------------"),
        c);

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
    JLabel labelDescipcion = new JLabel("Documento : ");
    documentoEditado.setPreferredSize(new Dimension(200, 20));
    labelDescipcion.setPreferredSize(new Dimension(100, 20));
    datosClienteDocumento.add(labelDescipcion);
    datosClienteDocumento.add(documentoEditado);

    c = new GridBagConstraints();
    c.gridx = 0; // columna 0
    c.gridy = 6; // fila 1
    panel.add(datosClienteDocumento, c);

    // ----DIRECCIÓN
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
    panel.add(new JLabel("-------------------------------------------------------------------------------------------"),
        c);

    JButton agregar = new JButton("Agregar");

    // MANEJAMOS EL EVENTO DEL JCOMBOBOX
    agregar.addActionListener(new AgregarDato());

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

  class AgregarDato implements ActionListener {

    public void actionPerformed(ActionEvent e) {

      ClienteBinario clienteBinario = new ClienteBinario();

      int id = Integer.parseInt(idCliente.getText());
      String nombre = nombreEditado.getText();
      String documenteo = documentoEditado.getText();
      String telefono = telefonoEditado.getText();
      String direccion = direccionEditado.getText();

      Cliente productoEditado = new Cliente(id, nombre, documenteo, direccion, telefono);

      // Verificar que es un teléfono
      int num;
      Boolean esTelefono = true;
      try {
        num = Integer.parseInt(telefono);
        if (num < 899999999) {
          esTelefono = false;
        }
      } catch (NumberFormatException err) {
        esTelefono = false;
      }


      // Verificar que el domumento no exista
      Boolean existeDocumento = false;

      for (Cliente cliente : clientes_totales) {
        if (cliente.getDocumento().equals(documenteo)){
          existeDocumento = true;
        }
      }
      //Verificar si es un DNI O RUC
      Boolean esDniOEsRuc = false;
      if(documenteo.length()==8 || documenteo.length()==11 ) esDniOEsRuc= true;
      
      if (nombre.isEmpty() || direccion.isEmpty() || !esDniOEsRuc || !esTelefono || existeDocumento  ) {

        JFrame frameAlerta = new JFrame();
        frameAlerta.setTitle("Alerta de Validación");
        frameAlerta.setSize(new Dimension(400, 180));
        GridBagConstraints cAlerta;
        JPanel panelGeneral = new JPanel();
        panelGeneral.setLayout(new GridBagLayout());

        // Validaciones
        
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
        JLabel separdor = new JLabel(
            "-------------------------------------------------------------------------------------------");
        separdor.setForeground(Color.RED);
        panelGeneral.add(separdor, cAlerta);

        // Nombre no puede ser nulo
        JPanel panelAertaN = new JPanel();
        JLabel textoAlertaN = new JLabel("Nombre y Dirección no tiene que ser nulo");
        textoAlertaN.setForeground(Color.RED);
        panelAertaN.add(textoAlertaN);
        cAlerta = new GridBagConstraints();
        cAlerta.gridx = 0; // columna 0
        cAlerta.gridy = 2; // fila 1
        panelGeneral.add(textoAlertaN, cAlerta);

        // Documento válido
        JPanel panelAertaPS = new JPanel();
        JLabel textoAlertaPS = new JLabel("Documento tiene que ser DNI (8 dígitos) o RUC (11 dígitos)");
        textoAlertaPS.setForeground(Color.RED);
        panelAertaPS.add(textoAlertaPS);
        cAlerta = new GridBagConstraints();
        cAlerta.gridx = 0; // columna 0
        cAlerta.gridy = 3; // fila 1
        panelGeneral.add(textoAlertaPS, cAlerta);

        // Documento válido
        JPanel panelAertaPSD = new JPanel();
        JLabel textoAlertaPSD = new JLabel("Documento tiene que ser nuevo");
        textoAlertaPSD.setForeground(Color.RED);
        panelAertaPSD.add(textoAlertaPSD);
        cAlerta = new GridBagConstraints();
        cAlerta.gridx = 0; // columna 0
        cAlerta.gridy = 4; // fila 1
        panelGeneral.add(textoAlertaPSD, cAlerta);

        // téléfono Váñlido
        JPanel panelAertaF = new JPanel();
        JLabel textoAlertaF = new JLabel("El Teléfono debe tener un correcto formato");
        textoAlertaF.setForeground(Color.RED);
        panelAertaF.add(textoAlertaF);
        cAlerta = new GridBagConstraints();
        cAlerta.gridx = 0; // columna 0
        cAlerta.gridy = 5; // fila 1
        panelGeneral.add(textoAlertaF, cAlerta);

        // Agragamos el panel y terminamos de configurarlo
        frameAlerta.add(panelGeneral);
        frameAlerta.setVisible(true);
        frameAlerta.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameAlerta.setLocationRelativeTo(null);

      } else {

        clienteBinario.agregarCliente(productoEditado);  
        dispose();

      }

    }

  }

}
