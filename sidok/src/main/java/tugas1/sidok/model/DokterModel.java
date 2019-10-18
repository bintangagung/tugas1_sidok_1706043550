package tugas1.sidok.model;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
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
    @Size(max = 255)
    @Column(name= "jenisKelamin", nullable = false)
    private short jenisKelamin;
    //short itu tipe data apa??

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name= "tanggalLahir", nullable = false)
    private Date tanggalLahir;

    @NotNull
    @Size(max = 255)
    @Column(name= "tempatLahir", nullable = false)
    private String tempatLahir;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "spesialisasiDokter",
            joinColumns = @JoinColumn(name = 'idDokter'),
            inversejoinColumns = @JoinColumn(name = "idSpesialisasi")
    )

    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<SpesialisasiModel> listSpesialisasi;

    @OneToMany(mappedBy = "dokter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DokterTugasDiPoli> listPoli;


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

    public short getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(short jenisKelamin) {
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

    public List<SpesialisasiModel> getListSpesialisasi() {
        return listSpesialisasi;
    }

    public void setListSpesialisasi(List<SpesialisasiModel> listSpesialisasi) {
        this.listSpesialisasi = listSpesialisasi;
    }

    public List<DokterTugasDiPoli> getListPoli() {
        return listPoli;
    }

    public void setListPoli(List<DokterTugasDiPoli> listPoli) {
        this.listPoli = listPoli;
    }

}
