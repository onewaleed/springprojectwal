package com.example.trainers.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.trainers.domain.Trainers;

@Repository
public interface TrainersRepo extends JpaRepository<Trainers, Integer> {
	
	//spring auto-generated the basic CRUD functionalities
	
	List<Trainers> findByBrandIgnoreCase(String brand);
	
	List<Trainers> findByModel(String model);
	
	}