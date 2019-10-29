package tugas1.sidok.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tugas1.sidok.model.JadwalJagaModel;
import tugas1.sidok.repository.JadwalJagaDb;

import java.util.List;

@Service
@Transactional
public class JadwalServiceImpl implements JadwalService {
    @Autowired
    private JadwalJagaDb jadwalJagaDb;

    @Override
    public void addJadwalJaga(JadwalJagaModel jadwalJaga) { jadwalJagaDb.save(jadwalJaga); }

    @Override
    public List<JadwalJagaModel> getListJadwalJaga() { return jadwalJagaDb.findAll(); }
}
