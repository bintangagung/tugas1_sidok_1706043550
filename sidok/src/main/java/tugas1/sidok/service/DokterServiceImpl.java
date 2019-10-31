package tugas1.sidok.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tugas1.sidok.model.DokterModel;
import tugas1.sidok.repository.DokterDb;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DokterServiceImpl implements DokterService {
    @Autowired
    private DokterDb dokterDb;

    @Override
    public void addDokter(DokterModel dokter) { dokterDb.save(dokter); }

    @Override
    public List<DokterModel> getListDokter() { return dokterDb.findAll(); }

    @Override
    public Optional<DokterModel> getDokterByIdDokter(Long idDokter) {
        return dokterDb.findByIdDokter(idDokter);
    }

    @Override
    public Optional<DokterModel> getDokterByNikDokter(String nik) {
        return dokterDb.findByNik(nik);
    }

    @Override
    public Optional<DokterModel> getDokterByNipDokter(String nip) {
        return dokterDb.findByNip(nip);
    }

    @Override
    public DokterModel changeDokter(DokterModel dokterModel) {
        DokterModel targetDokter = dokterDb.findByIdDokter(dokterModel.getIdDokter()).get();
        try {
            targetDokter.setNama(dokterModel.getNama());
            targetDokter.setNip(dokterModel.getNip());
            targetDokter.setNik(dokterModel.getNik());
            targetDokter.setTanggalLahir(dokterModel.getTanggalLahir());
            targetDokter.setTempatLahir(dokterModel.getTempatLahir());
            targetDokter.setJenisKelamin(dokterModel.getJenisKelamin());

            dokterDb.save(targetDokter);
            return targetDokter;
        } catch (NullPointerException nullException) {
            return null;
        }
    }

    @Override
    public void deleteDokter(DokterModel dokter) { dokterDb.delete(dokter); }
    

}
