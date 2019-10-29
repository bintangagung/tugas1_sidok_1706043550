package tugas1.sidok.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name="SpesialisasiDokter")
public class SpesialisasiDokterModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSpesialisasiDokter;

    @ManyToOne
    @JoinColumn(name = "idDokter", referencedColumnName = "idDokter", nullable = false)
    private DokterModel dokter;

    @ManyToOne
    @JoinColumn(name = "idSpesialisasi", referencedColumnName = "idSpesialisasi", nullable = false)
    private SpesialisasiModel spesialisasi;

    public Long getIdSpesialisasiDokter() {
        return idSpesialisasiDokter;
    }

    public void setIdSpesialisasiDokter(Long idSpesialisasiDokter) {
        this.idSpesialisasiDokter = idSpesialisasiDokter;
    }

    public DokterModel getDokter() {
        return dokter;
    }

    public void setDokter(DokterModel dokter) {
        this.dokter = dokter;
    }

    public SpesialisasiModel getSpesialisasi() {
        return spesialisasi;
    }

    public void setSpesialisasi(SpesialisasiModel spesialisasi) {
        this.spesialisasi = spesialisasi;
    }
}
