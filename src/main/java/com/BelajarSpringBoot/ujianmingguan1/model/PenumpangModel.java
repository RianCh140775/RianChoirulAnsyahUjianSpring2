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
@Table(name="penumpang")
public class PenumpangModel {

	@Id

	@Column(length = 20)
	private String nik;
	private String nama;
	private String username;
	private String password;
	private String telepon;
}
