package Model;

import Controller.UsuariosController;


public class main {
     public static void main(String args[]) {
         
         UsuariosController usuarioCon = new UsuariosController();
         
         for (Object object : usuarioCon.findAllusuarios()) {
            //Usuarios user = (Usuarios) object;
            Usuarios user = (Usuarios) object;
             System.out.println(user.toString());
        }
     }
}
