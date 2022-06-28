package POJOs;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "COPIE_FILM", schema = "EQUIPE112", catalog = "")
public class CopieFilm {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "NO_COPIE_FILM", nullable = false, precision = 0)
    private Integer noCopieFilm;
    @Basic
    @Column(name = "CODE_FILM", nullable = false, precision = 0)
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
