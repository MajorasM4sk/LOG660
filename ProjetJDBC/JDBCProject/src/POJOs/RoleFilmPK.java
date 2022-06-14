package POJOs;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

public class RoleFilmPK implements Serializable {
    private String personnage;
    private BigInteger idPersonne;
    private BigInteger codeFilm;

    public String getPersonnage() {
        return personnage;
    }

    public void setPersonnage(String personnage) {
        this.personnage = personnage;
    }

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
        RoleFilmPK that = (RoleFilmPK) o;
        return Objects.equals(personnage, that.personnage) && Objects.equals(idPersonne, that.idPersonne) && Objects.equals(codeFilm, that.codeFilm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personnage, idPersonne, codeFilm);
    }
}
