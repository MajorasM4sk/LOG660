package POJOs;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "LOCATION_FILM", schema = "EQUIPE112", catalog = "")
@IdClass(LocationFilmPK.class)
public class LocationFilm {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "DATE_PRET", nullable = false)
    private Date datePret;
    @Basic
    @Column(name = "DATE_RETOUR", nullable = true)
    private Date dateRetour;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_CLIENT", nullable = false, precision = 0)
    private Integer idClient;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "NO_COPIE_FILM", nullable = false, precision = 0)
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
