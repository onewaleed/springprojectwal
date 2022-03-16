package com.example.trainers.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.trainers.domain.Trainers;
import com.example.trainers.repo.TrainersRepo;

@Service
public class TrainersService implements ServiceIF<Trainers> {

	private TrainersRepo trepo;

	@Autowired
	public TrainersService(TrainersRepo trepo) {
		this.trepo = trepo;
	}

//create
	public Trainers createTrainers(Trainers e) {

		Trainers created = this.trepo.save(e);
		return created;
	}

//read
	public List<Trainers> getAllTrainers() {

		return this.trepo.findAll();
	}

	public Trainers getTrainers(Integer id) {
		Optional<Trainers> find = this.trepo.findById(id);
		return find.get();
	}

	public List<Trainers> getAllEquipByBrand(String brand) {
		List<Trainers> findbrand = this.trepo.findByBrandIgnoreCase(brand);
		return findbrand;
	}

	public List<Trainers> getAllEquipByModel(String model) {
		List<Trainers> findmodel = this.trepo.findByModel(model);
		return findmodel;
	}

//update
	public Trainers replaceTrainers(Integer id, Trainers newTrainers) {
		Trainers exists = this.trepo.findById(id).get();
		exists.setBrand (newTrainers.getBrand());
		exists.setModel(newTrainers.getModel());
		Trainers replaced = this.trepo.save(exists);
		return replaced;
	}

//delete
	public void deleteTrainers(@PathVariable Integer id) {

		this.trepo.deleteById(id);
	}
}