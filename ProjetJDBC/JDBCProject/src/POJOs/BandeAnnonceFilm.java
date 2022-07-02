package POJOs;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "BANDE_ANNONCE_FILM", schema = "EQUIPE112", catalog = "")
@IdClass(BandeAnnonceFilmPK.class)
public class BandeAnnonceFilm {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "LIEN_BANDE_ANNONCE", nullable = false, length = 50)
    private String lienBandeAnnonce;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CODE_FILM", nullable = false, precision = 0)
    private BigInteger codeFilm;

    public String getLienBandeAnnonce() {
        return lienBandeAnnonce;
    }

    public void setLienBandeAnnonce(String lienBandeAnnonce) {
        this.lienBandeAnnonce = lienBandeAnnonce;
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
        BandeAnnonceFilm that = (BandeAnnonceFilm) o;
        return Objects.equals(lienBandeAnnonce, that.lienBandeAnnonce) && Objects.equals(codeFilm, that.codeFilm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lienBandeAnnonce, codeFilm);
    }
}
