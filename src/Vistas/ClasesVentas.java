package Vistas;

//paquetes de java
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

//creacion de componentes 
class VENTAS_CENTRO extends JPanel {

    // crear paneles
    CENTRO_FROMULARIO formulario = new CENTRO_FROMULARIO();
    CENTRO_LISTA listado = new CENTRO_LISTA();

    public VENTAS_CENTRO() {
        // establecer diseño
        setLayout(new GridLayout(2,1));

        // añadir paneles
        add(formulario);
        add(listado);
    }

}

class CENTRO_FROMULARIO extends JPanel{
    
    // crear labels para mensajes
    JLabel docCliente           = new JLabel("DOC-Cliente         ");
    JLabel nombreCliente        = new JLabel("NOMBRE-Cliente      ");
    JLabel direccionCliente     = new JLabel("DIRECCION-Cliente   ");
    JLabel telefonoCliente      = new JLabel("TELEFONO-Cliente    ");
    JLabel idProducto           = new JLabel("ID-Producto         ");
    JLabel nombreProducto       = new JLabel("NOMBRE-Producto     ");
    JLabel descripcionProducto  = new JLabel("DESCRIPCION-Producto");
    JLabel precioUnitario       = new JLabel("PRECIO-Unitario     ");
    JLabel cantidadStock        = new JLabel("CANTIDAD-Stock      ");
    JLabel cantidadProducto     = new JLabel("CANTIDAD-Producto   ");

    // crear campos para recibir texto
    JTextField docClienteValor          = new JTextField();
    JTextField nombreClienteValor       = new JTextField();
    JTextField direccionClienteValor    = new JTextField();
    JTextField telefonoClienteValor     = new JTextField();
    JTextField idProductoValor          = new JTextField();
    JTextField nombreProductoValor      = new JTextField();
    JTextField descripcionProductoValor = new JTextField();
    JTextField precioUnitarioValor      = new JTextField();
    JTextField cantidadStockValor       = new JTextField();
    JTextField cantidadProductoValor    = new JTextField();

    // creamos imagen icono
    ImageIcon imagen    = new ImageIcon("LUPA.png");

    //creamos botones con imagenes
    JButton validacion1 = new JButton(imagen);
    JButton validacion2 = new JButton(imagen);

    //creamos botones con texto
    JButton agregar     = new JButton("AGREGAR");
    JButton tarjeta     = new JButton("TARJETA");
    JButton efectivo    = new JButton("EFECTIVO");

    //info pantalla
    Toolkit pantalla        = Toolkit.getDefaultToolkit();
    Dimension medidaPantalla= pantalla.getScreenSize();

    //variables de posicionamiento
    int inicioX         = (medidaPantalla.width/2)-280;
    int inicioY         = 35;
    int separacionH     = 5;
    int separacionV     = 25;
    int anchoLabel      = 130;
    int anchoBoton      = 30;
    int alto            = 20;

    public CENTRO_FROMULARIO(){
        //estableciendo diseño
        setLayout(null);
    
        //propiedades de jtfield no editables
        nombreClienteValor.setEnabled(false);
        direccionClienteValor.setEnabled(false);
        telefonoClienteValor.setEnabled(false);
        nombreProductoValor.setEnabled(false);
        descripcionProductoValor.setEnabled(false);
        precioUnitarioValor.setEnabled(false);
        cantidadStockValor.setEnabled(false);

        //redimensionar las imagenes apra daptarse a los botones
        validacion1.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        validacion2.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));

        //cordenadas (x,y) dimensiones (w,h)

            //1ra fila
            docCliente.setBounds(inicioX,inicioY+separacionV,anchoLabel,alto);
            docClienteValor.setBounds(inicioX+anchoLabel+separacionH,inicioY+separacionV,anchoLabel,alto);
            validacion1.setBounds(inicioX+2*anchoLabel+2*separacionH,inicioY+separacionV,anchoBoton,alto);
            nombreCliente.setBounds(inicioX+2*anchoLabel+3*separacionH+anchoBoton,inicioY+separacionV,anchoLabel,alto);
            nombreClienteValor.setBounds(inicioX+3*anchoLabel+4*separacionH+anchoBoton,inicioY+separacionV,anchoLabel,alto);
            add(docCliente);
            add(docClienteValor);
            add(validacion1);
            add(nombreCliente);
            add(nombreClienteValor);

            //2da fila
            direccionCliente.setBounds(inicioX+2*anchoLabel+3*separacionH+anchoBoton,inicioY+2*separacionV,anchoLabel,alto);
            direccionClienteValor.setBounds(inicioX+3*anchoLabel+4*separacionH+anchoBoton,inicioY+2*separacionV,anchoLabel,alto);
            add(direccionCliente);
            add(direccionClienteValor);

            //3ra fila
            telefonoCliente.setBounds(inicioX+2*anchoLabel+3*separacionH+anchoBoton,inicioY+3*separacionV,anchoLabel,alto);
            telefonoClienteValor.setBounds(inicioX+3*anchoLabel+4*separacionH+anchoBoton,inicioY+3*separacionV,anchoLabel,alto);
            add(telefonoCliente);
            add(telefonoClienteValor);  

            //4ta fila
            idProducto.setBounds(inicioX,inicioY+4*separacionV,anchoLabel,alto);
            idProductoValor.setBounds(inicioX+anchoLabel+separacionH,inicioY+4*separacionV,anchoLabel,alto);
            validacion2.setBounds(inicioX+2*anchoLabel+2*separacionH,inicioY+4*separacionV,anchoBoton,alto);
            nombreProducto.setBounds(inicioX+2*anchoLabel+3*separacionH+anchoBoton,inicioY+4*separacionV,anchoLabel,alto);
            nombreProductoValor.setBounds(inicioX+3*anchoLabel+4*separacionH+anchoBoton,inicioY+4*separacionV,anchoLabel,alto);
            add(idProducto);
            add(idProductoValor);
            add(validacion2);
            add(nombreProducto);
            add(nombreProductoValor);

            //5ta fila
            descripcionProducto.setBounds(inicioX+2*anchoLabel+3*separacionH+anchoBoton,inicioY+5*separacionV,anchoLabel,alto);
            descripcionProductoValor.setBounds(inicioX+3*anchoLabel+4*separacionH+anchoBoton,inicioY+5*separacionV,anchoLabel,alto);
            add(descripcionProducto);
            add(descripcionProductoValor);   

            //6ta fila
            precioUnitario.setBounds(inicioX+2*anchoLabel+3*separacionH+anchoBoton,inicioY+6*separacionV,anchoLabel,alto);
            precioUnitarioValor.setBounds(inicioX+3*anchoLabel+4*separacionH+anchoBoton,inicioY+6*separacionV,anchoLabel,alto);
            add(precioUnitario);
            add(precioUnitarioValor);      

            //7ma fila
            cantidadStock.setBounds(inicioX+2*anchoLabel+3*separacionH+anchoBoton,inicioY+7*separacionV,anchoLabel,alto);
            cantidadStockValor.setBounds(inicioX+3*anchoLabel+4*separacionH+anchoBoton,inicioY+7*separacionV,anchoLabel,alto);          
            add(cantidadStock);
            add(cantidadStockValor);

            //8va fila
            cantidadProducto.setBounds(inicioX+2*anchoLabel+3*separacionH+anchoBoton,inicioY+8*separacionV,anchoLabel,alto);
            cantidadProductoValor.setBounds(inicioX+3*anchoLabel+4*separacionH+anchoBoton,inicioY+8*separacionV,anchoLabel,alto);
            add(cantidadProducto);
            add(cantidadProductoValor);

            //9na fila
            agregar.setBounds(inicioX+2*anchoLabel+3*separacionH+anchoBoton,inicioY+9*separacionV,2*anchoLabel+separacionH,alto);
            add(agregar);      

            //9na fila
            tarjeta.setBounds(inicioX+2*anchoLabel+3*separacionH+anchoBoton,inicioY+10*separacionV,anchoLabel,alto);
            efectivo.setBounds(inicioX+3*anchoLabel+4*separacionH+anchoBoton,inicioY+10*separacionV,anchoLabel,alto);
            
            add(tarjeta);
            add(efectivo);
    }

}

class CENTRO_LISTA extends JPanel{

    //dimsensiones de la JTable
    Object [] columnas      = {"N°","ID","NOMBRE","DESCRI.","PRECIO U.","STOCK","FECHA P.","FECHA V.","CANTIDAD","TOTAL"};

    //creando modelo JTable
    DefaultTableModel modeloTabla   = new DefaultTableModel();

    //creando JTable
    JTable tablaProductos = new JTable();
    
    public CENTRO_LISTA() {
        //estableciendo diseño
        setLayout(new FlowLayout(FlowLayout.CENTER,0,20));

        //propiedades del modelo
        for (Object columna : columnas) {
            modeloTabla.addColumn(columna);
        }
        tablaProductos.setModel(modeloTabla);

        //dimensiones tabla
        tablaProductos.setPreferredScrollableViewportSize(new Dimension(600, 220));
        
        //añadiendo tabla y scroll
        add(tablaProductos);
        add(new JScrollPane(tablaProductos));
    }

}

class VENTAS_SUR extends JPanel {

    // creando botones
    JButton generarVenta = new JButton("GENERAR VENTA");
    JButton cancelarVenta = new JButton("CANCELAR VENTA");
    JButton total = new JButton("TOTAL : S/.0.00");

    public VENTAS_SUR() {
        // estableciendo diseño
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // propiedades de buttons
        total.setEnabled(false);

        // añadiendo botones en panel
        add(generarVenta);
        add(cancelarVenta);
        add(total);
    }

}

public class ClasesVentas {

    public static void main(String[] args) {

        FrameVentas frameVentas = new FrameVentas();
        frameVentas.setVisible(true);
    
    }

}