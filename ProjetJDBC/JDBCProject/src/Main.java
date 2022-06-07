import POJOs.*;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String argv[]) {
        String url = "jdbc:oracle:thin:EQUIPE112/C66VmkzD@log660ora12c.logti.etsmtl.ca:1521:LOG660";
        try {
            Connection connection = DriverManager.getConnection(url);
            if (connection != null) {
                System.out.println("Connexion à la BD réussie !");
                connection.setAutoCommit(false);

                Videotheque videotheque = XmlParser.fetchData();

                System.out.println("Insertion des clients...");
                insertClients(connection, videotheque.clients);

                System.out.println("Insertion des cartes de crédit...");
                insertCartesCredit(connection, videotheque.clients);

                System.out.println("Insertion des types de forfait...");
                insertForfaits(connection);

                System.out.println("Insertion des forfaits clients...");
                insertForfaitsClients(connection, videotheque.clients);

                System.out.println("Insertion des personnes...");
                insertPersonnes(connection, videotheque.personnes);

                System.out.println("Insertion des films...");
                insertFilms(connection, videotheque.films);

                System.out.println("Insertion des roles des acteurs...");
                insertRoles(connection, videotheque.films);

                System.out.println("Insertion des réalisateurs...");
                insertRealisateurs(connection, videotheque.films);

                System.out.println("Détection des genres...");
                List<String> genres = listerGenres(videotheque.films);

                System.out.println("Insertion des genres...");
                insertGenres(connection, genres);

                System.out.println("Insertion des liaisons film-genre...");
                insertGenresFilms(connection, videotheque.films);

                System.out.println("Détection des pays...");
                List<String> pays = listerPays(videotheque.films);

                System.out.println("Insertion des pays...");
                insertPays(connection, pays);

                System.out.println("Insertion des liaisons film-pays...");
                insertPaysFilms(connection, videotheque.films);

                System.out.println("Insertion d'employés bidons...");
                insertEmployes(connection);

                System.out.println("Insertion des copies de film aléatoirement pour chaque film (1-100)...");
                insertCopies(connection, videotheque.films);

                // exemple de prepared statement avec batch
                /*
                 * String sql =
                 * "INSERT INTO client (id_client, courriel, mot_de_passe, telephone, nom, prenom, date_naissance) VALUES (?, ?, ?, ?, ?, ?, ?)"
                 * ;
                 * PreparedStatement statement = conn.prepareStatement(sql);
                 * statement.setInt(1, 1);
                 * statement.setString(2, "test@gmail.com");
                 * statement.setString(3, "12345");
                 * statement.setString(4, "4444444419");
                 * statement.setString(5, "Gertrude");
                 * statement.setString(6, "Lapoule");
                 * statement.setString(7, "2021-04-19");
                 * statement.addBatch();
                 * statement.setInt(1, 2);
                 * statement.setString(2, "test2@gmail.com");
                 * statement.setString(3, "123456");
                 * statement.setString(4, "4444444418");
                 * statement.setString(5, "Gertrud");
                 * statement.setString(6, "Lapoul");
                 * statement.setString(7, "2021-04-18");
                 * statement.addBatch();
                 * 
                 * int[] count = statement.executeBatch();
                 */

                connection.commit();
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertClients(Connection connection, List<Client> clients) {
        try {
            String sql = "INSERT INTO t_client (id_client, courriel, mot_de_passe, telephone, nom, prenom, date_naissance, code_forfait) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            clients.forEach(client -> {
                try {
                    statement.setInt(1, client.idClient);
                    statement.setString(2, client.courriel);
                    statement.setString(3, client.motDePasse);
                    statement.setString(4, client.telephone);
                    statement.setString(5, client.nom);
                    statement.setString(6, client.prenom);
                    statement.setString(7, client.dateNaissance);
                    statement.setString(8, client.forfait.codeForfait);
                    statement.addBatch();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            int[] count = statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertCartesCredit(Connection connection, List<Client> clients) {
        try {
            String sql = "INSERT INTO carte_credit (no_carte, type_carte, annee_expiration, mois_expiration, cvv, id_client) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            clients.forEach(client -> {
                try {
                    statement.setString(1, client.carteCredit.noCarte);
                    statement.setString(2, client.carteCredit.typeCarte);
                    statement.setString(3, client.carteCredit.anneeExpiration);
                    statement.setString(4, client.carteCredit.moisExpiration);
                    statement.setInt(5, 000);
                    statement.setString(6, client.prenom + ' ' + client.nom);
                    statement.setInt(7, client.idClient);
                    statement.addBatch();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            int[] count = statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertForfaits(Connection connection) {
        // voir le pdf d'énoncé du tp pour les types de forfaits (le tableau en haut du
        // document)
    }

    private static void insertForfaitsClients(Connection connection, List<Client> clients) {

    }

    private static void insertPersonnes(Connection connection, List<Personne> personnes) {
        try {
            String sql = "INSERT INTO personne (id_personne, nom, date_naissance, lieu_naissance, photo, biographie) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            personnes.forEach(personne -> {
                try {
                    statement.setInt(1, personne.idPersonne);
                    statement.setString(2, personne.nom);
                    statement.setString(3, personne.dateNaissance);
                    statement.setString(4, personne.lieuNaissance);
                    statement.setString(5, personne.photo);
                    statement.setString(6, personne.biographie);
                    statement.addBatch();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            int[] count = statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertFilms(Connection connection, List<Film> films) {
        try {
            String sql = "INSERT INTO films (code_film, titre, annee, langue, resume_film, affiche, realisateur) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            films.forEach(film -> {
                try {
                    statement.setInt(1, film.codeFilm);
                    statement.setString(2, film.titre);
                    statement.setInt(3, film.annee);
                    statement.setString(4, film.langue);
                    statement.setString(5, film.resume);
                    statement.setString(6, film.lienAffiche);
                    statement.setInt(7, film.realisateur.idPersonne);
                    statement.addBatch();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            int[] count = statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertRoles(Connection connection, List<Film> films) {

    }

    private static List<String> listerGenres(List<Film> films) {
        // faire une liste des genres possibles pour les films, dont les éléments
        // doivent être uniques
        return new LinkedList<String>();
    }

    private static void insertGenres(Connection connection, List<String> genres) {

    }

    private static void insertGenresFilms(Connection connection, List<Film> films) {

    }

    private static List<String> listerPays(List<Film> films) {
        // faire une liste des pays possibles pour les films, dont les éléments doivent
        // être uniques
        return new LinkedList<String>();
    }

    private static void insertPays(Connection connection, List<String> pays) {
        try {
            String sql = "INSERT INTO pays (code_pays, nom) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            pays.forEach(unPays -> {
                try {
                    statement.setString(1, unPays.substring(0, 3));
                    statement.setString(2, unPays);
                    statement.addBatch();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            int[] count = statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertPaysFilms(Connection connection, List<Film> films) {

    }

    private static void insertEmployes(Connection connection) {
        // have fun
    }

    private static void insertCopies(Connection connection, List<Film> films) {
        // nb aléatoire de 1 à 100
    }
}
