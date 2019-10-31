package tugas1.sidok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tugas1.sidok.model.SpesialisasiDokterModel;

import java.util.List;
import java.util.Optional;


@Repository
public interface SpesialisasiDokterDb extends JpaRepository<SpesialisasiDokterModel, Long> {
    List<SpesialisasiDokterModel> findAllByDokterIdDokter(Long idDokter);
    List<SpesialisasiDokterModel> findAllBySpesialisasiIdSpesialisasi(Long idSpesialisasi);
}
