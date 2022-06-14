package POJOs;

import java.math.BigInteger;
import java.util.Objects;

public class PaysFilm {
    private String codePays;
    private BigInteger codeFilm;

    public String getCodePays() {
        return codePays;
    }

    public void setCodePays(String codePays) {
        this.codePays = codePays;
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
        PaysFilm paysFilm = (PaysFilm) o;
        return Objects.equals(codePays, paysFilm.codePays) && Objects.equals(codeFilm, paysFilm.codeFilm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codePays, codeFilm);
    }
}
