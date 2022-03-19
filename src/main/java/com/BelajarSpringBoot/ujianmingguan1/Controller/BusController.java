package com.BelajarSpringBoot.ujianmingguan1.Controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BelajarSpringBoot.ujianmingguan1.Repository.BookingRepository;
import com.BelajarSpringBoot.ujianmingguan1.Repository.BusRepository;
import com.BelajarSpringBoot.ujianmingguan1.Repository.KeberangkatanRepository;
import com.BelajarSpringBoot.ujianmingguan1.Repository.PenumpangRepository;
import com.BelajarSpringBoot.ujianmingguan1.model.BookingCustomeGetNameModel;
import com.BelajarSpringBoot.ujianmingguan1.model.BusModel;
import com.BelajarSpringBoot.ujianmingguan1.model.PenumpangModel;

@RestController
@RequestMapping("/busbookingsystem")
public class BusController {

	@Autowired // autowired dapat dipakai lebih dari satu per satu repository
	BusRepository busrepo;
	
	@Autowired
	KeberangkatanRepository keberangkatanRepo;
	
	@Autowired
	BookingRepository bookingRepo;
	
	@Autowired
	PenumpangRepository penumpangrepo;
	
	@PostMapping("/insertBus")
	private String saveAllBus(
			@RequestBody List<BusModel> bus) {
		busrepo.saveAll(bus);
		return "Data Berhasil Disimpan";
	}
	
	@PostMapping("/insertPenumpang")
	private String saveAllPenumpang(
			@RequestBody PenumpangModel penumpang) {
		penumpangrepo.save(penumpang);
		return "Data penumpang berhasil ditambahkan";
	}
	
	@GetMapping("/getPenumpang/{nik}")
	private String cariPenumpang(
			@PathVariable(value="nik")String nik,
			@RequestBody PenumpangModel penumpang) {
		if (nik.equals(penumpang.getNik())) {
			return "Penumpang telah terdaftar";
		}else{
			return "Penumpang belum terdaftar";
		}
		 
	}
	
	@GetMapping("/")
	private List<BusModel> getAllBus(){
		return busrepo.findAll();
	}
	
	@PostMapping("/booking")
	private String saveDataPenumpang(
			@RequestBody PenumpangModel penumpang) {
		penumpangrepo.save(penumpang);
		return "Pemesanan Bus berhasil";
	}
	
	
	@GetMapping("/findKeberangkatan")
	private List<BookingCustomeGetNameModel> getAllDataTerminalAwalTanggal(
			@RequestParam (name= "terminal")String terminal,
			@RequestParam (name="tanggal")String tanggal){
		return keberangkatanRepo.getAllDataTerminalTanggal(terminal, tanggal);
	}
	
	@PostMapping("/cancel")
	private String deleteByBookingId(@RequestParam(name="id")long id) {
		bookingRepo.deleteById(id);
		return "Pemesanan dengan id"+id+"telah dibatalkan";
	}
	
	
}
