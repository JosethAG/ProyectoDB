package Model;

import java.util.Date;

public class Citas {
    private int appointmentID;
    private int clienteID;
    private int especialitaID;
    private Date fechaCita;
    private String hora;
    private int sucursalID;
    private String estado;

    public Citas() {
    }
    
    public Citas(int appointmentID, int clienteID, int especialitaID, Date fechaCita, String hora, int sucursalID, String estado) {
        this.appointmentID = appointmentID;
        this.clienteID = clienteID;
        this.especialitaID = especialitaID;
        this.fechaCita = fechaCita;
        this.hora = hora;
        this.sucursalID = sucursalID;
        this.estado = estado;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public int getClienteID() {
        return clienteID;
    }

    public void setClienteID(int clienteID) {
        this.clienteID = clienteID;
    }

    public int getEspecialitaID() {
        return especialitaID;
    }

    public void setEspecialitaID(int especialitaID) {
        this.especialitaID = especialitaID;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getSucursalID() {
        return sucursalID;
    }

    public void setSucursalID(int sucursalID) {
        this.sucursalID = sucursalID;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
