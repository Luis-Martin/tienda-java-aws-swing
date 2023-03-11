
package Modelo;

import java.io.Serializable;

public class Detalle_Venta implements Serializable {

    private int id_venta, id_producto, cantidad;

    public Detalle_Venta() {}

    public Detalle_Venta(int Id_Venta, int Id_Producto, int Cantidad) {
        this.id_venta = Id_Venta;
        this.id_producto = Id_Producto;
        this.cantidad = Cantidad;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int Id_Venta) {
        this.id_venta = Id_Venta;
    }

    public int getId_Producto() {
        return id_producto;
    }

    public void setId_Producto(int Id_Producto) {
        this.id_producto = Id_Producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.cantidad = Cantidad;
    }

    @Override
    public String toString() {
        return "Detalle_Venta{" + "Id_Venta=" + id_venta + ", Id_Producto=" + id_producto + ", Cantidad=" + cantidad
                + '}';
    }

}
