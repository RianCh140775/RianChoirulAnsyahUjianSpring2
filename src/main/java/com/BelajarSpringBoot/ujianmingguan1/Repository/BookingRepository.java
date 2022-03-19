package com.BelajarSpringBoot.ujianmingguan1.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.BelajarSpringBoot.ujianmingguan1.model.BookingCustomeGetNameModel;
import com.BelajarSpringBoot.ujianmingguan1.model.BookingModel;

public interface BookingRepository extends JpaRepository<BookingModel, Long>{

	
}
