package com.example.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.SisdukMapper;
import com.example.demo.model.Kecamatan;
import com.example.demo.model.Keluarga;
import com.example.demo.model.Kelurahan;
import com.example.demo.model.Kota;
import com.example.demo.model.Penduduk;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

public class SisdukServiceDatabase implements SisdukService {
	
    @Autowired
    private SisdukMapper sisdukMapper;

	@Override
	public Penduduk selectPenduduk(String nik) {
		log.info("select penduduk with nik {}", nik);
		return sisdukMapper.selectPenduduk(nik);
	}

	@Override
	public Penduduk selectPendudukKota(String nik) {
		log.info("selectPendudukKota)");
		return sisdukMapper.selectPendudukKota(nik);
	}

	@Override
	public Keluarga selectKeluarga(int id_keluarga) {
		log.info("select keluarga with id {}", id_keluarga);
		return sisdukMapper.selectKeluarga(id_keluarga);
	}
	
	@Override
	public Keluarga selectKeluargaKK(String nomor_kk) {
		log.info("select keluarga with nomoor kk {}", nomor_kk);
		return sisdukMapper.selectKeluargaKK(nomor_kk);
	}
	
	@Override
	public List<Penduduk> selectPendudukKeluarga(int id) {
		log.info("select penduduk with id {}", id);
		return sisdukMapper.selectPendudukKeluarga(id);
	}
	
	@Override
	public void addPendudukKeluarga(Penduduk penduduk) {
		log.info("tambah");
		sisdukMapper.addPendudukKeluarga();
	}

	@Override
	public void addKeluarga(Keluarga keluarga) {
		sisdukMapper.addKeluarga(keluarga);
	}

	@Override
	public void updatePenduduk(String nik) {
		sisdukMapper.updatePenduduk(nik);
	}

	@Override
	public void updateKeluarga(String nomor_kk) {
		sisdukMapper.updateKeluarga(nomor_kk);
	}


	@Override
	public void updateKematian(String nik) {
		sisdukMapper.updateKematian(nik);
	}
	
	@Override
	public Kelurahan selectKelurahan(int id_kelurahan) {
		return sisdukMapper.selectKelurahan(id_kelurahan);
	}
	
	@Override
	public Kecamatan selectKecamatan(int id_kecamatan) {
		return sisdukMapper.selectKecamatan(id_kecamatan);
	}

	@Override
	public Kota selectKota(int id_kota) {
		return sisdukMapper.selectKota(id_kota);
	}

}
