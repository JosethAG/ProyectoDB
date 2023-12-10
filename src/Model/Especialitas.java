package Model;


public class Especialitas {
    
  private int especialistaID;
  private String nombre;
  private String especialidad;
  private int estado;

    public Especialitas() {
    }

    public Especialitas(int especialistaID, String nombre, String especialidad, int estado) {
        this.especialistaID = especialistaID;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.estado = estado;
    }

    public int getEspecialistaID() {
        return especialistaID;
    }

    public void setEspecialistaID(int especialistaID) {
        this.especialistaID = especialistaID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Especialitas{" + "especialistaID=" + especialistaID + ", nombre=" + nombre + ", especialidad=" + especialidad + ", estado=" + estado + '}';
    }

  
  
}
