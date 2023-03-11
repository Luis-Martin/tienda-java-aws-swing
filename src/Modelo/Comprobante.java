
package Modelo;

import java.io.Serializable;

public class Comprobante implements Serializable {

    public Comprobante() {

    }

    private int id_venta;
    private String tipo_comprobante, fecha_emision;

    public Comprobante(int Id_Venta, String Tipo_comprobante, String Fecha_emision) {
        this.id_venta = Id_Venta;
        this.tipo_comprobante = Tipo_comprobante;
        this.fecha_emision = Fecha_emision;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int Id_Venta) {
        this.id_venta = Id_Venta;
    }

    public String getTipo_comprobante() {
        return tipo_comprobante;
    }

    public void setTipo_comprobante(String Tipo_comprobante) {
        this.tipo_comprobante = Tipo_comprobante;
    }

    public String getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(String Fecha_emision) {
        this.fecha_emision = Fecha_emision;
    }

    public String toString() {
        return "Comprobantes{" + ", id_venta=" + id_venta + ", tipo_comprobante=" + tipo_comprobante
                + ", fecha_emision=" + fecha_emision + '}';
    }

}
