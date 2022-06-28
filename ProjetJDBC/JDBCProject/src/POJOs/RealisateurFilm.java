package POJOs;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "REALISATEUR_FILM", schema = "EQUIPE112", catalog = "")
@IdClass(RealisateurFilmPK.class)
public class RealisateurFilm {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_PERSONNE", nullable = false, precision = 0)
    private BigInteger idPersonne;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CODE_FILM", nullable = false, precision = 0)
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
