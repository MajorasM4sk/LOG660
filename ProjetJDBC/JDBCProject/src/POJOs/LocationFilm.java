package POJOs;

import java.sql.Date;
import java.util.Objects;

public class LocationFilm {
    private Date datePret;
    private Date dateRetour;
    private Integer idClient;
    private Integer noCopieFilm;

    public Date getDatePret() {
        return datePret;
    }

    public void setDatePret(Date datePret) {
        this.datePret = datePret;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
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
        LocationFilm that = (LocationFilm) o;
        return Objects.equals(datePret, that.datePret) && Objects.equals(dateRetour, that.dateRetour) && Objects.equals(idClient, that.idClient) && Objects.equals(noCopieFilm, that.noCopieFilm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(datePret, dateRetour, idClient, noCopieFilm);
    }
}
