package tugas1.sidok.controller;

import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tugas1.sidok.model.DokterModel;
import tugas1.sidok.model.SpesialisasiModel;
import tugas1.sidok.service.DokterService;
import tugas1.sidok.service.PoliService;
import tugas1.sidok.service.SpesialisasiService;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Controller
public class DokterController {
    @Qualifier("dokterServiceImpl")
    @Autowired
    private DokterService dokterService;

//    @Autowired
//    private PoliService poliService;

    @Autowired
    private SpesialisasiService spesialisasiService;

    @RequestMapping(value = "/")
    public String beranda(Model model) {
        System.out.println ("masuk home");
        List<DokterModel> listDokter = dokterService.getListDokter();
        model.addAttribute("listDokter", listDokter);
        System.out.println ("sebelum beranda");
        return "beranda";
    }

    // URL mapping yang digunakan untuk mengakses halaman add dokter
    @RequestMapping(value = "/dokter/tambah", method = RequestMethod.GET)
    public String addDokterFormPage(Model model) {
        System.out.println ("masuk form dokter");
        // Membuat objek DokterModel
        DokterModel newDokter = new DokterModel();
        model.addAttribute("dokter", newDokter);
        // Return view template
        return "form-add-dokter";
    }

    // URL mapping yang digunakan untuk submit form yang telah anda masukkan pada halaman add dokter
    @RequestMapping(value = "/dokter/tambah", method = RequestMethod.POST)
    public String addDokterSubmit(@ModelAttribute DokterModel dokter, Model model) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
        char generatedRandom = chars.charAt(rnd.nextInt(2));
        int tahunLahir = LocalDateTime.now().getYear() + 5 ;
        String tanggalLahir = String.valueOf(dokter.getTanggalLahir().getDate()) + String.valueOf(dokter.getTanggalLahir().getMonth()) + String.valueOf(dokter.getTanggalLahir().getYear());

        String nip = String.valueOf(tahunLahir) + tanggalLahir + dokter.getJenisKelamin() + generatedRandom;
        dokter.setNip(nip);

        if (dokter.getJenisKelamin().equals("1")) dokter.setJenisKelamin("Laki-Laki");
        if (dokter.getJenisKelamin().equals("2")) dokter.setJenisKelamin("Perempuan");
        dokterService.addDokter(dokter);

        System.out.println ("berhasil add dokter");
        System.out.println (dokter.getJenisKelamin());
        System.out.println (dokter.getNip());
        System.out.println (dokter.getNik());
        System.out.println ("berhasil add dokter");
        model.addAttribute("namaDokter", dokter.getNama());
        model.addAttribute("nipDokter", dokter.getNip());
        return "add-dokter";
    }

    // URL mapping view
    @RequestMapping(path = "/dokter", method = RequestMethod.GET)
    public String view(
            // Request Parameter untuk dipass
            @RequestParam(value = "nik") String nik, Model model
    ) {

        // Mengambil objek DokterModel yang dituju
        DokterModel dokter = dokterService.getDokterByNikDokter(nik).get();

//        List<SpesialisasiModel> spesialisList = SpesialisasiService.getListSpesialisasi(dokter.getNik());
//        dokter.setListSpesialisasi(spesialisList);

        // Add model dokter ke "dokter" untuk dirender
        model.addAttribute("dokter", dokter);

        // return view template
        return "view-dokter";
    }

    //API yang digunakan untuk menuju halaman form update dokter
    @RequestMapping(value = "dokter/update/{idDokter}", method = RequestMethod.GET)
    public String changeDokterFormPage(@PathVariable Long idDokter, Model model) {
        //mengambil existing data dokter
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

    @RequestMapping(path = "/dokter/delete/{idDokter}", method = RequestMethod.GET)
    public String deleteDokter(@PathVariable Long idDokter, Model model) {
        // Mengambil objek DokterModel yang dituju
        DokterModel dokter = dokterService.getDokterByIdDokter(idDokter).get();
        dokterService.deleteDokter(dokter);

        //hapus jadwal dokter dipoli BELOOOM
        model.addAttribute("namaDokter", dokter.getNama());
        model.addAttribute("nipDokter", dokter.getNip());
        return "delete-dokter";
    }

}