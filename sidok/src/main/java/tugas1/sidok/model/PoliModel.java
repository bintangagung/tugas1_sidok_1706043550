package tugas1.sidok.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name="poli")
public class PoliModel implements Serializable {
//    id, nama poli, dan lokasi. Suatu poli dapat memiliki banyak dokter.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPoli;

    @NotNull
    @Size(max = 20)
    @Column(name="nama", nullable = false)
    private String nama;

    @NotNull
    @Size(max = 30)
    @Column(name="lokasi", nullable = false)
    private String lokasi;

    public Long getIdPoli() {
        return idPoli;
    }

    public void setIdPoli(Long idPoli) {
        this.idPoli = idPoli;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }


}
