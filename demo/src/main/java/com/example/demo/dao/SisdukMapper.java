package com.example.demo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.model.Keluarga;
import com.example.demo.model.Penduduk;

@Mapper
public interface SisdukMapper
{
	@Select("select nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, is_wni, id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, is_wafat from penduduk where nik = #{nik}")
	@Results(value = {
			@Result(property="nik", column="nik"),
			@Result(property="nama", column="nama"),
			@Result(property="tempat_lahir", column="tempat_lahir"),
			@Result(property="tanggal_lahir", column="tanggal_lahir"),
			@Result(property="jenis_kelamin", column="jenis_kelamin"),
			@Result(property="is_wni", column="is_wni"),
			@Result(property="id_keluarga", column="id_keluarga"),
			@Result(property="agama", column="agama"),
			@Result(property="pekerjaan", column="pekerjaan"),
			@Result(property="status_perkawinan", column="status_perkawinan"),
			@Result(property="status_dalam_keluarga", column="status_dalam_keluarga"),
			@Result(property="golongan_darah", column="golongan_darah"),
			@Result(property="is_wafat", column="is_wafat")
			//@Result(property="keluarga", column="keluarga",
	    		//	javaType = List.class,
	    			//many=@Many(select="selectKeluarga(id_keluarga)"))
	})
	Penduduk selectPenduduk (@Param("nik") String nik);
	
	@Select("select nik, nama, tempat_lahir, jenis_kelamin, is_wni, id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, is_wafat from penduduk where nik = #{nik}")
	@Results(value = {
			@Result(property="nik", column="nik"),
			@Result(property="nama", column="nama"),
			@Result(property="tempat_lahir", column="tempat_lahir"),
			@Result(property="jenis_kelamin", column="jenis_kelamin"),
			@Result(property="is_wni", column="is_wni"),
			@Result(property="id_keluarga", column="id_keluarga"),
			@Result(property="agama", column="agama"),
			@Result(property="pekerjaan", column="pekerjaan"),
			@Result(property="status_perkawinan", column="status_perkawinan"),
			@Result(property="status_dalam_keluarga", column="status_dalam_keluarga"),
			@Result(property="golongan_darah", column="golongan_darah"),
			@Result(property="is_wafat", column="is_wafat")
	})
	Penduduk selectPendudukKota (@Param("nik") String nik);
	
	@Select("select nomor_kk, alamat, rt, rw, id_kelurahan, is_tidak_berlaku from keluarga where nomor_kk = #{nomor_kk}")
	@Results(value = {
			@Result(property="nomor_kk", column="nomor_kk"),
			@Result(property="alamat", column="alamat"),
			@Result(property="rt", column="rt"),
			@Result(property="rw", column="rw"),
			@Result(property="id_kelurahan", column="id_kelurahan"),
			@Result(property="is_tidak_berlaku", column="is_tidak_berlaku")
	}) 
	Keluarga selectKeluarga (@Param("nomor_kk") int nomor_kk);
	
	@Insert("INSERT INTO keluarga (alamat, rt, rw, id_kelurahan,) VALUES (#{alamat}, #{rt}, #{rw}, #{id_kelurahan})")
	void addKeluarga (Keluarga keluarga);
	
	@Update("UPDATE penduduk SET nama = #{nama}, tempat_lahir = #{tempat_lahir}, jenis_kelamin = #{jenis_kelamin}, is_wni = #{is_wni}, id_keluarga = #{id_keluarga}, agama = #{agama}, pekerjaan = #{pekerjaan}, status_perkawinan = #{status_perkawinan}, status_dalam_keluarga = #{status_dalam_keluarga}, golongan_darah = #{golongan_darah} FROM penduduk WHERE nik = #{nik}")
	void updatePenduduk(@Param("nik") String nik);
	
	@Update("UPDATE keluarga SET alamat = #{alamat}, rt = #{rt}, rw = #{rw}, id_kelurahan = #{id_kelurahan}, is_tidak_berlaku = #{is_tidak_berlaku} FROM keluarga WHERE nomor_kk = #{nomor_kk}")
	void updateKeluarga(@Param("nomor_kk") String nomor_kk);
	
	@Update("UPDATE penduduk SET is_wafat = #{is_wafat} FROM penduduk WHERE nik = #{nik}")
	void updateKematian(@Param("nik") String nik);
}