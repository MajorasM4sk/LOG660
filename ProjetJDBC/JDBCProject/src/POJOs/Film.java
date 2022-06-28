package POJOs;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.HashSet;

@Entity
public class Film {
    @Transient
    public int duree;
    // public List<String> bandeAnnonce = new LinkedList<String>();
    @Transient
    public List<Role> roles = new LinkedList<Role>();
    @Transient
    public Personne realisateur = new Personne();
    @OneToMany
    public List<Genre> genres = new LinkedList<Genre>();
    @OneToMany
    public List<Pays> pays = new LinkedList<Pays>();
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CODE_FILM", nullable = false, precision = 0)
    BigInteger codeFilm;
    @Basic
    @Column(name = "TITRE", nullable = false, length = 200)
    String titre;
    @Basic
    @Column(name = "ANNEE", nullable = true, precision = 0)
    public
    int annee;
    @Basic
    @Column(name = "LANGUE", nullable = true, length = 25)
    String langue;
    @Basic
    @Column(name = "RESUME_FILM", nullable = true, length = 500)
    private String resumeFilm;
    @Basic
    @Column(name = "AFFICHE", nullable = true, length = 400)
    private String affiche;

    public BigInteger getCodeFilm() {
        return codeFilm;
    }

    public void setCodeFilm(BigInteger codeFilm) {
        this.codeFilm = codeFilm;
    }

    public void setCodeFilm(int codeFilm) {
        this.codeFilm = BigInteger.valueOf(codeFilm);
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
