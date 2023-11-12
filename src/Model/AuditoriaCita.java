package Model;
import java.util.Date;

public class AuditoriaCita {
    private int auditoriaID;
    private int appoimentoID;
    private String nombre;
    private int descripcion;
    private Date fecha;
    
    public AuditoriaCita(){
        
    }

    public AuditoriaCita(int auditoriaID, int appoimentoID, String nombre, int descripcion, Date fecha) {
        this.auditoriaID = auditoriaID;
        this.appoimentoID = appoimentoID;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }
    
    public int getAuditoriaID() {
        return auditoriaID;
    }

    public void setAuditoriaID(int auditoriaID) {
        this.auditoriaID = auditoriaID;
    }

    public int getAppoimentoID() {
        return appoimentoID;
    }

    public void setAppoimentoID(int appoimentoID) {
        this.appoimentoID = appoimentoID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(int descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
}
