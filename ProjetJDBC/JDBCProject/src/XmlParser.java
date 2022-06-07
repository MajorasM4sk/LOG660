import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import POJOs.CarteCredit;
import POJOs.Client;
import POJOs.Film;
import POJOs.Forfait;
import POJOs.Genre;
import POJOs.Pays;
import POJOs.Personne;
import POJOs.Role;
import POJOs.Videotheque;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

//Source : https://stackoverflow.com/questions/428073/what-is-the-best-simplest-way-to-read-in-an-xml-file-in-java-application
public class XmlParser {

    public static void main(String argv[]) {
        try {
            Document docClients = XmlParser.getDocFromFileName("../../Donnees/db_latin1/clients_latin1.xml");
            Document docFilms = XmlParser.getDocFromFileName("../../Donnees/db_latin1/films_latin1.xml");
            Document docPersonnes = XmlParser.getDocFromFileName("../../Donnees/db_latin1/personnes_latin1.xml");

            NodeList nClients = docClients.getElementsByTagName("client");
            NodeList nFilms = docFilms.getElementsByTagName("film");
            NodeList nPersonnes = docPersonnes.getElementsByTagName("personne");

            List<Client> clients = XmlParser.readClients(nClients);
            List<Film> films = XmlParser.readFilms(nFilms);
            List<Personne> personnes = XmlParser.readPersonnes(nPersonnes);
            System.out.println(clients.get(0).idClient);
            System.out.println(clients.get(0).prenom);
            System.out.println(clients.get(0).nom);
            System.out.println(clients.get(0).courriel);
            System.out.println(clients.get(0).telephone);
            System.out.println(clients.get(0).dateNaissance);
            System.out.println(clients.get(0).adresse);
            System.out.println(clients.get(0).ville);
            System.out.println(clients.get(0).province);
            System.out.println(clients.get(0).codePostal);
            System.out.println(clients.get(0).carteCredit.typeCarte);
            System.out.println(clients.get(0).carteCredit.noCarte);
            System.out.println(clients.get(0).carteCredit.moisExpiration);
            System.out.println(clients.get(0).carteCredit.anneeExpiration);
            System.out.println(clients.get(0).motDePasse);
            System.out.println(clients.get(0).forfait.codeForfait);
            System.out.println("------------------");
            System.out.println(films.get(0).codeFilm);
            System.out.println(films.get(0).titre);
            System.out.println(films.get(0).annee);
            System.out.println(films.get(0).duree);
            System.out.println(films.get(0).langue);
            System.out.println(films.get(0).resume);
            System.out.println(films.get(0).lienAffiche);

            System.out.println(films.get(0).roles.get(0).personnage);

            System.out.println(films.get(0).realisateur.idPersonne);
            // Les infos suivantes ne sont pas importantes, elles se retrouvent déjà dans
            // "personnes"
            // System.out.println(films.get(0).realisateurs.get(0).nom);
            // System.out.println(films.get(0).realisateurs.get(0).dateNaissance);
            // System.out.println(films.get(0).realisateurs.get(0).lieu);
            // System.out.println(films.get(0).realisateurs.get(0).photo);
            // System.out.println(films.get(0).realisateurs.get(0).biographie);

            System.out.println(films.get(0).genres.get(0).idGenre);
            System.out.println(films.get(0).genres.get(0).nom);

            System.out.println(films.get(0).pays.get(0).codePays);
            System.out.println(films.get(0).pays.get(0).nom);
            System.out.println("------------------");
            System.out.println(personnes.get(0).idPersonne);
            System.out.println(personnes.get(0).nom);
            System.out.println(personnes.get(0).dateNaissance);
            System.out.println(personnes.get(0).lieuNaissance);
            System.out.println(personnes.get(0).photo);
            System.out.println(personnes.get(0).biographie);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Videotheque fetchData() {
        Document docClients = XmlParser.getDocFromFileName("../../Donnees/db_latin1/clients_latin1.xml");
        Document docFilms = XmlParser.getDocFromFileName("../../Donnees/db_latin1/films_latin1.xml");
        Document docPersonnes = XmlParser.getDocFromFileName("../../Donnees/db_latin1/personnes_latin1.xml");

        NodeList nClients = docClients.getElementsByTagName("client");
        NodeList nFilms = docFilms.getElementsByTagName("film");
        NodeList nPersonnes = docPersonnes.getElementsByTagName("personne");

        System.out.println("Début de la lecture des clients...");
        List<Client> clients = XmlParser.readClients(nClients);
        System.out.println("Début de la lecture des films...");
        List<Film> films = XmlParser.readFilms(nFilms);
        System.out.println("Début de la lecture des personnes...");
        List<Personne> personnes = XmlParser.readPersonnes(nPersonnes);

        Videotheque videotheque = new Videotheque();
        videotheque.clients = clients;
        videotheque.films = films;
        videotheque.personnes = personnes;

        return videotheque;
    }

    private static Document getDocFromFileName(String fileName) {
        try {
            File file = new File(fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            return doc;
        } catch (Exception e) {
            System.out.println("Erreur lors de la lecture du fichier : " + fileName);
            e.printStackTrace();
        }
        return null;
    }

    private static String getTagText(Element e, String tag) {
        if (e != null) {
            NodeList list = e.getElementsByTagName(tag);
            if (list.getLength() > 0) {
                return list.item(0).getTextContent();
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    private static List<Client> readClients(NodeList nClients) {
        List<Client> clients = new LinkedList<Client>();
        for (int i = 0; i < nClients.getLength(); i++) {
            Node nClient = nClients.item(i);
            Client c = new Client();
            Element e = (Element) nClient;
            c.idClient = Integer.parseInt(e.getAttribute("id"));
            c.prenom = XmlParser.getTagText(e, "prenom");
            c.nom = XmlParser.getTagText(e, "nom-famille");
            c.courriel = XmlParser.getTagText(e, "courriel");
            c.telephone = XmlParser.getTagText(e, "tel");
            c.dateNaissance = XmlParser.getTagText(e, "anniversaire");
            c.adresse = XmlParser.getTagText(e, "adresse");
            c.ville = XmlParser.getTagText(e, "ville");
            c.province = XmlParser.getTagText(e, "province");
            c.codePostal = XmlParser.getTagText(e, "code-postal");
            c.motDePasse = XmlParser.getTagText(e, "mot-de-passe");
            Forfait forfait = new Forfait();
            forfait.codeForfait = XmlParser.getTagText(e, "forfait");
            c.forfait = forfait;
            CarteCredit carteCredit = new CarteCredit();
            Element eCarteCredit = (Element) e.getElementsByTagName("info-credit").item(0);
            carteCredit.noCarte = XmlParser.getTagText(eCarteCredit, "no");
            carteCredit.typeCarte = XmlParser.getTagText(eCarteCredit, "carte");
            carteCredit.moisExpiration = XmlParser.getTagText(eCarteCredit, "exp-mois");
            carteCredit.anneeExpiration = XmlParser.getTagText(eCarteCredit, "exp-annee");
            c.carteCredit = carteCredit;
            clients.add(c);
        }
        return clients;
    }

    private static List<Film> readFilms(NodeList nFilms) {
        List<Film> films = new LinkedList<Film>();
        for (int i = 0; i < nFilms.getLength(); i++) {
            Node nFilm = nFilms.item(i);
            Film f = new Film();
            Element e = (Element) nFilm;

            f.codeFilm = Integer.parseInt(e.getAttribute("id"));
            f.titre = XmlParser.getTagText(e, "titre");
            f.annee = Integer.parseInt(XmlParser.getTagText(e, "annee"));
            NodeList nPaysList = e.getElementsByTagName("pays");
            f.pays = XmlParser.readPays(nPaysList);
            f.langue = XmlParser.getTagText(e, "langue");
            f.duree = Integer.parseInt(XmlParser.getTagText(e, "duree"));
            f.resume = XmlParser.getTagText(e, "resume");

            NodeList nGenres = e.getElementsByTagName("genre");
            Node nRealisateur = e.getElementsByTagName("realisateur").item(0);
            Element eRealisateur = (Element) nRealisateur;
            NodeList nRoles = e.getElementsByTagName("role");

            f.genres = XmlParser.readGenres(nGenres);
            if (eRealisateur != null) {
                f.realisateur.idPersonne = Integer.parseInt(eRealisateur.getAttribute("id"));
            }
            f.roles = XmlParser.readRoles(nRoles);

            f.lienAffiche = XmlParser.getTagText(e, "poster");
            films.add(f);
        }
        return films;
    }

    private static List<Pays> readPays(NodeList nPaysList) {
        List<Pays> paysList = new LinkedList<Pays>();
        for (int i = 0; i < nPaysList.getLength(); i++) {
            Node nPays = nPaysList.item(i);
            Pays p = new Pays();
            int max = 3;
            p.nom = nPays.getTextContent();
            if (p.nom.length() <= 2) {
                max = p.nom.length();
            }
            p.codePays = p.nom.substring(0, max).toUpperCase();
            paysList.add(p);
        }
        return paysList;
    }

    private static List<Genre> readGenres(NodeList nGenres) {
        List<Genre> genres = new LinkedList<Genre>();
        for (int i = 0; i < nGenres.getLength(); i++) {
            Node nGenre = nGenres.item(i);
            Genre g = new Genre();
            g.nom = nGenre.getTextContent();
            g.idGenre = g.nom.substring(0, 3);
            genres.add(g);
        }
        return genres;
    }

    private static List<Personne> readRealisateurs(NodeList nRealisateurs) {
        List<Personne> realisateurs = new LinkedList<Personne>();
        for (int i = 0; i < nRealisateurs.getLength(); i++) {
            Node nRealisateur = nRealisateurs.item(i);
            Element e = (Element) nRealisateur;
            Personne realisateur = new Personne();
            realisateur.idPersonne = Integer.parseInt(e.getAttribute("id"));
            realisateur.nom = nRealisateur.getTextContent();
            realisateurs.add(realisateur);
        }
        return realisateurs;
    }

    private static List<Role> readRoles(NodeList nRoles) {
        List<Role> roles = new LinkedList<Role>();
        for (int i = 0; i < nRoles.getLength(); i++) {
            Node nRole = nRoles.item(i);
            Role role = new Role();
            Element eRole = (Element) nRole;
            Personne acteur = new Personne();
            Element eActeur = (Element) eRole.getElementsByTagName("acteur").item(0);
            acteur.idPersonne = Integer.parseInt(eActeur.getAttribute("id"));
            role.personnage = XmlParser.getTagText(eRole, "personnage");
            roles.add(role);
        }
        return roles;
    }

    private static List<Personne> readPersonnes(NodeList nPersonnes) {
        List<Personne> personnes = new LinkedList<Personne>();
        for (int i = 0; i < nPersonnes.getLength(); i++) {
            Node nPersonne = nPersonnes.item(i);
            Element e = (Element) nPersonne;
            Personne personne = new Personne();
            personne.idPersonne = Integer.parseInt(e.getAttribute("id"));
            personne.nom = XmlParser.getTagText(e, "nom");
            Element eNaissance = (Element) e.getElementsByTagName("naissance").item(0);
            personne.dateNaissance = XmlParser.getTagText(eNaissance, "anniversaire");
            personne.lieuNaissance = XmlParser.getTagText(eNaissance, "lieu");
            personne.photo = XmlParser.getTagText(e, "photo");
            personne.biographie = XmlParser.getTagText(e, "bio");
            personnes.add(personne);
        }
        return personnes;
    }
}