package POJOs;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class LocationFilmPK implements Serializable {
    private Date datePret;
    private Integer idClient;
    private Integer noCopieFilm;

    public Date getDatePret() {
        return datePret;
    }

    public void setDatePret(Date datePret) {
        this.datePret = datePret;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public Integer getNoCopieFilm() {
        return noCopieFilm;
    }

    public void setNoCopieFilm(Integer noCopieFilm) {
        this.noCopieFilm = noCopieFilm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationFilmPK that = (LocationFilmPK) o;
        return Objects.equals(datePret, that.datePret) && Objects.equals(idClient, that.idClient) && Objects.equals(noCopieFilm, that.noCopieFilm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(datePret, idClient, noCopieFilm);
    }
}
