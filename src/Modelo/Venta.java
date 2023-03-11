
package Modelo;

import java.io.Serializable;

public class Venta implements Serializable {
    private int id_venta, id_cliente;
    private double monto_sin_igv, monto_con_igv;
    private String fecha_venta, modalidad;

    public Venta() {

    }

    public Venta(int Id_Venta, int Id_Cliente, double Monto_sin_igv, double Monto_con_igv, String Fecha_venta,
            String Modalidad) {
        this.id_venta = Id_Venta;
        this.id_cliente = Id_Cliente;
        this.monto_con_igv = Monto_con_igv;
        this.monto_sin_igv = Monto_sin_igv;
        this.fecha_venta = Fecha_venta;
        this.modalidad = Modalidad;

    }

    public int getId_Venta() {
        return id_venta;
    }

    public void setId_Venta(int Id_Venta) {
        this.id_venta = Id_Venta;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int Id_Cliente) {
        this.id_cliente = Id_Cliente;
    }

    public double getMonto_con_igv() {
        return monto_con_igv;
    }

    public void setMonto_con_igv(double Monto_con_igv) {
        this.monto_con_igv = Monto_con_igv;
    }

    public double getMonto_sin_igv() {
        return monto_sin_igv;
    }

    public void setMonto_sin_igv(double Monto_sin_igv) {
        this.monto_sin_igv = Monto_sin_igv;
    }

    public String getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(String Fecha_venta) {
        this.fecha_venta = Fecha_venta;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String Modalidad) {
        this.modalidad = Modalidad;
    }

    public String toString() {
        return "Ventas{" + "Id_Venta=" + id_venta + ", id_cliente=" + id_cliente + ", monto_con_igv=" + monto_con_igv
                + ", monto_sin_igv=" + monto_sin_igv + ", fecha_venta=" + fecha_venta + ", modalidad=" + modalidad
                + '}';
    }
}
