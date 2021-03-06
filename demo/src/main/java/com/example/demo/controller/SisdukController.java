package com.example.demo.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Kecamatan;
import com.example.demo.model.Keluarga;
import com.example.demo.model.Kelurahan;
import com.example.demo.model.Kota;
import com.example.demo.model.Penduduk;
import com.example.demo.service.SisdukService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SisdukController {
    @Autowired
    SisdukService sisdukDAO;

    @RequestMapping("/")
    public String index ()
    {
        return "index";
    } 
    
    @RequestMapping("/penduduk")
    public String viewPenduduk (Model model, @RequestParam(value = "nik", required = true) String nik)
    {
        Penduduk penduduk = sisdukDAO.selectPenduduk(nik);
        if (penduduk != null) {
        	String[] parts = penduduk.getTanggal_lahir().split("-");
        	String bulan = "";
        	switch(parts[1]) {
        		case "01" :
        			bulan = "Januari";
        			break;
        		case "02" :
        			bulan = "Februari";
        			break;
        		case "03" :
        			bulan = "Maret";
        			break;
        		case "04" :
        			bulan = "April";
        			break;
        		case "05" :
        			bulan = "Mei";
        			break;
        		case "06" :
        			bulan = "Juni";
        			break;
        		case "07" :
        			bulan = "Juli";
        			break;
        		case "08" :
        			bulan = "Agustus";
        			break;
        		case "09" :
        			bulan = "September";
        			break;
        		case "10" :
        			bulan = "Oktober";
        			break;
        		case "11" :
        			bulan = "November";
        			break;
        		case "12" :
        			bulan = "Desember";
        			break;
        	}
        	
        	String ttl = "" + parts[2] + " " + bulan + " " + parts[0];
        	penduduk.setTtl(ttl);
        	
            Keluarga keluarga = sisdukDAO.selectKeluarga(penduduk.getId_keluarga());
            Kelurahan kelurahan = sisdukDAO.selectKelurahan(keluarga.getId_kelurahan());
            Kecamatan kecamatan = sisdukDAO.selectKecamatan(kelurahan.getId_kecamatan());
            Kota kota = sisdukDAO.selectKota(kecamatan.getId_kota());
            model.addAttribute("alamat", keluarga.getAlamat());
        	model.addAttribute("rt", keluarga.getRt());
        	model.addAttribute("rw", keluarga.getRw());
        	model.addAttribute("kelurahan", kelurahan.getNama_kelurahan());
        	model.addAttribute("kecamatan", kecamatan.getNama_kecamatan());
        	model.addAttribute("kota", kota.getNama_kota());
        	model.addAttribute("penduduk", penduduk);
            model.addAttribute("keluarga", keluarga);
        	model.addAttribute("kelurahan", kelurahan);
        	model.addAttribute("kecamatan", kecamatan);
        	model.addAttribute("kota", kota);
        	return "penduduk";
        } else {
            model.addAttribute ("nik", nik);
            return "/error/404";
        }
    }
    
    @RequestMapping("/keluarga")
    public String viewKeluarga (Model model, @RequestParam(value = "nomor_kk", required = true) String nomor_kk)
    {
    	Keluarga keluarga = sisdukDAO.selectKeluargaKK(nomor_kk);
    	if (keluarga != null) {
    		Kelurahan kelurahan = sisdukDAO.selectKelurahan(keluarga.getId_kelurahan());
            Kecamatan kecamatan = sisdukDAO.selectKecamatan(kelurahan.getId_kecamatan());
            Kota kota = sisdukDAO.selectKota(kecamatan.getId_kota());
            model.addAttribute("alamat", keluarga.getAlamat());
        	model.addAttribute("rt", keluarga.getRt());
        	model.addAttribute("rw", keluarga.getRw());
        	model.addAttribute("kelurahan", kelurahan.getNama_kelurahan());
        	model.addAttribute("kecamatan", kecamatan.getNama_kecamatan());
        	model.addAttribute("kota", kota.getNama_kota());
        	model.addAttribute("keluarga", keluarga);
        	model.addAttribute("kelurahan", kelurahan);
        	model.addAttribute("kecamatan", kecamatan);
        	model.addAttribute("kota", kota);
        	return "keluarga";
        } else {
            model.addAttribute ("nomor_kk", nomor_kk);
            return "/error/404";
        }
    }
    
    @RequestMapping("/tambahUbah")
    public String tambahUbah ()
    {
        return "tambahUbah";
    } 

    @RequestMapping("/tambahPenduduk")
    public String tambahPenduduk (Model model)
    {
    	Penduduk penduduk = new Penduduk();
    	model.addAttribute("penduduk", penduduk);
        return "tambahPenduduk";
    }
    
    @RequestMapping(value = "/penduduk/tambah", method = RequestMethod.POST)
    public String addSubmitPenduduk (Penduduk penduduk, Model model) {
    	Keluarga keluarga = sisdukDAO.selectKeluarga(penduduk.getId_keluarga());
        Kelurahan kelurahan = sisdukDAO.selectKelurahan(keluarga.getId_kelurahan());    	
    	Kecamatan kecamatan = sisdukDAO.selectKecamatan(kelurahan.getId_kecamatan());
    	
        String kodekecamatan = "" + kecamatan.getKode_kecamatan().substring(0, 6);
        String[] parts = penduduk.getTanggal_lahir().split("-");
        parts[0] = parts[0].substring(2);
        if(penduduk.getJenis_kelamin() == 1) {
        	parts[2] += 40;
        }
        String ttl = parts[2] + parts[1] + parts[0];
        int urutan;
        
        String nik = kodekecamatan + ttl;
        
        Penduduk other = sisdukDAO.selectPendudukLike(nik);
        if(other != null) {
        	urutan = Integer.parseInt(other.getNik().substring(15)) + 1;
        } else {
        	urutan = 1;
        }
    	
        nik = nik + "000" + urutan;
        penduduk.setNik(nik);

    	sisdukDAO.addPendudukKeluarga(penduduk);
        return "success-add-penduduk";
    }
    
    @RequestMapping("/tambahKeluarga")
    public String tambahKeluarga (Model model)
    {
    	Keluarga keluarga = new Keluarga();
    	List<Kelurahan> kelurahans = sisdukDAO.selectAllKelurahan();
    	model.addAttribute("kelurahans", kelurahans);
    	model.addAttribute("keluarga", keluarga);
        return "tambahKeluarga";
    }
    
    @RequestMapping(value = "/keluarga/tambah", method = RequestMethod.POST)
    public String addSubmitKeluarga (Keluarga keluarga, Model model) {
        Kelurahan kelurahan = sisdukDAO.selectKelurahan(keluarga.getId_kelurahan());    	
    	Kecamatan kecamatan = sisdukDAO.selectKecamatan(kelurahan.getId_kecamatan());
    	
        String kodekecamatan = "" + kecamatan.getKode_kecamatan().substring(0, 6);
        
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.now();
        String tanggalterbitnkk = dateFormat.format(date);
        
        String[] parts = tanggalterbitnkk.split("-");
        parts[0] = parts[0].substring(2);
        tanggalterbitnkk = parts[2] + parts[1] + parts[0];
        
        int urutan;
        
        String nkk = kodekecamatan + tanggalterbitnkk;
        Keluarga other = sisdukDAO.selectKeluargaLike(nkk);
        if(other != null) {
        	urutan = Integer.parseInt(other.getNomor_kk().substring(15)) + 1;
        } else {
        	urutan = 1;
        }
        
        nkk = nkk +"000"+ urutan;
        keluarga.setNomor_kk(nkk);
        sisdukDAO.addKeluarga(keluarga);
        return "success-add-keluarga";
    }
    
    @RequestMapping("/ubahDataPenduduk/{nik}")
    public String ubahDataPenduduk (Model model, @PathVariable(value="nik") String nik)
    {	
        Penduduk penduduk = sisdukDAO.selectPenduduk(nik);
    	model.addAttribute("penduduk", penduduk);
    	return "/ubahDataPenduduk";
    }
    
    @RequestMapping(value = "/penduduk/ubah/", method = RequestMethod.POST)
    public String updateSubmitPenduduk (Penduduk penduduk, Model model) {
        String nowNIK = penduduk.getNik();
        
        Keluarga keluarga = sisdukDAO.selectKeluarga(penduduk.getId_keluarga());
        Kelurahan kelurahan = sisdukDAO.selectKelurahan(keluarga.getId_kelurahan());    	
    	Kecamatan kecamatan = sisdukDAO.selectKecamatan(kelurahan.getId_kecamatan());
    	
        String kodekecamatan = "" + kecamatan.getKode_kecamatan().substring(0, 6);
        String[] parts = penduduk.getTanggal_lahir().split("-");
        parts[0] = parts[0].substring(2);
        if(penduduk.getJenis_kelamin() == 1) {
        	parts[2] += 40;
        }
        String ttl = parts[2] + parts[1] + parts[0];
        String nik = kodekecamatan + ttl;
        int urutan;
        
        Penduduk other = sisdukDAO.selectPendudukLike(nik);     
        if(nik == penduduk.getNik().substring(0, 12)) {
        	urutan = 1;
        	penduduk.setNik(nik + "000" + urutan);
        	sisdukDAO.updatePenduduk(penduduk);
        } else {
        	if(other == null) {
        		urutan = 1;
        		penduduk.setNik(nik + "000" + urutan);
        		sisdukDAO.updatePenduduk(penduduk);
        	} else {
        		urutan = Integer.parseInt(other.getNik().substring(12));
        		penduduk.setNik(nik + "000" + urutan);
        		sisdukDAO.updatePenduduk(penduduk);
        	}
        }
        
    	return "/success-update-penduduk";
    }
}

