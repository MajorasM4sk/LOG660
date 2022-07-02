package POJOs;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

public class PaysFilmPK implements Serializable {
    @Column(name = "CODE_PAYS", nullable = false, length = 3)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String codePays;
    @Column(name = "CODE_FILM", nullable = false, precision = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        PaysFilmPK that = (PaysFilmPK) o;
        return Objects.equals(codePays, that.codePays) && Objects.equals(codeFilm, that.codeFilm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codePays, codeFilm);
    }
}
