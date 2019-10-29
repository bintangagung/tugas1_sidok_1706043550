package tugas1.sidok.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tugas1.sidok.model.DokterModel;
import tugas1.sidok.service.DokterService;
import tugas1.sidok.service.JadwalJagaService;

import java.time.LocalDateTime;
import java.util.Random;

@Controller
public class JadwalJagaController {

    @Autowired
    private JadwalJagaService jadwalJagaService;

    @RequestMapping(value = "jadwal/tambah/{nip}", method = RequestMethod.GET)
    public String addJadwalDokterFormPage(@PathVariable String nip, Model model) {
        DokterModel existingDokter = dokterService.getDokterByIdDokter(idDokter).get();
        model.addAttribute("dokter", existingDokter);

        return "form-update-dokter";
    }


    //API yang digunakan untuk submit form update dokter
    @RequestMapping(value = "dokter/update/{idDokter}", method = RequestMethod.POST)
    public String changeDokterFormSubmit(@PathVariable Long idDokter, @ModelAttribute DokterModel dokter, Model model) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
        char generatedRandom = chars.charAt(rnd.nextInt(2));
        int tahunLahir = LocalDateTime.now().getYear() + 5 ;
        String tanggalLahir = String.valueOf(dokter.getTanggalLahir().getDate()) + String.valueOf(dokter.getTanggalLahir().getMonth()) + String.valueOf(dokter.getTanggalLahir().getYear());
        String nip = String.valueOf(tahunLahir) + tanggalLahir + dokter.getJenisKelamin() + generatedRandom;
        dokter.setNip(nip);
        if (dokter.getJenisKelamin().equals("1")) dokter.setJenisKelamin("Laki-Laki");
        if (dokter.getJenisKelamin().equals("2")) dokter.setJenisKelamin("Perempuan");
        DokterModel newDokterData = dokterService.changeDokter(dokter);

        model.addAttribute("namaDokter", newDokterData.getNama());
        model.addAttribute("nipDokter", newDokterData.getNip());
        return "update-dokter";
    }

}
