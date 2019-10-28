package tugas1.sidok.service;

import tugas1.sidok.model.DokterModel;

import java.util.List;
import java.util.Optional;

public interface DokterService {
    // Method untuk menambah Dokter
    void addDokter(DokterModel dokter);

    // Method untuk mendapatkan semua data Dokter yang tersimpan
    List<DokterModel> getListDokter();

    // Method untuk mendapatkan data sebuah Dokter berdasarkan idDokter
    Optional<DokterModel> getDokterByIdDokter(Long idDokter);

    // Method untuk mendapatkan data sebuah Dokter berdasarkan idDokter
    Optional<DokterModel> getDokterByNikDokter(String nik);

    DokterModel changeDokter(DokterModel dokterModel);

    void deleteDokter(DokterModel Dokter);

    //viewAll
    List<DokterModel> getDokterListOrderByNama();
}
