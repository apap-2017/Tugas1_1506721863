package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Keluarga {
	private int id;
	private char nomor_kk;
	private String alamat;
	private String rt;
	private String rw;
	private int id_kelurahan;
	private int is_tidak_berlaku;
}
