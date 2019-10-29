package tugas1.sidok.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tugas1.sidok.model.DokterModel;
import tugas1.sidok.model.SpesialisasiModel;
import tugas1.sidok.repository.SpesialisasiDb;

import java.util.List;
import java.util.Optional;

@Service
public class SpesialisasiServiceImpl implements SpesialisasiService {
    @Autowired
    private SpesialisasiDb spesialisasiDb;

    @Override
    public List<SpesialisasiModel> getAll() {
        return spesialisasiDb.findAll();
    }

    @Override
    public Optional<SpesialisasiModel> getByIdSpesialisasi(Long idSpesialisasi) {
        return spesialisasiDb.findByIdSpesialisasi(idSpesialisasi);
    }
}
