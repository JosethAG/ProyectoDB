package Model;


public class Sucursales {
    
    private int SucursalID;
    private String NombreSucursal;
    private int ProvinciaID;

    public Sucursales(int SucursalID, String NombreSucursal, int ProvinciaID) {
        this.SucursalID = SucursalID;
        this.NombreSucursal = NombreSucursal;
        this.ProvinciaID = ProvinciaID;
    }

    public int getSucursalID() {
        return SucursalID;
    }

    public void setSucursalID(int SucursalID) {
        this.SucursalID = SucursalID;
    }

    public String getNombreSucursal() {
        return NombreSucursal;
    }

    public void setNombreSucursal(String NombreSucursal) {
        this.NombreSucursal = NombreSucursal;
    }

    public int getProvinciaID() {
        return ProvinciaID;
    }

    public void setProvinciaID(int ProvinciaID) {
        this.ProvinciaID = ProvinciaID;
    }

    
    
}