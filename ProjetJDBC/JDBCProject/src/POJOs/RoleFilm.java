package POJOs;

import java.math.BigInteger;
import java.util.Objects;

public class RoleFilm {
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
        RoleFilm roleFilm = (RoleFilm) o;
        return Objects.equals(personnage, roleFilm.personnage) && Objects.equals(idPersonne, roleFilm.idPersonne) && Objects.equals(codeFilm, roleFilm.codeFilm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personnage, idPersonne, codeFilm);
    }
}
