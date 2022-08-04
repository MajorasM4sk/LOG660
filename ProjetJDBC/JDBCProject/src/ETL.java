import POJOs.*;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ETL {
    public static void main(String argv[]) {
        String url = "jdbc:oracle:thin:EQUIPE112/C66VmkzD@log660ora12c.logti.etsmtl.ca:1521:LOG660";
        /*
        select date_pret, t_client.nom, adresse, ville, province, codepostal, date_naissance, titre, annee, film.code_film from location_film, t_client, copie_film, film
            where location_film.id_client = t_client.id_client
            and location_film.no_copie_film = copie_film.no_copie_film
            and copie_film.code_film = film.code_film;
        select nom from genre_film, genre where code_film = 317219 and genre_film.no_genre = genre.no_genre;
        select min(date_pret) from location_film where id_client = 2128598;
        * */
        try {
            Connection connection = DriverManager.getConnection(url);
            if (connection != null) {
                System.out.println("Connexion à la BD réussie !");
                connection.setAutoCommit(false);
                String query = "select * from GENRE";
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    System.out.println(rs.getInt(1));
                    System.out.println(rs.getString(2));
                }
                connection.commit();
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
