package Modelo;

import java.io.Serializable;

public class Producto implements Serializable {

  int id_Producto, stock;
  double precio;
  private String nombre, detalle, fecha_produccion, fecha_vencimiento;

  public Producto() {
  }

  public Producto(int Id_Producto, String Nombre, String Detalle, double Precio, int Stock, String Fecha_produccion,
      String Fecha_vencimiento) {
    this.id_Producto = Id_Producto;
    this.nombre = Nombre;
    this.detalle = Detalle;
    this.precio = Precio;
    this.stock = Stock;
    this.fecha_produccion = Fecha_produccion;
    this.fecha_vencimiento = Fecha_vencimiento;
  }

  public int getId_Producto() {
    return id_Producto;
  }

  public void setId_Producto(int Id_Producto) {
    this.id_Producto = Id_Producto;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String Nombre) {
    this.nombre = Nombre;
  }

  public String getDetalle() {
    return detalle;
  }

  public void setDetalle(String Detalle) {
    this.detalle = Detalle;
  }

  public double getPrecio() {
    return precio;
  }

  public void setPrecio(double Precio) {
    this.precio = Precio;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int Stock) {
    this.stock = Stock;
  }

  public String getFecha_produccion() {
    return fecha_produccion;
  }

  public void setFecha_produccion(String Fecha_produccion) {
    this.fecha_produccion = Fecha_produccion;
  }

  public String getFecha_vencimiento() {
    return fecha_vencimiento;
  }

  public void setFecha_vencimiento(String Fecha_vencimiento) {
    this.fecha_vencimiento = Fecha_vencimiento;
  }

  public String toString() {
    return "Productos{" + "Id_Producto=" + id_Producto + ", Nombre=" + nombre + ", Detalle=" + detalle
        + ", Precio=" + precio + ", Stock=" + stock + ", fecha_produccion=" + fecha_produccion
        + ", fecha_vencimiento=" + fecha_vencimiento + '}';
  }

}
