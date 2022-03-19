package com.BelajarSpringBoot.ujianmingguan1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="perusahaan")
public class PerusahaanModel {

	@Id
//	@GeneratedValue (strategy = GenerationType.IDENTITY)
//	private long id;
//	@Column(name="nama", unique = true, nullable = false)
	@Column(length = 30)
	private String nama_perusahaan;
	private String alamat;
}
