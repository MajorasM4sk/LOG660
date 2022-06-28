package POJOs;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "ROLE_FILM", schema = "EQUIPE112", catalog = "")
@IdClass(RoleFilmPK.class)
public class RoleFilm {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "PERSONNAGE", nullable = false, length = 200)
    private String personnage;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_PERSONNE", nullable = false, precision = 0)
    private BigInteger idPersonne;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CODE_FILM", nullable = false, precision = 0)
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
