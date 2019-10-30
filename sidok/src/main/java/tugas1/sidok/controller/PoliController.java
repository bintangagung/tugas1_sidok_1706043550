package tugas1.sidok.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tugas1.sidok.model.DokterModel;
import tugas1.sidok.model.JadwalJagaModel;
import tugas1.sidok.model.PoliModel;
import tugas1.sidok.model.SpesialisasiDokterModel;
import tugas1.sidok.repository.JadwalJagaDb;
import tugas1.sidok.service.DokterService;
import tugas1.sidok.service.JadwalJagaService;
import tugas1.sidok.service.PoliService;
import tugas1.sidok.service.SpesialisasiService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Controller
public class PoliController {
    @Qualifier("poliServiceImpl")

    @Autowired
    private PoliService poliService;

    @Autowired
    private DokterService dokterService;

    @Autowired
    private SpesialisasiService spesialisasiService;

    @Autowired
    private JadwalJagaService jadwalService;

//    @Autowired
//    private JadwalJagaDb jadwalJagaDb;

    @RequestMapping(value = "/poli")
    public String poli(Model model) {
        List<PoliModel> listPoli = poliService.getListPoli();
        model.addAttribute("listPoli", listPoli);

        return "list-poli";
    }

    // URL mapping yang digunakan untuk mengakses halaman add poli
    @RequestMapping(value = "/poli/tambah", method = RequestMethod.GET)
    public String addPoliFormPage(Model model) {
        // Membuat objek PoliModel
        PoliModel newPoli = new PoliModel();
        model.addAttribute("poli", newPoli);
        // Return view template
        return "form-add-poli";
    }

    // URL mapping yang digunakan untuk submit form yang telah anda masukkan pada halaman add poli
    @RequestMapping(value = "/poli/tambah", method = RequestMethod.POST)
    public String addPoliSubmit(@ModelAttribute PoliModel poli, Model model) {
        poliService.addPoli(poli);
        model.addAttribute("namaPoli", poli.getNama());
        model.addAttribute("lokasiPoli", poli.getLokasi());
        return "add-poli";
    }

    //API yang digunakan untuk menuju halaman form update poli
    @RequestMapping(value = "poli/update/{idPoli}", method = RequestMethod.GET)
    public String changePoliFormPage(@PathVariable Long idPoli, Model model) {
        //mengambil existing data poli
        System.out.println ("berhasil masuk update");
        PoliModel existingPoli = poliService.getPoliByIdPoli(idPoli).get();
        model.addAttribute("poli", existingPoli);
        System.out.println ("akan render update");
        return "form-update-poli";
    }

    //API yang digunakan untuk submit form update poli
    @RequestMapping(value = "poli/update/{idPoli}", method = RequestMethod.POST)
    public String changePoliFormSubmit(@PathVariable Long idPoli, @ModelAttribute PoliModel poli, Model model) {
        PoliModel newPoliData = poliService.changePoli(poli);

        model.addAttribute("namaPoli", newPoliData.getNama());
        model.addAttribute("lokasiPoli", newPoliData.getLokasi());
        return "update-poli";
    }

    @RequestMapping(path = "/poli/delete/{idPoli}", method = RequestMethod.GET)
    public String deletePoli(@PathVariable Long idPoli, Model model) {
        // Mengambil objek PoliModel yang dituju
        PoliModel poli = poliService.getPoliByIdPoli(idPoli).get();
        poliService.deletePoli(poli);

        model.addAttribute("namaPoli", poli.getNama());
        model.addAttribute("lokasiPoli", poli.getLokasi());
        return "delete-poli";
    }

    @RequestMapping(path = "/poli/dokter", method = RequestMethod.GET, params = "idPoli")
    public String view(
            // Request Parameter untuk dipass
            @RequestParam(value = "idPoli") Long idPoli, Model model
    ) {
        // Mengambil objek DokterModel yang dituju
        PoliModel poli = poliService.getPoliByIdPoli(idPoli).get();

        List<JadwalJagaModel> dokterList = poli.getListDokter();

        model.addAttribute("poli", poli);
        model.addAttribute("dokterList", dokterList);

        // return view template
        return "view-poli";
    }


}
