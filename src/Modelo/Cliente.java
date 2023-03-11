package Modelo;

import java.io.Serializable;

public class Cliente implements Serializable {

    private int Id_Cliente;
    private String nombre, documento, direccion, telefono;

    public Cliente() {}

    public Cliente(int Id_Cliente, String nombre, String documento, String direccion, String telefono) {
        this.Id_Cliente = Id_Cliente;
        this.nombre = nombre;
        this.documento = documento;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public int getId_Cliente() {
        return Id_Cliente;
    }

    public void setId_Cliente(int Id_Cliente) {
        this.Id_Cliente = Id_Cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String toString() {
        return "Clientes{" + "Id_Cliente=" + Id_Cliente + ", nombre=" + nombre + ", documento=" + documento
                + ", direccion=" + direccion + ", telefono=" + telefono + '}';
    }

}
