package com.dddd.doctorpatientrest.application.services;

import java.util.List;

public interface GenericService<T> {

	List<T> findAll();

	T findById(long id);

	T save(T t);

	T update(T t);

	void deleteById(long id);
}
