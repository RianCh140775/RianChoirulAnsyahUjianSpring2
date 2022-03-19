package com.BelajarSpringBoot.ujianmingguan1.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BelajarSpringBoot.ujianmingguan1.Repository.JurusanRepository;
import com.BelajarSpringBoot.ujianmingguan1.model.JurusanModel;

@RestController
@RequestMapping("/jurusan")
public class JurusanController {

	@Autowired
	JurusanRepository jurusanrepo;
	
	@PostMapping("/insertJurusan")
	private String saveAllJurusan(
			@RequestBody List<JurusanModel> jurusan) {
		jurusanrepo.saveAll(jurusan);
		return "Data Berhasil Disimpan";
	}
	
	@GetMapping("/")
	private List<JurusanModel> getAllJurusan(){
		return jurusanrepo.findAll();
	}
}
