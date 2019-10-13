package tugas1.sidok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tugas1.sidok.model.DokterModel;


@Repository
public interface DokterDb extends JpaRepository<DokterModel, Long> {

}
