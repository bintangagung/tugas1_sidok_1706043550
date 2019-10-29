package tugas1.sidok.service;

import tugas1.sidok.model.JadwalModel;

import java.util.List;

public interface JadwalService {
    // Method untuk menambah Jadwal
    void addJadwal(JadwalModel jadwal);

    // Method untuk mendapatkan semua jadwal yang tersimpan
    List<JadwalModel> getListJadwal();
}
