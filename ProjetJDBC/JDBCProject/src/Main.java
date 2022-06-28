import POJOs.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String argv[]) {
        String url = "jdbc:oracle:thin:EQUIPE112/C66VmkzD@log660ora12c.logti.etsmtl.ca:1521:LOG660";

        try {
            Connection connection = DriverManager.getConnection(url);
            if (connection != null) {
                System.out.println("Connexion à la BD réussie !");
                connection.setAutoCommit(false);

                Videotheque videotheque = XmlParser.fetchData();

                System.out.println("Insertion des types de forfait...");
                insertForfaits(connection);

                System.out.println("Insertion des clients...");
                insertClients(connection, videotheque.clients);

                System.out.println("Insertion des cartes de crédit...");
                insertCartesCredit(connection, videotheque.clients);

                System.out.println("Insertion des personnes...");
                insertPersonnes(connection, videotheque.personnes);

                System.out.println("Insertion des films...");
                insertFilms(connection, videotheque.films);

                System.out.println("Insertion des réalisateurs...");
                insertRealisateurs(connection, videotheque.films);

                System.out.println("Insertion des roles des acteurs...");
                insertRoles(connection, videotheque.films);

                System.out.println("Détection des genres...");
                List<Genre> genres = listerGenres(videotheque.films);

                System.out.println("Insertion des genres...");
                insertGenres(connection, genres);

                System.out.println("Insertion des liaisons film-genre...");
                insertGenresFilms(connection, videotheque.films, genres);

                System.out.println("Détection des pays...");
                List<Pays> pays = listerPays(videotheque.films);

                System.out.println("Insertion des pays...");
                insertPays(connection, pays);

                System.out.println("Insertion des liaisons film-pays...");
                insertPaysFilms(connection, videotheque.films, pays);

                // System.out.println("Insertion d'employés bidons...");
                // insertEmployes(connection);

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
                    statement.setString(4, client.telephone.replaceAll("-", ""));
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
            String sql = "INSERT INTO carte_credit (no_carte, type_carte, annee_expiration, mois_expiration, cvv, nom_proprio, id_client) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            clients.forEach(client -> {
                try {
                    statement.setString(1, client.carteCredit.getNoCarte().replaceAll(" ", ""));
                    statement.setString(2, client.carteCredit.getTypeCarte());
                    statement.setString(3, client.carteCredit.anneeExpiration);
                    statement.setString(4, client.carteCredit.moisExpiration);
                    statement.setString(5, "000");
                    statement.setString(6, client.prenom + " " + client.nom);
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
        Forfait debutant = new Forfait();
        Forfait intermediaire = new Forfait();
        Forfait avance = new Forfait();
        debutant.codeForfait = "D";
        debutant.typeForfait = "Débutant";
        debutant.cout = 5;
        debutant.locationMax = 1;
        debutant.duree = 10;
        intermediaire.codeForfait = "I";
        intermediaire.typeForfait = "Intermédiaire";
        intermediaire.cout = 10;
        intermediaire.locationMax = 5;
        intermediaire.duree = 30;
        avance.codeForfait = "A";
        avance.typeForfait = "Avancé";
        avance.cout = 15;
        avance.locationMax = 10;
        avance.duree = 10000000;
        List<Forfait> forfaits = new LinkedList<Forfait>();
        forfaits.add(debutant);
        forfaits.add(intermediaire);
        forfaits.add(avance);
        try {
            String sql = "INSERT INTO type_forfait (code_forfait, type_forfait, cout, location_max, duree) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            forfaits.forEach(forfait -> {
                try {
                    statement.setString(1, forfait.codeForfait);
                    statement.setString(2, forfait.typeForfait);
                    statement.setInt(3, forfait.cout);
                    statement.setInt(4, forfait.locationMax);
                    statement.setInt(5, forfait.duree);
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

    private static void insertPersonnes(Connection connection, List<Personne> personnes) {
        try {
            String sql = "INSERT INTO personne (id_personne, nom, date_naissance, lieu_naissance, photo, biographie) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            personnes.forEach(personne -> {
                try {
                    statement.setInt(1, personne.idPersonne);
                    statement.setString(2, personne.getNom());
                    statement.setString(3, personne.dateNaissance);
                    statement.setString(4, personne.getLieuNaissance());
                    statement.setString(5, personne.getPhoto());
                    statement.setString(6, personne.getBiographie());
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
            String sql = "INSERT INTO film (code_film, titre, annee, langue, resume_film, affiche) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            films.forEach(film -> {
                try {
                    statement.setInt(1, film.codeFilm);
                    statement.setString(2, film.getTitre());
                    statement.setInt(3, film.annee);
                    statement.setString(4, film.getLangue());
                    statement.setString(5, film.resume);
                    statement.setString(6, film.lienAffiche);
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

    private static void insertRealisateurs(Connection connection, List<Film> films) {
        try {
            String sql = "INSERT INTO realisateur_film (id_personne, code_film) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            films.forEach(film -> {
                if (film.realisateur.idPersonne != 0) {
                    try {
                        statement.setInt(1, film.realisateur.idPersonne);
                        statement.setInt(2, film.codeFilm);
                        statement.addBatch();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            });

            int[] count = statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertRoles(Connection connection, List<Film> films) {
        try {
            String sql = "INSERT INTO role_film (personnage, id_personne, code_film) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            films.forEach(film -> {
                film.roles.forEach(role -> {
                    try {
                        statement.setString(1, role.personnage);
                        statement.setInt(2, role.acteur.idPersonne);
                        statement.setInt(3, film.codeFilm);
                        statement.addBatch();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
            });

            int[] count = statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static List<Genre> listerGenres(List<Film> films) {
        // faire une liste des genres possibles pour les films, dont les éléments
        // doivent être uniques
        List<Genre> genres = new ArrayList<Genre>();
        films.forEach(film -> {
            // ne se trouve pas dans la liste des genres?
            // si oui ajouter
            // si non continuer
            film.genres.forEach(genreFilm -> {
                boolean found = false;
                for (int i = 0; i < genres.size(); i++) {
                    if (genres.get(i).getNom().equals(genreFilm.getNom())) {
                        found = true;
                    }
                }
                if (!found) {
                    genres.add(genreFilm);
                }
            });
        });
        return genres;
    }

    private static void insertGenres(Connection connection, List<Genre> genres) {
        try {
            String sql = "INSERT INTO genre (no_genre, nom) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            for (int i = 1; i < genres.size() + 1; i++) {
                try {
                    statement.setInt(1, i);
                    statement.setString(2, genres.get(i - 1).getNom());
                    statement.addBatch();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            int[] count = statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertGenresFilms(Connection connection, List<Film> films, List<Genre> genres) {
        try {
            String sql = "INSERT INTO genre_film (code_film, no_genre) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            films.forEach(film -> {
                film.genres.forEach(genre -> {
                    int genreId = getGenreId(genres, genre.getNom());
                    try {
                        statement.setInt(1, film.codeFilm);
                        statement.setInt(2, genreId);
                        statement.addBatch();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
            });

            int[] count = statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int getGenreId(List<Genre> genres, String genreName) {
        for (int i = 0; i < genres.size(); i++) {
            if (genres.get(i).getNom().equals(genreName)) {
                return i + 1;
            }
        }
        return -1;
    }

    private static List<Pays> listerPays(List<Film> films) {
        List<Pays> pays = new ArrayList<Pays>();
        films.forEach(film -> {
            // ne se trouve pas dans la liste des genres?
            // si oui ajouter
            // si non continuer
            film.pays.forEach(paysFilm -> {
                boolean found = false;
                for (int i = 0; i < pays.size(); i++) {
                    if (pays.get(i).getNom().equals(paysFilm.getNom())) {
                        found = true;
                    }
                }
                if (!found) {
                    pays.add(paysFilm);
                }
            });
        });
        return pays;
    }

    private static void insertPays(Connection connection, List<Pays> pays) {
        try {
            String sql = "INSERT INTO pays (code_pays, nom) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            for (int i = 1; i < pays.size() + 1; i++) {
                try {
                    statement.setInt(1, i);
                    statement.setString(2, pays.get(i - 1).getNom());
                    statement.addBatch();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            int[] count = statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertPaysFilms(Connection connection, List<Film> films, List<Pays> paysList) {
        try {
            String sql = "INSERT INTO pays_film (code_film, code_pays) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            films.forEach(film -> {
                film.pays.forEach(pays -> {
                    int paysId = getPaysId(paysList, pays.getNom());
                    try {
                        statement.setInt(1, film.codeFilm);
                        statement.setInt(2, paysId);
                        statement.addBatch();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
            });

            int[] count = statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int getPaysId(List<Pays> pays, String paysName) {
        for (int i = 0; i < pays.size(); i++) {
            if (pays.get(i).getNom().equals(paysName)) {
                return i + 1;
            }
        }
        return -1;
    }

    private static void insertCopies(Connection connection, List<Film> films) {
        try {
            String sql = "INSERT INTO copie_film (no_copie_film, code_film) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            int idCopie = 1;
            for (int i = 0; i < films.size(); i++) {
                Random r = new Random();
                int nbCopies = r.nextInt(100) + 1;
                for (int j = 0; j < nbCopies; j++) {
                    try {
                        statement.setInt(1, idCopie);
                        statement.setInt(2, films.get(i).codeFilm);
                        statement.addBatch();
                        idCopie++;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

            int[] count = statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertEmployes(Connection connection) {
        // have fun
    }
}
