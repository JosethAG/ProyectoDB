package Model;

public class Provincias {
    private int ProvinciaID;
    private String NombreUsuario;

    public Provincias(int ProvinciaID, String NombreUsuario) {
        this.ProvinciaID = ProvinciaID;
        this.NombreUsuario = NombreUsuario;
    }

    public int getProvinciaID() {
        return ProvinciaID;
    }

    public void setProvinciaID(int ProvinciaID) {
        this.ProvinciaID = ProvinciaID;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }
         
    
    
}


