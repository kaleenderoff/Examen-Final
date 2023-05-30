package proyectomundial.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import proyectomundial.model.User;
import proyectomundial.util.BasedeDatos;

public class UsersDAO {

    public UsersDAO() {
        BasedeDatos.conectar();
    }

    public boolean registrarUsuario(User user) {

        String sql = "INSERT INTO s_rojas9.users (username, password) values("
                + "'" + user.getUsername() + "', "
                + "'" + user.getPassword() + "')";

        //BasedeDatos.conectar();
        boolean registro = BasedeDatos.ejecutarActualizacionSQL(sql);
        //BasedeDatos.desconectar();
        return registro;
    }

    public List<User> getloginUsuario() {
        String sql = "SELECT * from s_rojas9.users;";
        List<User> usuarios = new ArrayList<User>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {

                while (result.next()) {
                    User usuario = new User(result.getString("username"), result.getString("password"));
                    usuarios.add(usuario);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando Sesion");
        }

        return usuarios;
    }
    
    public String[][] getUsuariosMatriz() {
        
        String[][] matrizUsuarios = null;
        List<User> usuarios = getloginUsuario();
        
        
        if(usuarios != null && usuarios.size() > 0) {
            
        
            matrizUsuarios = new String[usuarios.size()][4];

            int x = 0;
            for (User usuario : usuarios) {

                matrizUsuarios[x][0] = usuario.getUsername();
                matrizUsuarios[x][1] = usuario.getPassword();
                x++;
            }
        }
        
        return matrizUsuarios;
    }
}
