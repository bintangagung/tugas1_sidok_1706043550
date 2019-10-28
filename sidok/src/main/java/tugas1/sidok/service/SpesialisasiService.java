package tugas1.sidok.service;

import tugas1.sidok.model.SpesialisasiModel;

import java.util.List;
import java.util.Optional;

public interface SpesialisasiService {
    List<SpesialisasiModel> findAllSpesialisasiByIdDokter(long idDokter);
    List<SpesialisasiModel> getListSpesialisasi(long idDokter);
}
