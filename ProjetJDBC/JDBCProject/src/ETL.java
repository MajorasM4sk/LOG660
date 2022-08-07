import POJOs.*;
import POJOsEtoile.ClientEtoile;
import POJOsEtoile.FilmEtoile;
import POJOsEtoile.LocationEtoile;
import POJOsEtoile.TempsEtoile;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import java.util.Calendar;
import java.util.Locale;
import static java.util.Calendar.*;
import java.util.Date;

public class ETL {
    public static void main(String argv[]) {
        String url = "jdbc:oracle:thin:EQUIPE112/C66VmkzD@log660ora12c.logti.etsmtl.ca:1521:LOG660";
        /*
        select nom from genre_film, genre where code_film = 317219 and genre_film.no_genre = genre.no_genre;
        * */
        try {
            Connection connection = DriverManager.getConnection(url);
            if (connection != null) {
                System.out.println("Connexion à la BD réussie !");
                connection.setAutoCommit(false);
                String query = "select date_pret, t_client.nom, ville, province, codepostal, date_naissance, titre, annee, film.code_film, t_client.prenom, t_client.id_client from location_film, t_client, copie_film, film where location_film.id_client = t_client.id_client and location_film.no_copie_film = copie_film.no_copie_film and copie_film.code_film = film.code_film";
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                List<ClientEtoile> clients = new ArrayList();
                List<FilmEtoile> films = new ArrayList();
                List<LocationEtoile> locations = new ArrayList();
                List<TempsEtoile> temps = new ArrayList();

                int i = 1;
                while (rs.next()) {
                    
                    LocationEtoile location = new LocationEtoile();
                    location.id_location = i;
                    location.id_client = i;
                    location.id_film = i;
                    location.id_temps = i;
                    locations.add(location);

                    ClientEtoile client = new ClientEtoile();
                    client.id_client = i;
                    client.nom_client = rs.getString(10) + " " + rs.getString(2);
                    String queryPretMin = "select min(date_pret) from location_film where id_client = ?";
                    PreparedStatement pretMinStm = connection.prepareStatement(queryPretMin);
                    pretMinStm.setInt(1, rs.getInt(11));
                    ResultSet pretMin = pretMinStm.executeQuery();
                    pretMin.next();
                    Date dateMin = pretMin.getDate(1);
                    Calendar calDateMin = getCalendar(dateMin);
                    client.premiere_location_annee = calDateMin.get(YEAR);
                    client.premiere_location_mois = calDateMin.get(MONTH);
                    client.premiere_location_jour = calDateMin.get(DAY_OF_MONTH);
                    Date dateNaissance = rs.getDate(6);
                    int age = getDiffYears(new Date(), dateNaissance);
                    client.age0_4 = (age >= 0 && age < 5) ? "1" : "0";
                    client.age5_9 = (age >= 5 && age < 10) ? "1" : "0";
                    client.age10_14 = (age >= 10 && age < 15) ? "1" : "0";
                    client.age15_19 = (age >= 15 && age < 20) ? "1" : "0";
                    client.age20_24 = (age >= 20 && age < 25) ? "1" : "0";
                    client.age25_29 = (age >= 25 && age < 30) ? "1" : "0";
                    client.age30_34 = (age >= 30 && age < 35) ? "1" : "0";
                    client.age35_39 = (age >= 35 && age < 40) ? "1" : "0";
                    client.age40_44 = (age >= 40 && age < 45) ? "1" : "0";
                    client.age45_49 = (age >= 45 && age < 50) ? "1" : "0";
                    client.age50_54 = (age >= 50 && age < 55) ? "1" : "0";
                    client.age55_59 = (age >= 55 && age < 60) ? "1" : "0";
                    client.age60_64 = (age >= 60 && age < 65) ? "1" : "0";
                    client.age65_69 = (age >= 65 && age < 70) ? "1" : "0";
                    client.age70_74 = (age >= 70 && age < 75) ? "1" : "0";
                    client.age75_79 = (age >= 75 && age < 80) ? "1" : "0";
                    client.age80_84 = (age >= 80 && age < 85) ? "1" : "0";
                    client.age85_89 = (age >= 85 && age < 90) ? "1" : "0";
                    client.age90_94 = (age >= 90 && age < 95) ? "1" : "0";
                    client.age95_99 = (age >= 95 && age < 100) ? "1" : "0";
                    client.age100_104 = (age >= 100 && age < 105) ? "1" : "0";
                    client.age105_109 = (age >= 105 && age < 110) ? "1" : "0";
                    client.age110_114 = (age >= 110 && age < 115) ? "1" : "0";
                    client.age115_119 = (age >= 115 && age < 120) ? "1" : "0";
                    client.age120_124 = (age >= 120 && age < 125) ? "1" : "0";
                    client.code_postal = rs.getString(5);
                    client.ville = rs.getString(3);
                    client.province = rs.getString(4);
                    clients.add(client);

                    TempsEtoile tempsEtoile = new TempsEtoile();
                    Date datePret = rs.getDate(1);
                    tempsEtoile.id_temps = i;
                    Calendar calDatePret = getCalendar(datePret);
                    tempsEtoile.date_location_heure = calDatePret.get(HOUR);
                    tempsEtoile.date_location_jour_semaine = calDatePret.get(DAY_OF_WEEK);
                    tempsEtoile.date_location_mois = calDatePret.get(MONTH);
                    tempsEtoile.annee_location = calDatePret.get(YEAR);
                    temps.add(tempsEtoile);

                    FilmEtoile film = new FilmEtoile();
                    film.id_film = i;
                    film.titre_film = rs.getString(7);
                    film.annee_film = rs.getInt(8);
                    String sqlUsa = "select * from pays_film where code_pays = 1 and code_film = ?";
                    PreparedStatement usaStm = connection.prepareStatement(sqlUsa);
                    usaStm.setInt(1, rs.getInt(9));
                    ResultSet rsUsa = usaStm.executeQuery();
                    if (rsUsa.next()) {
                        film.est_usa = "1";
                    } else {
                        film.est_usa = "0";
                    }
                    String sqlGenre = "select nom from genre_film, genre where code_film = ? and genre_film.no_genre = genre.no_genre";
                    PreparedStatement genreStm = connection.prepareStatement(sqlGenre);
                    genreStm.setInt(1, rs.getInt(9));
                    ResultSet rsGenre = genreStm.executeQuery();
                    List genres = new ArrayList<String>();
                    while (rsGenre.next()) {
                        genres.add(rsGenre.getString(1));
                    }
                    film.est_action = genres.contains("Action") ? "1" : "0";
                    film.est_adventure = genres.contains("Adventure") ? "1" : "0";
                    film.est_comedy = genres.contains("Comedy") ? "1" : "0";
                    film.est_family = genres.contains("Family") ? "1" : "0";
                    film.est_romance = genres.contains("Romance") ? "1" : "0";
                    film.est_drama = genres.contains("Drama") ? "1" : "0";
                    film.est_animation = genres.contains("Animation") ? "1" : "0";
                    film.est_fantasy = genres.contains("Fantasy") ? "1" : "0";
                    film.est_biography = genres.contains("Biography") ? "1" : "0";
                    film.est_thriller = genres.contains("Thriller") ? "1" : "0";
                    film.est_sci_fi = genres.contains("Sci-Fi") ? "1" : "0";
                    film.est_crime = genres.contains("Crime") ? "1" : "0";
                    film.est_sport = genres.contains("Sport") ? "1" : "0";
                    film.est_horror = genres.contains("Horror") ? "1" : "0";
                    film.est_film_noir = genres.contains("Film-Noir") ? "1" : "0";
                    film.est_mystery = genres.contains("Mystery") ? "1" : "0";
                    film.est_western = genres.contains("Western") ? "1" : "0";
                    film.est_war = genres.contains("War") ? "1" : "0";
                    film.est_musical = genres.contains("Musical") ? "1" : "0";
                    film.est_documentary = genres.contains("Documentary") ? "1" : "0";
                    film.est_history = genres.contains("History") ? "1" : "0";
                    film.est_music = genres.contains("Music") ? "1" : "0";
                    films.add(film);
                    i++;
                }
                String sqlf = "INSERT INTO film_etoile (id_film, titre_film, annee_film, est_usa, est_action, est_adventure, est_comedy, est_family, est_romance, est_drama, est_animation, est_fantasy, est_biography, est_thriller, est_sci_fi, est_crime, est_sport, est_horror, est_film_noir, est_mystery, est_western, est_war, est_musical, est_documentary, est_history, est_music) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement statementf = connection.prepareStatement(sqlf);
                films.forEach(f -> {
                    try {
                        statementf.setInt(1, f.id_film);
                        statementf.setString(2, f.titre_film);
                        statementf.setInt(3, f.annee_film);
                        statementf.setString(4, f.est_usa);
                        statementf.setString(5, f.est_action);
                        statementf.setString(6, f.est_adventure);
                        statementf.setString(7, f.est_comedy);
                        statementf.setString(8, f.est_family);
                        statementf.setString(9, f.est_romance);
                        statementf.setString(10, f.est_drama);
                        statementf.setString(11, f.est_animation);
                        statementf.setString(12, f.est_fantasy);
                        statementf.setString(13, f.est_biography);
                        statementf.setString(14, f.est_thriller);
                        statementf.setString(15, f.est_sci_fi);
                        statementf.setString(16, f.est_crime);
                        statementf.setString(17, f.est_sport);
                        statementf.setString(18, f.est_horror);
                        statementf.setString(19, f.est_film_noir);
                        statementf.setString(20, f.est_mystery);
                        statementf.setString(21, f.est_western);
                        statementf.setString(22, f.est_war);
                        statementf.setString(23, f.est_musical);
                        statementf.setString(24, f.est_documentary);
                        statementf.setString(25, f.est_history);
                        statementf.setString(26, f.est_music);
                        statementf.addBatch();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
                statementf.executeBatch();

                String sqlc = "INSERT INTO client_etoile (id_client, nom_client, premiere_location_annee, premiere_location_mois, premiere_location_jour, age0_4, age5_9, age10_14, age15_19, age20_24, age25_29, age30_34, age35_39, age40_44, age45_49, age50_54, age55_59, age60_64, age65_69, age70_74, age75_79, age80_84, age85_89, age90_94, age95_99, age100_104, age105_109, age110_114, age115_119, age120_124, code_postal, ville, province) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement statementc = connection.prepareStatement(sqlc);
                clients.forEach(c -> {
                    try {
                        statementc.setInt(1, c.id_client);
                        statementc.setString(2, c.nom_client);
                        statementc.setInt(3, c.premiere_location_annee);
                        statementc.setInt(4, c.premiere_location_mois);
                        statementc.setInt(5, c.premiere_location_jour);
                        statementc.setString(6, c.age0_4);
                        statementc.setString(7, c.age5_9);
                        statementc.setString(8, c.age10_14);
                        statementc.setString(9, c.age15_19);
                        statementc.setString(10, c.age20_24);
                        statementc.setString(11, c.age25_29);
                        statementc.setString(12, c.age30_34);
                        statementc.setString(13, c.age35_39);
                        statementc.setString(14, c.age40_44);
                        statementc.setString(15, c.age45_49);
                        statementc.setString(16, c.age50_54);
                        statementc.setString(17, c.age55_59);
                        statementc.setString(18, c.age60_64);
                        statementc.setString(19, c.age65_69);
                        statementc.setString(20, c.age70_74);
                        statementc.setString(21, c.age75_79);
                        statementc.setString(22, c.age80_84);
                        statementc.setString(23, c.age85_89);
                        statementc.setString(24, c.age90_94);
                        statementc.setString(25, c.age95_99);
                        statementc.setString(26, c.age100_104);
                        statementc.setString(27, c.age105_109);
                        statementc.setString(28, c.age110_114);
                        statementc.setString(29, c.age115_119);
                        statementc.setString(30, c.age120_124);
                        statementc.setString(31, c.code_postal);
                        statementc.setString(32, c.ville);
                        statementc.setString(33, c.province);
                        statementc.addBatch();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
                statementc.executeBatch();

                String sqlt = "INSERT INTO temps_etoile (id_temps, date_location_heure, date_location_jour_semaine, date_location_mois, annee_location) VALUES (?, ?, ?, ?, ?)";

                PreparedStatement statementt = connection.prepareStatement(sqlt);
                temps.forEach(t -> {
                    try {
                        statementt.setInt(1, t.id_temps);
                        statementt.setInt(2, t.date_location_heure);
                        statementt.setInt(3, t.date_location_jour_semaine);
                        statementt.setInt(4, t.date_location_mois);
                        statementt.setInt(5, t.annee_location);
                        statementt.addBatch();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
                statementt.executeBatch();

                String sqll = "INSERT INTO location_etoile (id_location, id_film, id_temps, id_client) VALUES (?, ?, ?, ?)";
                PreparedStatement statementl = connection.prepareStatement(sqll);
                locations.forEach(l -> {
                    try {
                        statementl.setInt(1, l.id_location);
                        statementl.setInt(2, l.id_film);
                        statementl.setInt(3, l.id_temps);
                        statementl.setInt(4, l.id_client);
                        statementl.addBatch();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
                statementl.executeBatch();

                connection.commit();
                connection.close();
                System.out.println("Terminé");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //https://stackoverflow.com/questions/7906301/how-can-i-find-the-number-of-years-between-two-dates
    public static int getDiffYears(Date first, Date last) {
        Calendar b = getCalendar(first);
        Calendar a = getCalendar(last);
        int diff = b.get(YEAR) - a.get(YEAR);
        if (a.get(MONTH) > b.get(MONTH) ||
                (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
            diff--;
        }
        return diff;
    }

    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }
}
