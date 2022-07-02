package POJOs;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
public class Genre {
    public String idGenre;
    @Basic
    @Column(name = "NOM", nullable = false, length = 25)
    String nom;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "NO_GENRE", nullable = false, precision = 0)
    private BigInteger noGenre;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public BigInteger getNoGenre() {
        return noGenre;
    }

    public void setNoGenre(BigInteger noGenre) {
        this.noGenre = noGenre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return Objects.equals(nom, genre.nom) && Objects.equals(noGenre, genre.noGenre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noGenre, nom);
    }
}
