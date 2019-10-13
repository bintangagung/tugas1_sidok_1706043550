package tugas1.sidok.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name="dokter")
public class DokterModel implements Serializable {
//    id, NIK, nama, jenis kelamin, tanggal lahir, dan tempat lahir.
//    Dokter dapat memiliki 0 sampai N spesialisasi.
//    Dokter juga dapat bertugas di banyak poli.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDokter;

//    @NotNull
//    @Size(max = 20)
//    @Column(name= "nik", nullable = false)
//    private String nik;

    @NotNull
    @Size(max = 20)
    @Column(name= "nama", nullable = false)
    private String nama;

    @NotNull
    @Size(max = 10)
    @Column(name= "jenisKelamin", nullable = false)
    private String jenisKelamin;

    @NotNull
    @Size(max = 20)
    @Column(name= "tanggalLahir", nullable = false)
    private DateTimeFormat tanggalLahir;

    @NotNull
    @Size(max = 10)
    @Column(name= "tempatLahir", nullable = false)
    private String tempatLahir;

    @OneToMany(mappedBy = "dokter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PoliModel> listPoli;

    @OneToMany(mappedBy = "dokter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SpesialisasiModel> listSpesialisasi;

    public Long getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(Long idDokter) {
        this.idDokter = idDokter;
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

    public DateTimeFormat getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(DateTimeFormat tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }


}
