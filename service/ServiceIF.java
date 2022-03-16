package com.example.trainers.service;

import java.util.List;

public interface ServiceIF<T> {
	
	T createTrainers(T t);
	
	List<T> getAllTrainers();
	
	T getTrainers(Integer id);
	
	T replaceTrainers(Integer id, T t);
	
	void deleteTrainers(Integer id);

}