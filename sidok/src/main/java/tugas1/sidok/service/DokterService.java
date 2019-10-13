package tugas1.sidok.service;

import tugas1.sidok.model.DokterModel;

import java.util.List;
import java.util.Optional;

public interface DokterService {
    // Method untuk menambah Dokter
    void addDokter(DokterModel dokter);

    // Method untuk mendapatkan semua data Dokter yang tersimpan
    List<DokterModel> getDokterList();

    // Method untuk mendapatkan data sebuah Dokter berdasarkan idDokter
    Optional<DokterModel> getDokterByIdDokter(Long idDokter);

    DokterModel changeDokter(DokterModel dokterModel);

    //viewAll
    List<DokterModel> getDokterListOrderByNama();

    //method delete restoran
    void deleteDokter(DokterModel Dokter);
}
