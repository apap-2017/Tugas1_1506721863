package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Many;

import com.example.demo.model.Kecamatan;
import com.example.demo.model.Keluarga;
import com.example.demo.model.Kelurahan;
import com.example.demo.model.Kota;
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
	
	@Select("select id, nomor_kk, alamat, rt, rw, id_kelurahan, is_tidak_berlaku from keluarga where id = #{id_keluarga}")
	@Results(value = {
			@Result(property="id", column="id"),
			@Result(property="nomor_kk", column="nomor_kk"),
			@Result(property="alamat", column="alamat"),
			@Result(property="rt", column="rt"),
			@Result(property="rw", column="rw"),
			@Result(property="id_kelurahan", column="id_kelurahan"),
			@Result(property="is_tidak_berlaku", column="is_tidak_berlaku")
	}) 
	Keluarga selectKeluarga (@Param("id_keluarga") int id_keluarga);
	
	@Select("select id, nomor_kk, alamat, rt, rw, id_kelurahan, is_tidak_berlaku from keluarga where nomor_kk = #{nomor_kk}")
	@Results(value = {
			@Result(property="id", column="id"),
			@Result(property="nomor_kk", column="nomor_kk"),
			@Result(property="alamat", column="alamat"),
			@Result(property="rt", column="rt"),
			@Result(property="rw", column="rw"),
			@Result(property="id_kelurahan", column="id_kelurahan"),
			@Result(property="is_tidak_berlaku", column="is_tidak_berlaku"),
			@Result(property="penduduk", column="id",
					javaType = List.class,
					many=@Many(select="selectPendudukKeluarga"))
	}) 
	Keluarga selectKeluargaKK (@Param("nomor_kk") String nomor_kk);
	
	@Select("select penduduk.nama, penduduk.nik, penduduk.jenis_kelamin, penduduk.tempat_lahir, penduduk.tanggal_lahir, penduduk.is_wni, penduduk.id_keluarga, penduduk.agama, penduduk.pekerjaan, penduduk.status_perkawinan, penduduk.status_dalam_keluarga from penduduk, keluarga where penduduk.id_keluarga = keluarga.id AND keluarga.id = #{id}")
	List <Penduduk> selectPendudukKeluarga(@Param("id") int id);
	
	@Insert("INSERT INTO keluarga (alamat, rt, rw, id_kelurahan,) VALUES (#{alamat}, #{rt}, #{rw}, #{id_kelurahan})")
	void addKeluarga (Keluarga keluarga);
	
	@Insert("INSERT INTO penduduk (nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, is_wni, id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah) VALUES (#{nik}, #{nama}, #{tempat_lahir}, #{tanggal_lahir}, #{jenis_kelamin}, #{is_wni}, #{id_keluarga}, #{agama}, #{pekerjaan}, #{status_perkawinan}, #{status_dalam_keluarga}, #{golongan_darah})")
	void addPendudukKeluarga();
	
	@Update("UPDATE penduduk SET nama = #{nama}, tempat_lahir = #{tempat_lahir}, jenis_kelamin = #{jenis_kelamin}, is_wni = #{is_wni}, id_keluarga = #{id_keluarga}, agama = #{agama}, pekerjaan = #{pekerjaan}, status_perkawinan = #{status_perkawinan}, status_dalam_keluarga = #{status_dalam_keluarga}, golongan_darah = #{golongan_darah} FROM penduduk WHERE nik = #{nik}")
	void updatePenduduk(@Param("nik") String nik);
	
	@Update("UPDATE keluarga SET alamat = #{alamat}, rt = #{rt}, rw = #{rw}, id_kelurahan = #{id_kelurahan}, is_tidak_berlaku = #{is_tidak_berlaku} FROM keluarga WHERE nomor_kk = #{nomor_kk}")
	void updateKeluarga(@Param("nomor_kk") String nomor_kk);
	
	@Update("UPDATE penduduk SET is_wafat = #{is_wafat} FROM penduduk WHERE nik = #{nik}")
	void updateKematian(@Param("nik") String nik);
	
	@Select("select nama_kelurahan, id_kecamatan from kelurahan where id = #{id_kelurahan}")
	Kelurahan selectKelurahan(@Param("id_kelurahan") int id_kelurahan);
	
	@Select("select nama_kecamatan, kode_kecamatan, id_kota from kecamatan where id = #{id_kecamatan}")
	Kecamatan selectKecamatan(@Param("id_kecamatan") int id_kecamatan);
	
	@Select("select nama_kota, kode_kota from kota where id = #{id_kota}")
	Kota selectKota(@Param("id_kota") int id_kota);
}
