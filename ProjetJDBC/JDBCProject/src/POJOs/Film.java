package POJOs;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Film {
    public int duree;
    public String resume;
    public String lienAffiche;
    // public List<String> bandeAnnonce = new LinkedList<String>();
    public List<Role> roles = new LinkedList<Role>();
    public Personne realisateur = new Personne();
    public List<Genre> genres = new LinkedList<Genre>();
    public List<Pays> pays = new LinkedList<Pays>();
    public int codeFilm;
    String titre;
    public int annee;
    String langue;
    private String resumeFilm;
    private String affiche;

    public int getCodeFilm() {
        return codeFilm;
    }

    public void setCodeFilm(int codeFilm) {
        this.codeFilm = codeFilm;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(Short annee) {
        this.annee = annee;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getResumeFilm() {
        return resumeFilm;
    }

    public void setResumeFilm(String resumeFilm) {
        this.resumeFilm = resumeFilm;
    }

    public String getAffiche() {
        return affiche;
    }

    public void setAffiche(String affiche) {
        this.affiche = affiche;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return codeFilm == film.codeFilm && annee == film.annee && Objects.equals(titre, film.titre) && Objects.equals(langue, film.langue) && Objects.equals(resumeFilm, film.resumeFilm) && Objects.equals(affiche, film.affiche);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeFilm, titre, annee, langue, resumeFilm, affiche);
    }
}
