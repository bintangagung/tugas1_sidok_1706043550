package tugas1.sidok.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import tugas1.sidok.model.DokterModel;
import tugas1.sidok.model.PoliModel;
import tugas1.sidok.service.DokterService;
import tugas1.sidok.service.PoliService;
import tugas1.sidok.service.SpesialisasiService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Controller
public class PoliController {
    @Qualifier("poliServiceImpl")
    @Autowired
    private DokterService dokterService;

    @Autowired
    private PoliService poliService;

    @Autowired
    private SpesialisasiService spesialisasiService;

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

    // URL mapping view
    @RequestMapping(path = "/poli/view", method = RequestMethod.GET)
    public String view(
            // Request Parameter untuk dipass
            @RequestParam(value = "idPoli") Long idPoli, Model model
    ) {

        // Mengambil objek PoliModel yang dituju
        PoliModel poli = poliService.getPoliByIdPoli(idPoli).get();

//        List<PoliModel> poliList = PoliService.getListPoli(poli.getIdPoli());
//        poli.setListPoli(poliList);

        // Add model poli ke "poli" untuk dirender
        model.addAttribute("poli", poli);

        // return view template
        return "view-poli";
    }

}
