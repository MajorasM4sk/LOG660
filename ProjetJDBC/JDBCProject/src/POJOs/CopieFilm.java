package POJOs;

import java.math.BigInteger;
import java.util.Objects;

public class CopieFilm {
    private Integer noCopieFilm;
    private BigInteger codeFilm;

    public Integer getNoCopieFilm() {
        return noCopieFilm;
    }

    public void setNoCopieFilm(Integer noCopieFilm) {
        this.noCopieFilm = noCopieFilm;
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
        CopieFilm copieFilm = (CopieFilm) o;
        return Objects.equals(noCopieFilm, copieFilm.noCopieFilm) && Objects.equals(codeFilm, copieFilm.codeFilm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noCopieFilm, codeFilm);
    }
}
