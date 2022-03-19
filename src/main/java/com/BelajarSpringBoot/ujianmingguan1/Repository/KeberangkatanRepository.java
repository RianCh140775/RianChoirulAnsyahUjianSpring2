package com.BelajarSpringBoot.ujianmingguan1.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.BelajarSpringBoot.ujianmingguan1.model.BookingCustomeGetNameModel;
import com.BelajarSpringBoot.ujianmingguan1.model.KeberangkatanModel;

public interface KeberangkatanRepository extends JpaRepository<KeberangkatanModel, Long>{

	@Query(value="SELECT `keberangkatan`.`id_keberangkatan`,`perusahaan`.`nama_perusahaan`,`keberangkatan`.`kelas`,`keberangkatan`.`harga`,`keberangkatan`.`tanggal`,`jurusan`.`deskripsi` FROM `keberangkatan`\r\n"
			+ "INNER JOIN `bus` on `keberangkatan`.`no_polisi`= `bus`.`nomor_polisi`\r\n"
			+ "INNER JOIN `perusahaan` ON `bus`.`nama_perusahaan` = `perusahaan`.`nama_perusahaan`\r\n"
			+ "INNER JOIN `jurusan` ON `keberangkatan`.`jurusan_id` = `jurusan`.`jurusan_id`\r\n"
			+ "WHERE `jurusan`.`terminal_awal` = ?1 "
			+ "AND `tanggal` LIKE ?2% ", nativeQuery = true)
	
	List<BookingCustomeGetNameModel> getAllDataTerminalTanggal(String terminal, String tanggal);
	
//	List<BookingCustomeGetNameModel> getAllDataKeberangkatan();
}
