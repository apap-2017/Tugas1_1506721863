package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Kecamatan {
	private int id;
	private String kode_kecamatan;
	private int id_kota;
	private String nama_kecamatan;
}
