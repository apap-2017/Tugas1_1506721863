package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Kecamatan;
import com.example.demo.model.Keluarga;
import com.example.demo.model.Kelurahan;
import com.example.demo.model.Kota;
import com.example.demo.model.Penduduk;

public interface SisdukService
{
	Penduduk selectPenduduk(String nik);
	Penduduk selectPendudukKota(String nik);
	Keluarga selectKeluarga(int id_keluarga);
	Keluarga selectKeluargaKK(String nomor_kk);
	List <Penduduk> selectPendudukKeluarga(int id_keluarga);
	void addPendudukKeluarga();
	void addKeluarga(Keluarga keluarga);
	void updatePenduduk(String nik);
	void updateKeluarga(String nomor_kk);
	void updateKematian(String nik);
	Kelurahan selectKelurahan(int id_kelurahan);
	Kecamatan selectKecamatan(int id_kecamatan);
	Kota selectKota(int id_kota);
}
