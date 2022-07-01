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

public class TestServlet extends HttpServlet {
    // Initialisation du parent
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    // Methode doGet: on utilise l’implementation de doPost
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    // Traitement de la requete:
    // On recoit une chaine de charactere
    // On retourne un page HTML contenant le titre des films de la BD contenant
    // cette chaine dans leur titre.
    public void doPost(HttpServletRequest requete, HttpServletResponse reponse) throws ServletException, IOException {
        // Specifier le type et l’encodage des donnees
        reponse.setContentType("text/html");
        reponse.setCharacterEncoding("utf-8");

        // Creer un PrintWriter pour imprimer la page Web de la reponse
        OutputStreamWriter osw = new OutputStreamWriter(reponse.getOutputStream());
        PrintWriter out = new PrintWriter(osw);

        // Utiliser out.println() pour retourner un résultat

        String email = "";
        String password = "";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            // Recuperer le parametre provenant de la page HTML d’entree
            email = requete.getParameter("email");
            password = requete.getParameter("password");
            if (email != null && password != null) {
                // Ouvrir une connexion
                String url = "jdbc:oracle:thin:EQUIPE112/C66VmkzD@log660ora12c.logti.etsmtl.ca:1521:LOG660";
                conn = DriverManager.getConnection(url);

                // Creer une requete au serveur BD
                ps = conn.prepareStatement("select id_client from t_client where courriel = ? and mot_de_passe = ?");
                ps.setString(1, email);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();
                rs.next();
                if (rs.next())
                    out.println(true);
                else
                    out.println(false);
            }
        } catch (Exception e) {
            // Debug: afficher la trace d’erreur directement dans la page
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            out.println(sw.toString());
        } finally {
            try {
                // Liberer les connections et resources
                out.close();
                ps.close();
                conn.close();
            } catch (Exception lException) {
                lException.printStackTrace();
            }
        }
    }
}
