import java.sql.*;

public class Main {
    public static void main(String argv[]) {
        String url = "jdbc:oracle:thin:EQUIPE112/C66VmkzD@log660ora12c.logti.etsmtl.ca:1521:LOG660";
        try {
            Connection conn = DriverManager.getConnection(url);
            if (conn != null) {
                System.out.println("Connecté");
                String sql = "INSERT INTO client (id_client, courriel, mot_de_passe, telephone, nom, prenom, date_naissance) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = conn.prepareStatement(sql);
                conn.setAutoCommit(false);
                statement.setInt(1, 1);
                statement.setString(2, "test@gmail.com");
                statement.setString(3, "12345");
                statement.setString(4, "4444444419");
                statement.setString(5, "Gertrude");
                statement.setString(6, "Lapoule");
                statement.setString(7, "2021-04-19");
                statement.addBatch();
                statement.setInt(1, 2);
                statement.setString(2, "test2@gmail.com");
                statement.setString(3, "123456");
                statement.setString(4, "4444444418");
                statement.setString(5, "Gertrud");
                statement.setString(6, "Lapoul");
                statement.setString(7, "2021-04-18");
                statement.addBatch();

                int[] count = statement.executeBatch();

                conn.commit();
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
