package com.BelajarSpringBoot.ujianmingguan1.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BelajarSpringBoot.ujianmingguan1.model.BusModel;
import com.BelajarSpringBoot.ujianmingguan1.model.PenumpangModel;

public interface BusRepository extends JpaRepository<BusModel, String>{


		
}
