package tugas1.sidok.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class DokterPoliModel implements Serializable {
    private long dokterId;
    private String hari;
    private long poliId;

    public int hashCode() {
        return (int) (dokterId+poliId);
    }

    public boolean equals(Object object) {
        if (object instanceof DokterPoliModel) {
            DokterPoliModel otherId = (DokterPoliModel) object;
            return (otherId.dokterId == this.dokterId) && (otherId.poliId == this.poliId) && (otherId.hari.equals(this.hari));
        }
        return false;
    }
}

@Entity
@Table(name="bertugas")
@IdClass(DokterPoliModel.class)
class DokterTugasDiPoli {
    @Id
    private long dokterId;
    @Id
    private long poliId;

    @NotNull
    @Size(max = 255)
    @Column(name= "hari", nullable = false)
    private String hari;

    @ManyToOne
    @JoinColumn(name = "dokterId", referencedColumnName = "idDokter", updatable = false, insertable = false)
    private DokterModel dokter;

    @ManyToOne
    @JoinColumn(name = "poliId", referencedColumnName = "idPoli", updatable = false, insertable = false)
    private PoliModel poli;

    public long getDokterId() {
        return dokterId;
    }

    public void setDokterId(long dokterId) {
        this.dokterId = dokterId;
    }

    public long getPoliId() {
        return poliId;
    }

    public void setPoliId(long poliId) {
        this.poliId = poliId;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public DokterModel getDokter() {
        return dokter;
    }

    public void setDokter(DokterModel dokter) {
        this.dokter = dokter;
    }

    public PoliModel getPoli() {
        return poli;
    }

    public void setPoli(PoliModel poli) {
        this.poli = poli;
    }
}