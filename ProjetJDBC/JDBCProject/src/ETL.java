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

                List clients = new ArrayList<ClientEtoile>();
                List films = new ArrayList<FilmEtoile>();
                List locations = new ArrayList<LocationEtoile>();
                List temps = new ArrayList<TempsEtoile>();

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
                    client.age0_4 = age >= 0 && age < 5;
                    client.age5_10 = age >= 5 && age < 10;
                    client.age10_14 = age >= 10 && age < 15;
                    client.age15_20 = age >= 15 && age < 20;
                    client.age20_24 = age >= 20 && age < 25;
                    client.age25_30 = age >= 25 && age < 30;
                    client.age30_34 = age >= 30 && age < 35;
                    client.age35_40 = age >= 35 && age < 40;
                    client.age40_44 = age >= 40 && age < 45;
                    client.age45_50 = age >= 45 && age < 50;
                    client.age50_54 = age >= 50 && age < 55;
                    client.age55_60 = age >= 55 && age < 60;
                    client.age60_64 = age >= 60 && age < 65;
                    client.age65_70 = age >= 65 && age < 70;
                    client.age70_74 = age >= 70 && age < 75;
                    client.age75_80 = age >= 75 && age < 80;
                    client.age80_84 = age >= 80 && age < 85;
                    client.age85_90 = age >= 85 && age < 90;
                    client.age90_94 = age >= 90 && age < 95;
                    client.age95_100 = age >= 95 && age < 100;
                    client.age100_104 = age >= 100 && age < 105;
                    client.age105_110 = age >= 105 && age < 110;
                    client.age110_114 = age >= 110 && age < 115;
                    client.age115_120 = age >= 115 && age < 120;
                    client.age120_124 = age >= 120 && age < 125;
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
                        film.est_usa = true;
                    } else {
                        film.est_usa = false;
                    }
                    String sqlGenre = "select nom from genre_film, genre where code_film = ? and genre_film.no_genre = genre.no_genre";
                    PreparedStatement genreStm = connection.prepareStatement(sqlGenre);
                    genreStm.setInt(1, rs.getInt(9));
                    ResultSet rsGenre = genreStm.executeQuery();
                    List genres = new ArrayList<String>();
                    while (rsGenre.next()) {
                        genres.add(rsGenre.getString(1));
                    }
                    film.est_action = genres.contains("Action");
                    film.est_adventure = genres.contains("Adventure");
                    film.est_comedy = genres.contains("Comedy");
                    film.est_family = genres.contains("Family");
                    film.est_romance = genres.contains("Romance");
                    film.est_drama = genres.contains("Drama");
                    film.est_animation = genres.contains("Animation");
                    film.est_fantasy = genres.contains("Fantasy");
                    film.est_biography = genres.contains("Biography");
                    film.est_thriller = genres.contains("Thriller");
                    film.est_sci_fi = genres.contains("Sci-Fi");
                    film.est_crime = genres.contains("Crime");
                    film.est_sport = genres.contains("Sport");
                    film.est_horror = genres.contains("Horror");
                    film.est_film_noir = genres.contains("Film-Noir");
                    film.est_mystery = genres.contains("Mystery");
                    film.est_western = genres.contains("Western");
                    film.est_war = genres.contains("War");
                    film.est_musical = genres.contains("Musical");
                    film.est_documentary = genres.contains("Documentary");
                    film.est_history = genres.contains("History");
                    film.est_music = genres.contains("Music");
                    films.add(film);

                    i++;
                }
                connection.commit();
                connection.close();
                System.out.println("Finished loading...");
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
