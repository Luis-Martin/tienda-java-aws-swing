
package Vistas;

import javax.swing.*;

import Modelo.Trabajador;
import Modelo.TrabajadorBinario;

import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;

public class FrameLogin extends JFrame implements ActionListener {

    private JTextField textfield1;
    private JPasswordField passwordField;
    private JLabel label1, label2;
    private JButton boton1;

    public FrameLogin() {

        setTitle("Login");

        JPanel panel = new JPanel();// creacion de ventana
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c;

        label1 = new JLabel("Ingrese su Usuario");
        label1.setFont(new Font("Andale Mono", 1, 12));
        c = new GridBagConstraints();
        c.gridx = 0; // columna 0
        c.gridy = 0; // fila 1
        panel.add(label1, c);

        textfield1 = new JTextField();
        textfield1.setPreferredSize(new Dimension(200, 20));
        textfield1.setFont(new Font("Andale Mono", 1, 14));
        c = new GridBagConstraints();
        c.gridx = 0; // columna 0
        c.gridy = 1; // fila 1
        panel.add(textfield1, c);

        JTextArea espacioLibre1 = new JTextArea("");
        espacioLibre1.setEditable(false);
        c = new GridBagConstraints();
        c.gridx = 0; // columna 0
        c.gridy = 2; // fila 1
        panel.add(espacioLibre1, c);

        label2 = new JLabel("Ingrese su Contraseña");
        label2.setFont(new Font("Andale Mono", 1, 12));
        c = new GridBagConstraints();
        c.gridx = 0; // columna 0
        c.gridy = 3; // fila 1
        panel.add(label2, c);

        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 20));
        passwordField.setFont(new Font("Andale Mono", 1, 14));
        c = new GridBagConstraints();
        c.gridx = 0; // columna 0
        c.gridy = 4; // fila 1
        panel.add(passwordField, c);

        JTextArea espacioLibre2 = new JTextArea("");
        espacioLibre2.setEditable(false);
        c = new GridBagConstraints();
        c.gridx = 0; // columna 0
        c.gridy = 5; // fila 1
        panel.add(espacioLibre2, c);

        boton1 = new JButton("Ingresar");
        boton1.setFont(new Font("Andale Mono", 1, 14));
        boton1.addActionListener(this);
        c = new GridBagConstraints();
        c.gridx = 0; // columna 0
        c.gridy = 6; // fila 1
        panel.add(boton1, c);

        add(panel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == boton1) {

            String usuario = textfield1.getText();
            char[] contraseniaChar = passwordField.getPassword();
            String contrasenia = new String(contraseniaChar);

            System.out.println("Usuario: " + usuario);
            System.out.println("Contraseña: " + contrasenia);

            Boolean loginValido = false;

            TrabajadorBinario trabajadorBinario = new TrabajadorBinario();

            ArrayList<Trabajador> trabajadoresTotales;
            Trabajador trabajadorLogeado = new Trabajador();

            try {

                trabajadoresTotales = trabajadorBinario.leerBinario();

                for (Trabajador trabajador : trabajadoresTotales) {
                    if (usuario.equals(trabajador.getNombre()) && contrasenia.equals(trabajador.getContrasenia())) {
                        trabajadorLogeado.setNombre(trabajador.getNombre());
                        trabajadorLogeado.setApellido(trabajador.getApellido());
                        trabajadorLogeado.setDni(trabajador.getDni());
                        loginValido = true;
                    }
                }

            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }


            if (loginValido) {

                dispose();
                FrameAdmi frameAdmi = new FrameAdmi(trabajadorLogeado.getNombre(), trabajadorLogeado.getApellido(), trabajadorLogeado.getDni());
                frameAdmi.setVisible(true);

            } else {

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
                cAlerta.gridy = 1; // fila 1
                JLabel separdor1 = new JLabel(
                        "-------------------------------------------------------------------------------------------");
                separdor1.setForeground(Color.RED);
                panelGeneral.add(separdor1, cAlerta);

                // Nombre no puede ser nulo
                JPanel panelAertaN = new JPanel();
                JLabel textoAlertaN = new JLabel("Usuario o contraseña no válido");
                textoAlertaN.setForeground(Color.RED);
                panelAertaN.add(textoAlertaN);
                cAlerta = new GridBagConstraints();
                cAlerta.gridx = 0; // columna 0
                cAlerta.gridy = 2; // fila 1
                panelGeneral.add(textoAlertaN, cAlerta);

                JPanel emogy = new JPanel();
                JLabel textoEmogy = new JLabel("≡(▔﹏▔)≡");
                textoEmogy.setForeground(Color.RED);
                emogy.add(textoEmogy);
                cAlerta = new GridBagConstraints();
                cAlerta.gridx = 0; // columna 0
                cAlerta.gridy = 3; // fila 1
                panelGeneral.add(textoEmogy, cAlerta);


                // SEPARADOR
                cAlerta = new GridBagConstraints();
                cAlerta.gridx = 0; // columna 0
                cAlerta.gridy = 4; // fila 1
                JLabel separdor2 = new JLabel(
                        "-------------------------------------------------------------------------------------------");
                separdor2.setForeground(Color.RED);
                panelGeneral.add(separdor2, cAlerta);


                // Agragamos el panel y terminamos de configurarlo
                frameAlerta.add(panelGeneral);
                frameAlerta.setVisible(true);
                frameAlerta.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameAlerta.setLocationRelativeTo(null);

            }
        }
    }

    public static void main(String args[]) {

        new FrameLogin();

    }
}
