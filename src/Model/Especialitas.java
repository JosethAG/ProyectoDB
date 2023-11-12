package Model;


public class Especialitas {
    
  private int EspecialistaID;
  private int UserID;
  private String Especialista;

    public Especialitas(int EspecialistaID, int UserID, String Especialista) {
        this.EspecialistaID = EspecialistaID;
        this.UserID = UserID;
        this.Especialista = Especialista;
    }

    public int getEspecialistaID() {
        return EspecialistaID;
    }

    public void setEspecialistaID(int EspecialistaID) {
        this.EspecialistaID = EspecialistaID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getEspecialista() {
        return Especialista;
    }

    public void setEspecialista(String Especialista) {
        this.Especialista = Especialista;
    }
  
  
}
