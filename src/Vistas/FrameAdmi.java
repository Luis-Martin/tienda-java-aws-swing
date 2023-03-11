package Vistas;

import java.awt.*;
import javax.swing.*;

public class FrameAdmi extends JFrame {

  public FrameAdmi(String nombre, String apellido, String dni) {

    // Colocamos un título al JFrame
    super("COMPRA Y VENTA DE ABARROTES - NOMBRE DE TIENDA");

    // Configuramos el JFrame del cliente para que se establezca en pantalla
    // completa
    // y para que no se pueda redimenzionar
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setExtendedState(Frame.MAXIMIZED_BOTH);
    setResizable(false);

    // Instanciamos el panel del cliente y lo agregamos al JFrame
    add(new PanelAdmi(nombre, apellido, dni));
    // add(new PanelAdmi());

  }

}
