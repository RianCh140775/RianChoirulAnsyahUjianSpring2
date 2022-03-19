package com.BelajarSpringBoot.ujianmingguan1.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table (name = "jurusan")
public class JurusanModel {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long jurusanId;
	private String deskripsi;
	private String terminal_awal;
	private String terminal_akhir;
}
