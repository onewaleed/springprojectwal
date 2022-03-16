package com.example.trainers.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.trainers.domain.Trainers;
import com.example.trainers.service.TrainersService;


@CrossOrigin
@RestController // tells spring that this is a controller.
public class TrainersController {
	
	private TrainersService service;

	@Autowired // tells spring to fetch the Equipment service from the context
	public TrainersController(TrainersService service) {

		super();
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<Trainers> createEquipment(@RequestBody Trainers e) {
		Trainers created = this.service.createTrainers(e);
		ResponseEntity<Trainers> response = new ResponseEntity<Trainers>(created, HttpStatus.CREATED);
		return response;
	}
	
	@GetMapping("/getAll")

	public ResponseEntity<List<Trainers>> getAllTrainers() {
		return ResponseEntity.ok(this.service.getAllTrainers());
	}

	@GetMapping("/get/{id}")

	public Trainers getTrainers(@PathVariable Integer id) {
		return this.service.getTrainers(id);
	}
	
	@PutMapping("/update/{id}")

	public ResponseEntity<Trainers> replaceEquipment(@PathVariable Integer id, @RequestBody Trainers newTrainers) {
		Trainers body = this.service.replaceTrainers(id, newTrainers);
		ResponseEntity<Trainers> response = new ResponseEntity<Trainers>(body, HttpStatus.ACCEPTED);
		return response;
	}

	@DeleteMapping("/delete/{id}")

	public ResponseEntity<?> deleteTrainers(@PathVariable Integer id) {
		this.service.deleteTrainers(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
