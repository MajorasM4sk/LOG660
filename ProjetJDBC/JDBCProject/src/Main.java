import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String argv[]) {
        String url = "jdbc:oracle:thin:EQUIPE112/C66VmkzD@log660ora12c.logti.etsmtl.ca:1521:LOG660";
        try {
            Connection conn = DriverManager.getConnection(url);
            if (conn != null) {
                System.out.println("Connecté");
            }
        } catch (Exception e) {
            System.out.println("Pas connecté");
        }
    }
}
