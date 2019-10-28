package tugas1.sidok.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tugas1.sidok.model.SpesialisasiModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpesialisasiDb extends JpaRepository<SpesialisasiModel, Long> {
    List<SpesialisasiModel> findByDokterIdDokter(long idDokter);
}