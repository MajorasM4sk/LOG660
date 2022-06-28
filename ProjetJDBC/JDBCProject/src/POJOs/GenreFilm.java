package POJOs;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "GENRE_FILM", schema = "EQUIPE112", catalog = "")
@IdClass(GenreFilmPK.class)
public class GenreFilm {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CODE_FILM", nullable = false, precision = 0)
    private BigInteger codeFilm;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "NO_GENRE", nullable = false, precision = 0)
    private BigInteger noGenre;

    public BigInteger getCodeFilm() {
        return codeFilm;
    }

    public void setCodeFilm(BigInteger codeFilm) {
        this.codeFilm = codeFilm;
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
        GenreFilm genreFilm = (GenreFilm) o;
        return Objects.equals(codeFilm, genreFilm.codeFilm) && Objects.equals(noGenre, genreFilm.noGenre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeFilm, noGenre);
    }
}
