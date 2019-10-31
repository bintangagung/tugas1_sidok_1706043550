package tugas1.sidok.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="dokter")
public class DokterModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDokter;

    @NotNull
    @Size(max = 255)
    @Column(name= "nip", nullable = false, unique=true)
    private String nip;

    @NotNull
    @Size(max = 255)
    @Column(name= "nik", nullable = false, unique=true)
    private String nik;

    @NotNull
    @Size(max = 255)
    @Column(name= "nama", nullable = false)
    private String nama;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name= "tanggalLahir", nullable = false)
    private Date tanggalLahir;

    @NotNull
    @Size(max = 255)
    @Column(name= "tempatLahir", nullable = false)
    private String tempatLahir;

    @NotNull
    @Size(max = 255)
    @Column(name= "jenisKelamin", nullable = false)
    private String jenisKelamin;

    @OneToMany(mappedBy = "dokter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<JadwalJagaModel> listPoli;

    @OneToMany(mappedBy = "dokter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SpesialisasiDokterModel> listSpesialisasi;

    public Long getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(Long idDokter) {
        this.idDokter = idDokter;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public List<JadwalJagaModel> getListPoli() {
        return listPoli;
    }

    public void setListPoli(List<JadwalJagaModel> listPoli) {
        this.listPoli = listPoli;
    }

    public List<SpesialisasiDokterModel> getListSpesialisasi() {
        return listSpesialisasi;
    }

    public void setListSpesialisasi(List<SpesialisasiDokterModel> listSpesialisasi) {
        this.listSpesialisasi = listSpesialisasi;
    }
}
