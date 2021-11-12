package com.dddd.doctorpatientrest.application.services;

import java.util.List;
import java.util.Optional;

public interface GenericService<T> {

	List<T> findAll();

	Optional<T> findById(long id);

	T save(T entity);

	void deleteById(long id);
}
