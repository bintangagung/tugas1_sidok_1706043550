package tugas1.sidok.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tugas1.sidok.model.*;
import tugas1.sidok.repository.JadwalJagaDb;
import tugas1.sidok.service.DokterService;
import tugas1.sidok.service.JadwalJagaService;
import tugas1.sidok.service.JadwalJagaServiceImpl;
import tugas1.sidok.service.PoliService;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class JadwalJagaController {

    @Autowired
    private DokterService dokterService;

    @Autowired
    private PoliService poliService;

    @Autowired
    private JadwalJagaDb jadwalJagaDb;

    @RequestMapping(value = "/jadwal/tambah/{nip}", method = RequestMethod.GET)
    public String addJadwalDokterFormPage(@PathVariable String nip, Model model) {
        JadwalJagaModel jadwalJaga = new JadwalJagaModel();
        DokterModel dokter = dokterService.getDokterByNipDokter(nip).get();
        List<PoliModel> listPoli = poliService.getListPoli();

        model.addAttribute("listPoli",listPoli);
        model.addAttribute("dokter", dokter);
        model.addAttribute("jadwalJaga",jadwalJaga);
        // Return view template
        return "form-add-jadwal";
    }

    @RequestMapping(value = "/jadwal/tambah/{nip}", method = RequestMethod.POST)
    public String addJadwalFormSubmit(@PathVariable String nip, @ModelAttribute JadwalJagaModel newJadwal, Model model) {
        DokterModel dokter = dokterService.getDokterByNipDokter(nip).get();
        newJadwal.setDokter(dokter);
        newJadwal.setPoli(poliService.getPoliByIdPoli(newJadwal.getPoli().getIdPoli()).get());
        jadwalJagaDb.save(newJadwal);
        JadwalJagaModel jadwalJaga = new JadwalJagaModel();
        List<PoliModel> listPoli = poliService.getListPoli();
        model.addAttribute("dokter",dokter);
        model.addAttribute("listPoli",listPoli);
        model.addAttribute("jadwalJaga",jadwalJaga);
        return "form-add-jadwal";
    }

}
