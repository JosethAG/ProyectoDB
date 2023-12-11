package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;

public class Citas {
    
    private int codigoCita;
    private int clienteId;
    private int especialista;
    private Date fecha;
    private String hora;
    private int sucursal;
    private int tipoCita;
    private String estado;

    public Citas(int codigoCita, int clienteId, int especialista, Date fechaDesde, String hora, int sucursal, int tipoCita, String estado) {
        this.codigoCita = codigoCita;
        this.clienteId = clienteId;
        this.especialista = especialista;
        this.fecha = fecha;
        this.hora = hora;
        this.sucursal = sucursal;
        this.tipoCita = tipoCita;
        this.estado = estado;
    }

    public int getCodigoCita() {
        return codigoCita;
    }

    public void setCodigoCita(int codigoCita) {
        this.codigoCita = codigoCita;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getEspecialista() {
        return especialista;
    }

    public void setEspecialista(int especialista) {
        this.especialista = especialista;
    }

    

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getSucursal() {
        return sucursal;
    }

    public void setSucursal(int sucursal) {
        this.sucursal = sucursal;
    }

    public int getTipoCita() {
        return tipoCita;
    }

    public void setTipoCita(int tipoCita) {
        this.tipoCita = tipoCita;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    

   

   

    

    
    
    
}
