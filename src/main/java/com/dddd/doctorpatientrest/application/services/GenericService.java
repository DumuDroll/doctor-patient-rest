package com.dddd.doctorpatientrest.application.services;

import java.util.List;

public interface GenericService<E, D> {

	List<D> findAll();

	D findById(long id);

	D save(E e);

	D update(E e);

	void deleteById(long id);
}
