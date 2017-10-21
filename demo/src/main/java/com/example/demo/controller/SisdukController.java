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
    public String viewPath (Model model, @RequestParam(value = "nik", required = true) String nik)
    {
        Penduduk penduduk = sisdukDAO.selectPenduduk(nik);
        log.info ("select penduduk with nik {}", nik);
        if (penduduk != null) {
        	log.info ("nik {}", penduduk.getNik());
            //Keluarga keluarga = sisdukDAO.selectKeluarga(penduduk.getId_keluarga());
            //Kelurahan kelurahan = sisdukDAO.selectKelurahan(keluarga.getId_kelurahan());
            /*Kecamatan kecamatan = sisdukDAO.selectKecamatan(kelurahan.getId_kecamatan());
            Kota kota = sisdukDAO.selectKota(kecamatan.getId_kota());
            model.addAttribute("alamat", keluarga.getAlamat());
        	model.addAttribute("rt", keluarga.getRt());
        	model.addAttribute("rw", keluarga.getRw());
        	model.addAttribute("kelurahan", kelurahan.getNama_kelurahan());
        	model.addAttribute("kecamatan", kecamatan.getNama_kecamatan());
        	model.addAttribute("kota", kota.getNama_kota());*/
        	model.addAttribute ("penduduk", penduduk);
            return "penduduk";
        } else {
            model.addAttribute ("nik", nik);
            return "/error/404";
        }
        
    }
}

