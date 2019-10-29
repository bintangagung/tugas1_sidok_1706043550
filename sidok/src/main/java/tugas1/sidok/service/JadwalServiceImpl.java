package tugas1.sidok.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tugas1.sidok.model.JadwalModel;
import tugas1.sidok.model.PoliModel;
import tugas1.sidok.repository.JadwalDb;
import tugas1.sidok.repository.PoliDb;

import java.util.List;

@Service
@Transactional
public class JadwalServiceImpl implements JadwalService {
    @Autowired
    private JadwalDb jadwalDb;

    @Override
    public void addJadwal(JadwalModel jadwal) { jadwalDb.save(jadwal); }

    @Override
    public List<JadwalModel> getListJadwal() { return jadwalDb.findAll(); }
}
