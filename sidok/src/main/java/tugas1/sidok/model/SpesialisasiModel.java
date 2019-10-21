package tugas1.sidok.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="spesialisasi")
public class SpesialisasiModel implements Serializable {
//    id, nama, dan gelar.
//    Suatu gelar dapat dimiliki oleh 0 hingga N dokter.
//    Setiap dokter juga dapat memiliki beberapa spesialisasi.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSpesialisasi;

    @NotNull
    @Size(max = 255)
    @Column(name="nama", nullable = false)
    private String nama;

    @NotNull
    @Size(max = 255)
    @Column(name="gelar", nullable = false)
    private String gelar;

    @ManyToMany(mappedBy = "listSpesialisasi", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DokterModel> listDokter;

    public Long getIdSpesialisasi() {
        return idSpesialisasi;
    }

    public void setIdSpesialisasi(Long idSpesialisasi) {
        this.idSpesialisasi = idSpesialisasi;
    }

    public String getNama() { return nama; }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGelar() {
        return gelar;
    }

    public void setGelar(String gelar) {
        this.gelar = gelar;
    }

    public List<DokterModel> getListDokter() {
        return listDokter;
    }

    public void setListDokter(List<DokterModel> listDokter) {
        this.listDokter = listDokter;
    }
}
