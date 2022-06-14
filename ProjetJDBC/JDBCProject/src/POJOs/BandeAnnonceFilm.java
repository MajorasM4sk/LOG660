package POJOs;

import java.math.BigInteger;
import java.util.Objects;

public class BandeAnnonceFilm {
    private String lienBandeAnnonce;
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
