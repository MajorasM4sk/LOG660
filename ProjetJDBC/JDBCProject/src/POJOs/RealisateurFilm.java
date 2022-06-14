package POJOs;

import java.math.BigInteger;
import java.util.Objects;

public class RealisateurFilm {
    private BigInteger idPersonne;
    private BigInteger codeFilm;

    public BigInteger getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(BigInteger idPersonne) {
        this.idPersonne = idPersonne;
    }

    public BigInteger getCodeFilm() {
        return codeFilm;
    }

    public void setCodeFilm(BigInteger codeFilm) {
        this.codeFilm = codeFilm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RealisateurFilm that = (RealisateurFilm) o;
        return Objects.equals(idPersonne, that.idPersonne) && Objects.equals(codeFilm, that.codeFilm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPersonne, codeFilm);
    }
}
