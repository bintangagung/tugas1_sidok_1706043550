package tugas1.sidok.service;

import tugas1.sidok.model.DokterModel;
import tugas1.sidok.model.PoliModel;

import java.util.List;
import java.util.Optional;

public interface PoliService {
    // Method untuk menambah Poli
    void addPoli(PoliModel poli);

    // Method untuk mendapatkan semua data Poli yang tersimpan
    List<PoliModel> getListPoli();

    // Method untuk mendapatkan data sebuah Poli berdasarkan idPoli
    Optional<PoliModel> getPoliByIdPoli(Long idPoli);

    PoliModel changePoli(PoliModel poliModel);

    void deletePoli(PoliModel Poli);

}
