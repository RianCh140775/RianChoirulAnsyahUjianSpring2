package com.BelajarSpringBoot.ujianmingguan1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.BelajarSpringBoot.ujianmingguan1.Config.JwtTokenutil;
import com.BelajarSpringBoot.ujianmingguan1.Repository.PenumpangRepository;
import com.BelajarSpringBoot.ujianmingguan1.Service.JwtPenumpangDetailService;
import com.BelajarSpringBoot.ujianmingguan1.model.PenumpangModel;




@RestController
@RequestMapping("/penumpang")
public class PenumpangController {

	@Autowired
	PenumpangRepository penumpangRepo;
	
	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	JwtPenumpangDetailService jwtpenumpangdetailservice;
	
	@Autowired
	JwtTokenutil jwtTokenUtil;
	
	@Autowired
	PasswordEncoder pEncoder;
	
	@PostMapping("/authenticate")
	private ResponseEntity<?> login(@RequestBody PenumpangModel penumpangModel)throws Exception {
		authenticate(penumpangModel.getUsername(),penumpangModel.getPassword());
		final UserDetails userDetails = jwtpenumpangdetailservice
				.loadUserByUsername(penumpangModel.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(token);
	}
	
	
	private void authenticate(String username, String password)throws Exception {
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}catch (DisabledException error){
			//user disabled
			throw new Exception("USER DISABLED", error);
		}catch (BadCredentialsException e) {
			// invalid credentials
			throw new Exception("INVALID_CREDENTIALS",e);
		}
	}
	
	@PostMapping("/registrasi")
	private ResponseEntity<String> savePenumpang(@RequestBody PenumpangModel penumpang){
		penumpang.setPassword(pEncoder.encode(penumpang.getPassword()));
		penumpangRepo.save(penumpang);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("Berhasil dibuat");
	}
	
}
