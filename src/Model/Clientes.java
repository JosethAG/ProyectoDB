package Model;

import java.util.Date;

public class Clientes {
    private int clienteID;
    private String nombre;
    private String apellido;
    private String email;
    private Date fechaNacimiento;
    private int provinciaID;

    public Clientes() {
    }

    public Clientes(int clienteID, String nombre, String apellido, String email, Date fechaNacimiento, int provinciaID) {
        this.clienteID = clienteID;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.provinciaID = provinciaID;
    }

    public int getClienteID() {
        return clienteID;
    }

    public void setClienteID(int clienteID) {
        this.clienteID = clienteID;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getProvinciaID() {
        return provinciaID;
    }

    public void setProvinciaID(int provinciaID) {
        this.provinciaID = provinciaID;
    }

    @Override
    public String toString() {
        return "Clientes{" + "clienteID=" + clienteID + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", fechaNacimiento=" + fechaNacimiento + ", provinciaID=" + provinciaID + '}';
    }
    
}
