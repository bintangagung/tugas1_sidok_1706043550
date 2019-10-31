package tugas1.sidok.controller;

import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tugas1.sidok.model.*;
import tugas1.sidok.repository.JadwalJagaDb;
import tugas1.sidok.repository.SpesialisasiDokterDb;
import tugas1.sidok.service.DokterService;
import tugas1.sidok.service.PoliService;
import tugas1.sidok.service.SpesialisasiService;

import java.nio.charset.Charset;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Controller
public class DokterController {
    @Qualifier("dokterServiceImpl")
    @Autowired
    private DokterService dokterService;

    @Autowired
    private PoliService poliService;

    @Autowired
    private SpesialisasiService spesialisasiService;

    @Autowired
    private SpesialisasiDokterDb spesialisasiDokterDb;

    @Autowired
    private JadwalJagaDb jadwalJagaDb;

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
        SpesialisasiDokterModel spesialisasiDokter = new SpesialisasiDokterModel();
        List<SpesialisasiDokterModel> listSpesialisasiDokter = new ArrayList<>();
        listSpesialisasiDokter.add(spesialisasiDokter);
        newDokter.setListSpesialisasi(listSpesialisasiDokter);
        List<SpesialisasiModel> listSpesialisasi = spesialisasiService.getAll();

        model.addAttribute("listSpesialisasi",listSpesialisasi);
        model.addAttribute("dokter", newDokter);
        // Return view template
        return "form-add-dokter";
    }

    @RequestMapping(value = "/dokter/tambah", method = RequestMethod.POST, params = "tambahSpesialisasi")
    public String addRowSpesialisasiDokter(@ModelAttribute DokterModel dokter,
                                           BindingResult bindingResult, Model model) {
        if(dokter.getListSpesialisasi() == null) {
            dokter.setListSpesialisasi(new ArrayList<SpesialisasiDokterModel>());
        }
        dokter.getListSpesialisasi().add(new SpesialisasiDokterModel());
        List<SpesialisasiModel> listSpesialisasi = spesialisasiService.getAll();

        model.addAttribute("listSpesialisasi",listSpesialisasi);
        model.addAttribute("dokter", dokter);
        // Return view template
        return "form-add-dokter";
    }

    // URL mapping yang digunakan untuk submit form yang telah anda masukkan pada halaman add dokter
    @RequestMapping(value = "/dokter/tambah", method = RequestMethod.POST, params = "submitDokter")
    public String addDokterSubmit(@ModelAttribute DokterModel dokter, Model model) {
        DokterModel newDokter = new DokterModel();
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
        char generatedRandom = chars.charAt(rnd.nextInt(2));
        int tahunLahir = LocalDateTime.now().getYear() + 5 ;
        Format date = new SimpleDateFormat("ddMMyy");
        String tanggalLahir = date.format(dokter.getTanggalLahir());
        String nip = String.valueOf(tahunLahir) + tanggalLahir + dokter.getJenisKelamin() + generatedRandom;

        newDokter.setNip(nip);
        newDokter.setNama(dokter.getNama());
        newDokter.setJenisKelamin(dokter.getJenisKelamin());
        newDokter.setNik(dokter.getNik());
        newDokter.setTanggalLahir(dokter.getTanggalLahir());
        newDokter.setTempatLahir(dokter.getTempatLahir());

        if (dokter.getJenisKelamin().equals("1")) newDokter.setJenisKelamin("Laki-Laki");
        if (dokter.getJenisKelamin().equals("2")) newDokter.setJenisKelamin("Perempuan");

        List<SpesialisasiDokterModel> spesialisasiDokterList = dokter.getListSpesialisasi();
        dokter.setListSpesialisasi(null);
        dokterService.addDokter(newDokter);
        System.out.println ("berhasil add dokter");

        for (SpesialisasiDokterModel spesialisasiDokter: spesialisasiDokterList) {
            SpesialisasiDokterModel baru = new SpesialisasiDokterModel();
            baru.setDokter(newDokter);
            baru.setSpesialisasi(spesialisasiService.getByIdSpesialisasi(spesialisasiDokter.getSpesialisasi().getIdSpesialisasi()).get());
            spesialisasiDokterDb.save(baru);
        }

        model.addAttribute("namaDokter", newDokter.getNama());
        model.addAttribute("nipDokter", newDokter.getNip());
        return "add-dokter";
    }

    // URL mapping view
    @RequestMapping(path = "/dokter", method = RequestMethod.GET, params = "nikDokter")
    public String view(
            // Request Parameter untuk dipass
            @RequestParam(value = "nikDokter") String nik, Model model
    ) {
        // Mengambil objek DokterModel yang dituju
        DokterModel dokter = dokterService.getDokterByNikDokter(nik).get();

        List<SpesialisasiDokterModel> spesialisList = spesialisasiDokterDb.findAllByDokterIdDokter(dokter.getIdDokter());
        dokter.setListSpesialisasi(spesialisList);

        // Add model dokter ke "dokter" untuk dirender
        model.addAttribute("dokter", dokter);

        // return view template
        return "view-dokter";
    }

    //API yang digunakan untuk menuju halaman form update dokter
    @RequestMapping(value = "dokter/update/{idDokter}", method = RequestMethod.GET)
    public String changeDokterFormPage(@PathVariable Long idDokter, Model model) {
        //mengambil existing data dokter
        System.out.println ("berhasil masuk update");
        DokterModel existingDokter = dokterService.getDokterByIdDokter(idDokter).get();
        model.addAttribute("dokter", existingDokter);
        System.out.println ("akan render update");
        return "form-update-dokter";
    }

    //API yang digunakan untuk submit form update dokter
    @RequestMapping(value = "dokter/update/{idDokter}", method = RequestMethod.POST)
    public String changeDokterFormSubmit(@PathVariable Long idDokter, @ModelAttribute DokterModel dokter, Model model) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
        char generatedRandom = chars.charAt(rnd.nextInt(2));
        int tahunLahir = LocalDateTime.now().getYear() + 5 ;
        Format date = new SimpleDateFormat("ddMMyy");
        String tanggalLahir = date.format(dokter.getTanggalLahir());
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


    @RequestMapping(value = "/cari", method = RequestMethod.GET)
    public String cariDokterFormPage(Model model) {
        List<PoliModel> listPoli = poliService.getListPoli();
        List<SpesialisasiModel> listSpesialisasi = spesialisasiService.getAll();

        model.addAttribute("listSpesialisasi",listSpesialisasi);
        model.addAttribute("listPoli",listPoli);

        return "form-cari-dokter";
    }

    @RequestMapping(path = "/cari", method = RequestMethod.GET, params = {"submit"})
    public String cariDokterFormSubmit(@RequestParam(value = "idSpesialisasi",required = false) Long idSpesialisasi,
                             @RequestParam(value = "idPoli",required = false) Long idPoli, Model model
    ) {
        List<PoliModel> poli = poliService.getListPoli();
        List<SpesialisasiModel> spesialisasi = spesialisasiService.getAll();
        List<SpesialisasiDokterModel> listSpesialisasi = spesialisasiDokterDb.findAllBySpesialisasiIdSpesialisasi(idSpesialisasi);
        List<JadwalJagaModel> listPoli = jadwalJagaDb.findAllByPoliIdPoli(idPoli);
        List<DokterModel> spesialisasiList = new ArrayList<>();
        for (SpesialisasiDokterModel spesialisasiDokter: listSpesialisasi) {
            spesialisasiList.add(spesialisasiDokter.getDokter());
        }
        List<DokterModel> poliList = new ArrayList<>();
        for (JadwalJagaModel jadwalJaga: listPoli) {
            poliList.add(jadwalJaga.getDokter());
        }
        List<DokterModel> listDokter = new ArrayList<>();
        for (DokterModel spesialisasiDokter : spesialisasiList) {
            if (poliList.contains(spesialisasiDokter)) listDokter.add(spesialisasiDokter);
        }

        System.out.println (spesialisasiList);
        System.out.println (poliList);
        System.out.println (listDokter);
        model.addAttribute("listSpesialisasi", spesialisasi);
        model.addAttribute("listPoli", poli);
        model.addAttribute("listDokter", listDokter);
        return "form-cari-dokter";
    }

    @RequestMapping(path = "/cari/tugas-terbanyak", method = RequestMethod.GET)
    public String cariDokterTersibuk(@RequestParam(value = "idPoli",required = false) Long idPoli, Model model) {
        List<JadwalJagaModel> listJadwal;
        List<PoliModel> listPoli = poliService.getListPoli();
        List<DokterModel> listDokter = new ArrayList<>();
        HashMap<DokterModel,Integer> dokter = new HashMap();
        if(idPoli != null){
            listJadwal = jadwalJagaDb.findAllByPoliIdPoli(idPoli);
            for (JadwalJagaModel jadwalJaga: listJadwal) {
                if(listDokter.contains(jadwalJaga.getDokter()) == false){
                    listDokter.add(jadwalJaga.getDokter());
                    dokter.put(jadwalJaga.getDokter(),1);
                } else
                    dokter.put(jadwalJaga.getDokter(),(Integer)dokter.get(jadwalJaga.getDokter())+1);
            }
            Integer terbanyak = 0;
            for (DokterModel dokters: listDokter) {
                if (dokter.get(dokters)>terbanyak){
                    terbanyak = dokter.get(dokters);
                }
            }
            List<DokterModel> listDokterTerbanyak = new ArrayList<>();
            for(DokterModel dokters: listDokter){
                if(dokter.get(dokters)==terbanyak){
                    listDokterTerbanyak.add(dokters);
                }
            }
            model.addAttribute("listDokter",listDokterTerbanyak);
        }
        else{
            model.addAttribute("listDokter",listDokter);
        }
        model.addAttribute("listPoli",listPoli);
        return "cari-dokter-tersibuk";
    }

    @RequestMapping(value = "/jumlah-dokter", method = RequestMethod.GET)
    public String jumlahDokterUntukSetiapSpesialisasiPage(Model model) {
        List<SpesialisasiModel> listSpesialisasi = spesialisasiService.getAll();
        List<Integer> jumlahDokter = new ArrayList<>();

        for (SpesialisasiModel spesialisasi: listSpesialisasi) {
            jumlahDokter.add(spesialisasi.getListDokter().size());
        }
        System.out.println (listSpesialisasi);
        System.out.println (jumlahDokter);
        model.addAttribute("listSpesialisasi", listSpesialisasi);
        model.addAttribute("jumlahDokter", jumlahDokter);

        return "jumlah-dokter-di-spesialisasi";
    }

}