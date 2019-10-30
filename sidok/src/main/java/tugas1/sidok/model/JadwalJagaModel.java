package tugas1.sidok.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name="jadwalJaga")
public class JadwalJagaModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJadwalJaga;

    @NotNull
    @Size(max = 255)
    @Column(name= "hari", nullable = false)
    private String hari;

    @ManyToOne
    @JoinColumn(name = "idDokter", referencedColumnName = "idDokter", nullable = false)
    private DokterModel dokter;

    @ManyToOne
    @JoinColumn(name = "idPoli", referencedColumnName = "idPoli", nullable = false)
    private PoliModel poli;

    public Long getIdJadwalJaga() {
        return idJadwalJaga;
    }

    public void setIdJadwalJaga(Long idJadwalJaga) {
        this.idJadwalJaga = idJadwalJaga;
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
