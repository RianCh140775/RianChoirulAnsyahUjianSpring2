package com.BelajarSpringBoot.ujianmingguan1.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BelajarSpringBoot.ujianmingguan1.Repository.PerusahaanRepository;
import com.BelajarSpringBoot.ujianmingguan1.model.PerusahaanModel;

@RestController
@RequestMapping("/perusahaan")
public class PerusahaanController {

	@Autowired
	PerusahaanRepository perusahaanrepo;
	
	@PostMapping("/insertPerusahaan")
	private String saveAllPerusahaan(@RequestBody List<PerusahaanModel> perusahaan) {
		perusahaanrepo.saveAll(perusahaan);
		return "Data Berhasil Disimpan";
	}
	
	@GetMapping("/")
	private List<PerusahaanModel> getAllPerusahaan(){
		return perusahaanrepo.findAll();
	}
}
