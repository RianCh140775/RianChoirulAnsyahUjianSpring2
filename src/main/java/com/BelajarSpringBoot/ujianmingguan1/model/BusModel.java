package com.BelajarSpringBoot.ujianmingguan1.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="bus")
public class BusModel {

	@Id
	@Column(length = 10)
	private String nomor_polisi;
	private int kapasitas;
	private String nama_supir;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "nama_perusahaan", referencedColumnName = "nama_perusahaan", unique = true)
	private PerusahaanModel nama_perusahaan;
}
