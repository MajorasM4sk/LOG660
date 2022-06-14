package POJOs;

import java.math.BigInteger;
import java.util.Objects;

public class TypeForfait {
    private String codeForfait;
    private String typeForfait;
    private Byte cout;
    private Byte locationMax;
    private BigInteger duree;

    public String getCodeForfait() {
        return codeForfait;
    }

    public void setCodeForfait(String codeForfait) {
        this.codeForfait = codeForfait;
    }

    public String getTypeForfait() {
        return typeForfait;
    }

    public void setTypeForfait(String typeForfait) {
        this.typeForfait = typeForfait;
    }

    public Byte getCout() {
        return cout;
    }

    public void setCout(Byte cout) {
        this.cout = cout;
    }

    public Byte getLocationMax() {
        return locationMax;
    }

    public void setLocationMax(Byte locationMax) {
        this.locationMax = locationMax;
    }

    public BigInteger getDuree() {
        return duree;
    }

    public void setDuree(BigInteger duree) {
        this.duree = duree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeForfait that = (TypeForfait) o;
        return Objects.equals(codeForfait, that.codeForfait) && Objects.equals(typeForfait, that.typeForfait) && Objects.equals(cout, that.cout) && Objects.equals(locationMax, that.locationMax) && Objects.equals(duree, that.duree);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeForfait, typeForfait, cout, locationMax, duree);
    }
}
