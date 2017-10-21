package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Keluarga {
	private int id_keluarga;
	private char nomor_kk;
	private String alamat;
	private char rt;
	private char rw;
	private int id_kelurahan;
	private int is_tidak_berlaku;
}
