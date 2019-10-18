package tugas1.sidok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tugas1.sidok.model.DokterModel;

import java.util.Optional;


@Repository
public interface DokterDb extends JpaRepository<DokterModel, Long> {
    Optional<DokterModel> findByIdDokter(Long idDokter);
//    Optional<DokterModel> findByNip(String nip);
    Optional<DokterModel> findByNik(String nik);
}