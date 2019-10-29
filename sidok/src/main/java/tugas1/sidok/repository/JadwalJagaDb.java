package tugas1.sidok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tugas1.sidok.model.JadwalJagaModel;

import java.util.List;

@Repository
public interface JadwalJagaDb extends JpaRepository<JadwalJagaModel, Long> {

}
