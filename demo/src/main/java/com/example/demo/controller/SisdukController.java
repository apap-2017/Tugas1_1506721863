package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
}

