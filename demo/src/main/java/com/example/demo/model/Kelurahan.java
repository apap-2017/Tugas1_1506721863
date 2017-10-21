package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Kelurahan {
	private int id_kelurahan;
	private String kode_kelurahan;
	private int id_kecamatan;
	private String nama_kelurahan;
	private char kode_pos;
}
