package tugas1.sidok.service;

import tugas1.sidok.model.JadwalJagaModel;

import java.util.List;

public interface JadwalJagaService {
    // Method untuk menambah Jadwal
    void addJadwalJaga(JadwalJagaModel jadwalJaga);

    // Method untuk mendapatkan semua jadwal yang tersimpan
    List<JadwalJagaModel> getListJadwalJaga();
}
