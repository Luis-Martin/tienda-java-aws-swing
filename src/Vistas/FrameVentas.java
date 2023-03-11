package Vistas;

//paquetes de java
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFrame;

//librerias
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

//paquetes propios
import Modelo.*;

class FrameVentas extends JFrame implements ActionListener {

    // contador de validaciones
    boolean[] revisiones = { false, false, false, false };

    // variables para las filas de JTable
    int contadorItems   = 0;

    // variable para label total
    Double totalDouble  = 0.0;

    // variable para modalidad
    String modalidad        = "";

    // variable idproductovalor en formulario
    int idproductovalorFormulario ;
    String documentoClienteValorFormulario;

    //definiendo formato de fecha
    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

    // agregando paneles
    VENTAS_CENTRO centro = new VENTAS_CENTRO();
    VENTAS_SUR sur = new VENTAS_SUR();

    // creando objetos binarios para las fucionalidades en la BD
    ClienteBinario clienteBinario               = new ClienteBinario();
    ProductoBinario productoBinario             = new ProductoBinario();
    VentaBinario ventaBinario                   = new VentaBinario();
    Detalle_Venta_Binario detalleVentaBinario   = new Detalle_Venta_Binario();
    ComprobanteBinario comprobanteBinario       = new ComprobanteBinario();

    // creando array list para las tablas de la BD
    ArrayList<Cliente> clientes_totales         = new ArrayList<>();
    ArrayList<Producto> productos_totales       = new ArrayList<>();
    ArrayList<Venta> ventas_totales             = new ArrayList<>();
    ArrayList<Detalle_Venta> detalles_totales   = new ArrayList<>();
    ArrayList<Comprobante> comprobantes_totales = new ArrayList<>();
    
    // arrays de lectura 
    Object[][] datosClientes;
    Object[][] datosProductos;
    Object[][] datosVentas;
    Object[][] datosDetalleVentas;
    Object[][] datosComprobantes;

    public FrameVentas() {

        // propiedades jframe
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("VENTAS");

        // estableciendo layout
        setLayout(new BorderLayout(0, 10));

        // añadiendo JPnals
        add(centro, BorderLayout.CENTER);
        add(sur, BorderLayout.SOUTH);

        // añadiendo escuchadores de eventos
        centro.formulario.validacion1.addActionListener(this);
        centro.formulario.validacion2.addActionListener(this);
        centro.formulario.agregar.addActionListener(this);
        centro.formulario.tarjeta.addActionListener(this);
        centro.formulario.efectivo.addActionListener(this);
        sur.generarVenta.addActionListener(this);
        sur.cancelarVenta.addActionListener(this);
    }

    public void borrandoDatosFrame() {

        // vaciando JTextfields
        centro.formulario.docClienteValor.setText("");
        centro.formulario.nombreClienteValor.setText("");
        centro.formulario.direccionClienteValor.setText("");
        centro.formulario.telefonoClienteValor.setText("");
        centro.formulario.idProductoValor.setText("");
        centro.formulario.descripcionProductoValor.setText("");
        centro.formulario.nombreProductoValor.setText("");
        centro.formulario.precioUnitarioValor.setText("");
        centro.formulario.cantidadStockValor.setText("");
        centro.formulario.cantidadProductoValor.setText("");
        centro.formulario.docClienteValor.setEditable(true);
        centro.formulario.idProductoValor.setEditable(true);

        // vaciando jlabels
        sur.total.setText("TOTAL : S/.0.00");

        // vaciando JTable -> listado
        for (int i = 0; i < contadorItems; i++) {
            centro.listado.modeloTabla.removeRow(0);
        }

        //reinicializando variables fuera del constructor
        revisiones[0] = false;
        revisiones[1] = false;
        revisiones[2] = false;
        revisiones[3] = false;

        contadorItems = 0;
        totalDouble   = 0.0;

        centro.formulario.tarjeta.setEnabled(true);
        centro.formulario.efectivo.setEnabled(true);

        modalidad     = "";
    }

    public void inicializandoArraylist(){
        try {
            clientes_totales    = clienteBinario.leerBinario();
            productos_totales   = productoBinario.leerBinario();
            ventas_totales      = ventaBinario.leerBinario();
            detalles_totales    = detalleVentaBinario.leerBinario();
            comprobantes_totales= comprobanteBinario.leerBinario();
    
        } catch (ClassNotFoundException ev) {
            System.out.println("Error en llamado de binarios");
        }
    }

    public void redimensionandoArrayTrabajo(){
        datosClientes        = new Object[clientes_totales.size()][5];
        datosProductos       = new Object[productos_totales.size()][7];
        datosVentas          = new Object[ventas_totales.size()][6];
        datosDetalleVentas   = new Object[detalles_totales.size()][3];
        datosComprobantes    = new Object[comprobantes_totales.size()][3];
    }

    public void recorriendoArraylist(){

        for (int i = 0; i < datosClientes.length; i++) {
            Cliente cliente = clientes_totales.get(i);

            datosClientes[i][0] = cliente.getId_Cliente();
            datosClientes[i][1] = cliente.getNombre();
            datosClientes[i][2] = cliente.getDocumento();
            datosClientes[i][3] = cliente.getDireccion();
            datosClientes[i][4] = cliente.getTelefono();

        }

        for (int i = 0; i < datosProductos.length; i++) {
            Producto producto = productos_totales.get(i);

            datosProductos[i][0] = producto.getId_Producto();
            datosProductos[i][1] = producto.getNombre();
            datosProductos[i][2] = producto.getDetalle();
            datosProductos[i][3] = producto.getPrecio();
            datosProductos[i][4] = producto.getStock();
            datosProductos[i][5] = producto.getFecha_produccion();
            datosProductos[i][6] = producto.getFecha_vencimiento();

        }

        for (int i = 0; i < datosVentas.length; i++) {
            Venta venta = ventas_totales.get(i);

            datosVentas[i][0] = venta.getId_Venta();
            datosVentas[i][1] = venta.getId_cliente();
            datosVentas[i][2] = venta.getMonto_con_igv();
            datosVentas[i][3] = venta.getMonto_con_igv();
            datosVentas[i][4] = venta.getFecha_venta();
            datosVentas[i][5] = venta.getModalidad();

        }

        for (int i = 0; i < datosDetalleVentas.length; i++) {
            Detalle_Venta detalle = detalles_totales.get(i);

            datosDetalleVentas[i][0] = detalle.getId_venta();
            datosDetalleVentas[i][1] = detalle.getId_Producto();
            datosDetalleVentas[i][2] = detalle.getCantidad();

        }

        for (int i = 0; i < datosComprobantes.length; i++) {
            Comprobante comprobante = comprobantes_totales.get(i);

            datosComprobantes[i][0] = comprobante.getId_venta();
            datosComprobantes[i][1] = comprobante.getTipo_comprobante();
            datosComprobantes[i][2] = comprobante.getFecha_emision();

        }

    }

    public void actionPerformed(ActionEvent e) {

        /*
          TRABAJO DE BASE DE DATOS
        */

            //traremos los valores de la BD -> arraylist
            inicializandoArraylist();

            //redimenzionamos nuestros array de objetos acorde a la BD - arraylist
            redimensionandoArrayTrabajo();

            //mover los valores de la BD - arraylist -> array de objetos
            recorriendoArraylist();

        /*
          TRABAJO DE EVENTOS
        */

            // consiguiendo objeto que genera el evento
            Object objetoEvento = e.getSource();

            // filtrando el evento
            if (objetoEvento == centro.formulario.validacion1) {

                // recorriendo tabla clientes
                for (Object[] fila : datosClientes) {

                        // coincidencia del valor de la caja de texto con la tabla
                        if (centro.formulario.docClienteValor.getText().equals((String)fila[2])) {

                            // agregando texto a las cajas de texto
                            centro.formulario.nombreClienteValor.setText((String) fila[1]);
                            centro.formulario.direccionClienteValor.setText((String) fila[3]);
                            centro.formulario.telefonoClienteValor.setText((String) fila[4]);

                            revisiones[0] = true;
                            documentoClienteValorFormulario = centro.formulario.docClienteValor.getText();
                            centro.formulario.docClienteValor.setEditable(false);
                            break;

                        }

                    }

            } else if (objetoEvento == centro.formulario.validacion2) {

                // recorriendo la tabla productos
                for (Object[] fila : datosProductos) {

                        // coincidencia del valor de la caja de texto con la tabla
                        if (centro.formulario.idProductoValor.getText().equals(String.valueOf(fila[0]))){

                            // agregando texto a las cajas de texto
                            centro.formulario.nombreProductoValor.setText((String) fila[1]);
                            centro.formulario.descripcionProductoValor.setText((String) fila[2]);
                            centro.formulario.precioUnitarioValor.setText(String.valueOf(fila[3]));
                            centro.formulario.cantidadStockValor.setText(String.valueOf(fila[4]));

                            idproductovalorFormulario = Integer.valueOf(centro.formulario.idProductoValor.getText());
                            centro.formulario.idProductoValor.setEditable(false);

                            revisiones[1] = true;
                            break;

                        }

                }

            } else if (objetoEvento == centro.formulario.agregar) {

                int stockReal;
                int stockDeseado;

                try {

                    stockReal       = Integer.valueOf(centro.formulario.cantidadStockValor.getText());
                    stockDeseado    = Integer.valueOf(centro.formulario.cantidadProductoValor.getText());

                    // validaciones de stock
                    if (stockReal >= stockDeseado && stockReal != 0) {

                            // revisamos si fue o no agregado
                            if (contadorItems == 0) {

                                /*
                                AGREGAMOS PRODUCTO EN JTABLE 
                                */

                                // numero de item en la lista
                                contadorItems++;

                                // creando array provisional para la fila del JTable
                                Object[] filaJtable = new Object[10];

                                // insertando información del producto (capturado) en array provisional
                                filaJtable[0]       = contadorItems;
                                for (Object[] fila : datosProductos) {
                                        if (idproductovalorFormulario  == (int)fila[0]) {
                                                filaJtable[1] = fila[0];
                                                filaJtable[2] = fila[1];
                                                filaJtable[3] = fila[2];
                                                filaJtable[4] = fila[3];
                                                filaJtable[5] = fila[4];
                                                filaJtable[6] = fila[5];
                                                filaJtable[7] = fila[6];
                                                break;
                                        }
                                }
                                filaJtable[8]       = stockDeseado;
                                Double precioFila   = (Double) filaJtable[4] * (int) filaJtable[8];
                                filaJtable[9]       = Math.round(precioFila*100)/100d;

                                // insertando array provsional en registros de JTable
                                centro.listado.modeloTabla.addRow(filaJtable);

                                // redondeo de valores
                                totalDouble         =  precioFila + totalDouble;
                                sur.total.setText("TOTAL: S/. " + String.valueOf(totalDouble));  

                            } else {
                                for (int i = 0; i < contadorItems; i++) {
                                    if (idproductovalorFormulario == (int) centro.listado.modeloTabla.getValueAt(i, 1)) {
                                        System.out.println("El producto ya fue agregado");
                                        break;
                                    } else {
                                        if(i == contadorItems-1){

                                            /*
                                            AGREGAMOS PRODUCTO EN JTABLE 
                                            */

                                            // numero de item en la lista
                                            contadorItems++;
                
                                            // creando array provisional para la fila del JTable
                                            Object[] filaJtable = new Object[10];
            
                                            // insertando información del producto (capturado) en array provisional
                                            filaJtable[0]       = contadorItems;
                                            for (Object[] fila : datosProductos) {
                                                if (idproductovalorFormulario  == (int)fila[0]) {
                                                    filaJtable[1] = fila[0];
                                                    filaJtable[2] = fila[1];
                                                    filaJtable[3] = fila[2];
                                                    filaJtable[4] = fila[3];
                                                    filaJtable[5] = fila[4];
                                                    filaJtable[6] = fila[5];
                                                    filaJtable[7] = fila[6];
                                                    break;
                                                }
                                            }
                                            filaJtable[8]       = stockDeseado;
                                            Double precioFila   = ((Double) filaJtable[4] * (Integer) filaJtable[8]);
                                            filaJtable[9]       = Math.round(precioFila * 100) / 100d;
            
                                            // insertando array provsional en registros de JTable
                                            centro.listado.modeloTabla.addRow(filaJtable);
            
                                            // redondeo de valores
                                            totalDouble         = Math.round(precioFila * 100) / 100d + totalDouble;
                                            sur.total.setText("TOTAL: S/. " + String.valueOf(totalDouble));
            
                                        }
                                    }
                                }
                            }

                            //siguiente validacion
                            revisiones[1] = false;
                            revisiones[2] = true;

                    }else{
                        System.out.println("Stock no valido");
                    }

                } catch (Exception ev) {

                    System.out.println("No se puede agregar el producto");

                } finally {

                    // borramos campos de texto
                    centro.formulario.idProductoValor.setText("");
                    centro.formulario.nombreProductoValor.setText("");
                    centro.formulario.descripcionProductoValor.setText("");
                    centro.formulario.precioUnitarioValor.setText("");
                    centro.formulario.cantidadStockValor.setText("");
                    centro.formulario.cantidadProductoValor.setText("");

                    // habilitar el registro de un nuevo producto
                    centro.formulario.idProductoValor.setEditable(true);

                }


            } else if (objetoEvento == centro.formulario.tarjeta){

                //colores de referencia
                centro.formulario.tarjeta.setEnabled(false);
                centro.formulario.efectivo.setEnabled(true);

                //estableciendo modo de pago
                modalidad = "Tarjeta";
                revisiones[3] = true;
                    
            } else if (objetoEvento == centro.formulario.efectivo){

                //colores de referencia
                centro.formulario.efectivo.setEnabled(false);
                centro.formulario.tarjeta.setEnabled(true);

                //estableciendo modo de pago
                modalidad = "Efectivo";
                revisiones[3] = true;

            } else if (objetoEvento == sur.generarVenta) {

                if (revisiones[0]== true && revisiones[2] == true && revisiones[3] == true) {

                    /* 
                    REGISTRO NUEVA VENTA
                    */

                        //creando objeto venta
                        var ultimaVenta = new Venta();
                        
                        //estableciendo atributos objeto venta
                        int idVentaInt;
                        if (datosVentas.length == 0) {
                            idVentaInt = 1;
                        } else {
                            idVentaInt = (int) datosVentas[datosVentas.length - 1][0] + 1;
                        }
                        ultimaVenta.setId_Venta(idVentaInt);

                        int idCliente;
                        if (datosClientes.length == 0) {
                            idCliente = 1;
                        } else {                     
                            for (Object[] fila : datosClientes) {
                                if(documentoClienteValorFormulario.equals((String) fila[2])){
                                    idCliente = (int) fila[0];
                                    ultimaVenta.setId_cliente(idCliente);
                                    break;
                                }
                            }
                        }

                        ultimaVenta.setMonto_sin_igv(totalDouble - (Math.round((totalDouble * 0.18)*100)/100));
                        ultimaVenta.setMonto_con_igv(totalDouble);
                        ultimaVenta.setFecha_venta(formatoFecha.format(new Date()));
                        ultimaVenta.setModalidad(modalidad);

                        //ingresando en base de datos
                        ventaBinario.agregarVenta(ultimaVenta);
                    
                    /* 
                    REGISTRO NUEVO DETALLE
                    */  

                        //creando objeto detalle
                        Detalle_Venta ultimoDetalle = new Detalle_Venta();

                        //insertando nuevo registro(s) en tabla detalle
                        for (int i = 0; i < contadorItems; i++) {

                            //estableciendo atributos objeto detalle
                            ultimoDetalle.setId_venta(idVentaInt);
                            ultimoDetalle.setId_Producto((int)(centro.listado.tablaProductos.getValueAt(i, 1)));
                            ultimoDetalle.setCantidad((int)(centro.listado.tablaProductos.getValueAt(i,8)));

                            //ingresando en base de datos
                            detalleVentaBinario.agregarDetalle_Venta(ultimoDetalle);

                        }

                    /* 
                    REGISTRO NUEVO COMPROBANTES
                    */  

                        //creando objeto comprobante
                        Comprobante ultiComprobante = new Comprobante();

                        //estableciendo atributos objeto comprobante
                        ultiComprobante.setId_venta(idVentaInt);
                        ultiComprobante.setTipo_comprobante((centro.formulario.docClienteValor.getText().length() == 8) ? "BOLETA" : "FACTURA");
                        ultiComprobante.setFecha_emision(formatoFecha.format(new Date()));

                        //ingresando en base de datos
                        comprobanteBinario.agregarComprobante(ultiComprobante);

                    /* 
                    ACTUALIZACIÓN DE PRODUCTOS (STOCK)
                    */  

                        // actualizando según numero de items
                        for (int i = 0; i < contadorItems; i++) {

                            //creando objeto producto
                            Producto actualizacion = new Producto();

                            //estableciendo atributos objeto producto
                            actualizacion.setId_Producto((int)centro.listado.tablaProductos.getValueAt(i,1));
                            actualizacion.setNombre((String)centro.listado.tablaProductos.getValueAt(i,2));
                            actualizacion.setDetalle((String)centro.listado.tablaProductos.getValueAt(i,3));
                            actualizacion.setPrecio((Double)centro.listado.tablaProductos.getValueAt(i,4));
                            actualizacion.setStock(((int)(centro.listado.tablaProductos.getValueAt(i,5))) - ((int)(centro.listado.tablaProductos.getValueAt(i,8))));
                            actualizacion.setFecha_produccion((String)centro.listado.tablaProductos.getValueAt(i,6));
                            actualizacion.setFecha_vencimiento((String)centro.listado.tablaProductos.getValueAt(i,7));

                            try {
                                productoBinario.editarProducto(actualizacion);
                            } catch (ClassNotFoundException e1) {
                                System.out.print("No se generó la actualización");
                            }

                        }   

                    /* 
                    GENERANDO COMPROBANTE
                    */ 
                        Document documento = new Document();
                        try {

                            // creando documento
                            String ruta = "./src/comprobante.pdf";
                            PdfWriter.getInstance(documento, new FileOutputStream(ruta));
                            documento.open();


                            // creando encabezado
                            Paragraph titulo            = new Paragraph("VENTAS DE ABARROTES, 'UNFV'",FontFactory.getFont("Arial", 16, BaseColor.BLACK));
                            titulo.setAlignment(Element.ALIGN_CENTER);
                            Paragraph direccion         = new Paragraph("Av. Los quechuas " + formatoFecha.format(new Date()) + " - Lima",FontFactory.getFont("Arial", 12, BaseColor.BLACK));
                            direccion.setAlignment(Element.ALIGN_CENTER);
                            Paragraph rucEmpresa        = new Paragraph("RUC: 45817241015",FontFactory.getFont("Arial", 12, BaseColor.BLACK));
                            rucEmpresa.setAlignment(Element.ALIGN_CENTER);
                            String tipoDocumento        = (centro.formulario.docClienteValor.getText().length() == 8) ? "BOLETA DE VENTA ": "FACTURA DE VENTA";
                            Paragraph tipoComprobante   = new Paragraph(tipoDocumento,FontFactory.getFont("Arial", 12, BaseColor.BLACK));
                            tipoComprobante.setAlignment(Element.ALIGN_CENTER);
                            documento.add(titulo);
                            documento.add(direccion);
                            documento.add(rucEmpresa);
                            documento.add(tipoComprobante);
                            documento.add(new Paragraph(" "));


                            // creando datos de usuario
                            Paragraph documentoLabel    =  new Paragraph("Documento cliente    : " + centro.formulario.docClienteValor.getText(),FontFactory.getFont("Arial", 12, BaseColor.BLACK));
                            documentoLabel.setAlignment(Element.ALIGN_LEFT);
                            Paragraph nombreLabel       =  new Paragraph("Nombre cliente         : " + centro.formulario.nombreClienteValor.getText(),FontFactory.getFont("Arial", 12, BaseColor.BLACK));
                            nombreLabel.setAlignment(Element.ALIGN_LEFT);
                            Paragraph direccionLabel    =  new Paragraph("Dirección Cliente      : " + centro.formulario.direccionClienteValor.getText(),FontFactory.getFont("Arial", 12, BaseColor.BLACK));
                            direccionLabel.setAlignment(Element.ALIGN_LEFT);
                            Paragraph telefonoLabel     =  new Paragraph("Telefono Cliente       : " + centro.formulario.telefonoClienteValor.getText(),FontFactory.getFont("Arial", 12, BaseColor.BLACK));
                            telefonoLabel.setAlignment(Element.ALIGN_LEFT);
                            documento.add(documentoLabel);
                            documento.add(nombreLabel);
                            documento.add(direccionLabel);
                            documento.add(telefonoLabel);
                            documento.add(new Paragraph(" "));


                            // creando tabla 2
                            PdfPTable tabla2 = new PdfPTable(6);
                            tabla2.setHorizontalAlignment(Element.ALIGN_LEFT);
                            tabla2.addCell("CODIGO");
                            tabla2.addCell("NOMBRE");
                            tabla2.addCell("DESCRIP.");
                            tabla2.addCell("P. UNIT.");
                            tabla2.addCell("CANTIDAD");
                            tabla2.addCell("TOTAL");
                            for (int i = 0; i < contadorItems; i++) {

                                tabla2.addCell(String.valueOf(centro.listado.tablaProductos.getValueAt(i,1)));
                                tabla2.addCell(String.valueOf(centro.listado.tablaProductos.getValueAt(i,2)));
                                tabla2.addCell(String.valueOf(centro.listado.tablaProductos.getValueAt(i,3)));
                                tabla2.addCell(String.valueOf(centro.listado.tablaProductos.getValueAt(i,4)));
                                tabla2.addCell(String.valueOf(centro.listado.tablaProductos.getValueAt(i,8)));
                                tabla2.addCell(String.valueOf(centro.listado.tablaProductos.getValueAt(i,9)));

                            }
                            documento.add(tabla2);
                            documento.add(new Paragraph(" "));


                            // creando tabla 3
                            PdfPTable tabla3 = new PdfPTable(2);
                            tabla3.setHorizontalAlignment(Element.ALIGN_LEFT);
                            tabla3.addCell("SUB-TOTAL ");
                            tabla3.addCell(String.valueOf((totalDouble - (Math.round((totalDouble * 0.18)*100)/100))));
                            tabla3.addCell("IGV       ");
                            tabla3.addCell(String.valueOf(Math.round((totalDouble * 0.18)*100)/100));
                            tabla3.addCell("MONTO A PAGAR     ");
                            tabla3.addCell((String.valueOf((totalDouble))));
                            documento.add(tabla3);

                            // cerramos documento
                            documento.close();

                        } catch (Exception ev) {
                                System.out.println("Error al crear comprobante");
                        }

                    /* 
                    BORRANDO INFORMACION
                    */ 
                        borrandoDatosFrame();

                }else{
                    System.out.println("No hay validaciones de agregado/pago/cliente");
                }

            } else if (objetoEvento == sur.cancelarVenta) {

                borrandoDatosFrame();
                
            }

    }

}
