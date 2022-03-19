package com.BelajarSpringBoot.ujianmingguan1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BelajarSpringBoot.ujianmingguan1.model.PenumpangModel;

public interface PenumpangRepository extends JpaRepository<PenumpangModel, String>{

	PenumpangModel findByUsername(String username);
}
