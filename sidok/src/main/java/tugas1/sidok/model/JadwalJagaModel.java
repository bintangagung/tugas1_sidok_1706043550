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
}
