package com.BelajarSpringBoot.ujianmingguan1.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BelajarSpringBoot.ujianmingguan1.Repository.KeberangkatanRepository;
import com.BelajarSpringBoot.ujianmingguan1.model.KeberangkatanModel;

@RestController
@RequestMapping("/keberangkatan")
public class KeberangkatanController {

	@Autowired
	KeberangkatanRepository keberangkatanrepo;
	
	@PostMapping("/insertKeberangkatan")
	private String saveAllKeberangkatan(
			@RequestBody List<KeberangkatanModel> keberangkatan) {
		keberangkatanrepo.saveAll(keberangkatan);
		return "Data Berhasil Disimpan";
	}
	
	@GetMapping("/")
	private List<KeberangkatanModel> getAllKeberangkatan(){
		return keberangkatanrepo.findAll();
	}
}
