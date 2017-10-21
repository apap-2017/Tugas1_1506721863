package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
            model.addAttribute ("penduduk", penduduk);
            return "penduduk";
        } else {
            model.addAttribute ("nik", nik);
            return "/error/404";
        }
    }
}

