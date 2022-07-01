import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.*;
import oracle.jdbc.pool.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestTestServlet {

    public static void main(String argv[]) {
        String email = "";
        String password = "";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            // Recuperer le parametre provenant de la page HTML d’entree
            email = "BeverlyHMyer80@gmail.com";
            password = "MeiK8aex";
            if (email != null && password != null) {
                // Ouvrir une connexion
                String url = "jdbc:oracle:thin:EQUIPE112/C66VmkzD@log660ora12c.logti.etsmtl.ca:1521:LOG660";
                conn = DriverManager.getConnection(url);

                // Creer une requete au serveur BD
                ps = conn.prepareStatement("select id_client from t_client where courriel = ? and mot_de_passe = ?");
                ps.setString(1, email);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) System.out.println(true);
                else System.out.println(false);
            }
        }
        catch (Exception e) {
            // Debug: afficher la trace d’erreur directement dans la page
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            System.out.println(sw.toString());
        }
        finally {
            try {
                // Liberer les connections et resources
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            }
            catch (Exception lException) {
                lException.printStackTrace();
            }
        }
    }
}
