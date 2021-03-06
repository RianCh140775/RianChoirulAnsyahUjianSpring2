package com.BelajarSpringBoot.ujianmingguan1.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table (name = "keberangkatan")
public class KeberangkatanModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_keberangkatan;
	private int harga;
	private String kelas;
	private String tanggal;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "jurusanId", referencedColumnName = "jurusanId",unique = true)
	private JurusanModel jurusanId;
	private String no_polisi;

}
