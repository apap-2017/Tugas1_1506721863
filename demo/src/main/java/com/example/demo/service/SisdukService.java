package com.example.demo.service;

import com.example.demo.model.Keluarga;
import com.example.demo.model.Penduduk;

public interface SisdukService
{
	Penduduk selectPenduduk(String nik);
	Penduduk selectPendudukKota(String nik);
	Keluarga selectKeluarga(int nomor_kk);
	void addPendudukKeluarga();
	void addKeluarga(Keluarga keluarga);
	void updatePenduduk(String nik);
	void updateKeluarga(String nomor_kk);
	void updateKematian(String nik);
}
