package Model;

public class Usuarios {
    
    private int UsuarioID;
    private String NombreUsuario;
    private String Correo;
    private String Contrasenna;

    public Usuarios(int UsuarioID, String NombreUsuario, String Correo, String Contrasenna) {
        this.UsuarioID = UsuarioID;
        this.NombreUsuario = NombreUsuario;
        this.Correo = Correo;
        this.Contrasenna = Contrasenna;
    }

    public int getUsuarioID() {
        return UsuarioID;
    }

    public void setUsuarioID(int UsuarioID) {
        this.UsuarioID = UsuarioID;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getContrasenna() {
        return Contrasenna;
    }

    public void setContrasenna(String Contrasenna) {
        this.Contrasenna = Contrasenna;
    }
    
}
