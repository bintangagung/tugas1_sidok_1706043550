package tugas1.sidok.controller;

import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tugas1.sidok.model.DokterModel;
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
//
//    @Autowired
//    private SpesialisasiService spesialisasiService;

    @RequestMapping(value = "/")
    public String beranda(Model model) {
        System.out.println ("masuk home");
        List<DokterModel> listDokter = dokterService.getListDokter();;
        model.addAttribute("listDokter", listDokter);
        System.out.println ("sebelum beranda");
        return "beranda";
    }

    // URL mapping yang digunakan untuk mengakses halaman add dokter
    @RequestMapping(value = "/dokter/add", method = RequestMethod.GET)
    public String addDokterFormPage(Model model) {
        System.out.println ("masuk form dokter");
        // Membuat objek DokterModel
        DokterModel newDokter = new DokterModel();
        model.addAttribute("dokter", newDokter);
        // Return view template
        return "form-add-dokter";
    }

    // URL mapping yang digunakan untuk submit form yang telah anda masukkan pada halaman add dokter
    @RequestMapping(value = "/dokter/add", method = RequestMethod.POST)
    public String addDokterSubmit(@ModelAttribute DokterModel dokter, Model model) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
        char generatedRandom = chars.charAt(rnd.nextInt(2));
        int tahunLahir = LocalDateTime.now().getYear() + 5 ;
        String tanggalLahir = String.valueOf(dokter.getTanggalLahir().getDate()) + String.valueOf(dokter.getTanggalLahir().getMonth()) + String.valueOf(dokter.getTanggalLahir().getYear());
        String nip = String.valueOf(tahunLahir) + tanggalLahir + dokter.getJenisKelamin() + generatedRandom;
        dokter.setNip(nip);

        dokterService.addDokter(dokter);

        System.out.println ("berhasil add dokter");
        model.addAttribute("namaDokter", dokter.getNama());
        model.addAttribute("nipDokter", dokter.getNip());
        return "add-dokter";
    }

//    //API yang digunakan untuk menuju halaman form change restoran
//    @RequestMapping(value = "restoran/change/{idRestoran}", method = RequestMethod.GET)
//    public String changeRestoranFormPage(@PathVariable Long idRestoran, Model model) {
//        //mengambil existing data restoran
//        RestoranModel existingRestoran = restoranService.getRestoranByIdRestoran(idRestoran).get();
//        model.addAttribute("restoran", existingRestoran);
//        model.addAttribute("navbarTitle", "Change Restoran");
//
//        return "form-change-restoran";
//    }

//    //API yang digunakan untuk submit form change restoran
//    @RequestMapping(value = "restoran/change/{idRestoran}", method = RequestMethod.POST)
//    public String changeRestoranFormSubmit(@PathVariable Long idRestoran, @ModelAttribute RestoranModel restoran, Model model) {
//        RestoranModel newRestoranData = restoranService.changeRestoran(restoran);
//        model.addAttribute("restoran", newRestoranData);
//
//        return "change-restoran";
//    }

    //tampilan viewAll
    @RequestMapping(path = "/restoran/view-all")
    public String viewAll(Model model) {
        List<DokterModel> dokterList = dokterService.getListDokter();
        List<DokterModel> dokterListAlphabet = dokterService.getDokterListOrderByNama();

        model.addAttribute("dokterList", dokterList);
        model.addAttribute("dokterListAlphabet", dokterListAlphabet);
        model.addAttribute("navbarTitle", "View All Dokter");

        return "view-all-dokter";
    }

//    //delete restoran
//    @RequestMapping(path = "/restoran/delete/{idRestoran}", method = RequestMethod.GET)
//    public String deleteRestoran(@PathVariable Long idRestoran, Model model) {
//        // Mengambil objek RestoranModel yang dituju
//        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran).get();
//        if (restoran.getListMenu().isEmpty()) {
//            restoranService.deleteRestoran(restoran);
//            model.addAttribute("restoran", restoran);
//            model.addAttribute("navbarTitle", "Delete Restoran");
//            return "delete-restoran";
//        }
//        else {
//            model.addAttribute("restoran", restoran);
//            model.addAttribute("navbarTitle", "Delete Restoran Gagal");
//            return "delete-restoran-gagal";
//        }
//    }
}