package POJOs;

import java.math.BigInteger;
import java.util.Objects;

public class GenreFilm {
    private BigInteger codeFilm;
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
