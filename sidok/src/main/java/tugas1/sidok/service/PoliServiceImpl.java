package tugas1.sidok.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tugas1.sidok.model.PoliModel;
import tugas1.sidok.repository.PoliDb;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class PoliServiceImpl implements PoliService {
    @Autowired
    private PoliDb poliDb;

    @Override
    public void addPoli(PoliModel poli) { poliDb.save(poli); }

    @Override
    public List<PoliModel> getListPoli() { return poliDb.findAll(); }

    @Override
    public Optional<PoliModel> getPoliByIdPoli(Long idPoli) {
        return poliDb.findByIdPoli(idPoli);
    }

    @Override
    public PoliModel changePoli(PoliModel poliModel) {
        PoliModel targetPoli = poliDb.findByIdPoli(poliModel.getIdPoli()).get();
        try {
            targetPoli.setNama(poliModel.getNama());
            targetPoli.setLokasi(poliModel.getLokasi());

            poliDb.save(targetPoli);
            return targetPoli;
        } catch (NullPointerException nullException) {
            return null;
        }
    }

    @Override
    public void deletePoli(PoliModel poli) { poliDb.delete(poli); }

}
