package tugas1.sidok.service;

import org.springframework.beans.factory.annotation.Autowired;
import tugas1.sidok.model.DokterModel;
import tugas1.sidok.repository.DokterDb;

import java.util.List;
import java.util.Optional;

public class DokterServiceImpl implements DokterService {
    @Autowired
    private DokterDb dokterDb;

    @Override
    public void addDokter(DokterModel dokter) { dokterDb.save(dokter); }

    @Override
    public List<DokterModel> getDokterList() { return dokterDb.findAll(); }

    @Override
    public Optional<DokterModel> getDokterByIdDokter(Long idDokter) {
        return dokterDb.findByIdDokter(idDokter);
    }

    @Override
    public DokterModel changeDokter(DokterModel dokterModel) {
        DokterModel targetDokter = dokterDb.findById(dokterModel.getIdDokter()).get();
        try {
            targetDokter.setNama(dokterModel.getNama());
            targetDokter.setJenisKelamin(dokterModel.getJenisKelamin());
            targetDokter.setTanggalLahir(dokterModel.getTanggalLahir());
            targetDokter.setTempatLahir(dokterModel.getTempatLahir());
            dokterDb.save(targetDokter);
            return targetDokter;
        } catch (NullPointerException nullException) {
            return null;
        }
    }

    @Override
    public List<DokterModel> getDokterListOrderByNama() {
        return dokterDb.findAllByOrderByNamaAsc();
    }

    @Override
    public void deleteDokter(DokterModel dokter) { dokterDb.delete(dokter); }
}
