package tugas1.sidok.service;

import tugas1.sidok.model.DokterModel;
import tugas1.sidok.model.SpesialisasiModel;

import java.util.List;
import java.util.Optional;

public interface SpesialisasiService {
    List<SpesialisasiModel> getAll();

    Optional<SpesialisasiModel> getByIdSpesialisasi(Long idSpesialisasi);
}
