package Modelo;

import java.io.Serializable;

public class Trabajador implements Serializable {

    private String nombre, apellido, dni, contrasenia;

    public Trabajador() {}

    public Trabajador(String nombre, String apellido, String dni, String contrasenia) {
      this.nombre = nombre;
      this.apellido = apellido;
      this.dni = dni;
      this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String Contrasenia) {
        this.contrasenia = Contrasenia;
    }

    public String toString() {
        return "Clientes{" + "Nombre=" + nombre + ", Apellido=" + apellido + ", dni=" + dni
                + ", contrasenia=" + contrasenia  + '}';
    }

}
