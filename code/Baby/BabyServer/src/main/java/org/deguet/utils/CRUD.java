package org.deguet.utils;

import org.deguet.model.Identifiable;

import java.util.List;

public interface CRUD<T extends Identifiable> {

    int count();

    public class BadId extends RuntimeException{}
	
	/**
	 * Recover one by its ID
	 * @param id
	 * @return
	 */
	public T get(String id) ;

	/**
	 * Retrieve all the elements
	 * @return
	 */
	public List<T> getAll();

	/**
	 * If the ID is null:
	 *  - assign the next available id
	 *  - persist this new object
	 * If the id is not null, we consider the object already exists
	 *  - update the object
	 *  - triggers an exception if the ID is bad
	 * @param a
	 */
	public void save(T a) throws BadId;

	/**
	 * Deletes an object in persistence.
	 * Actually deletes the persisted object with the parameter's id.
	 * @param a
	 */
	public void delete(T a);

	/**
	 * Deletes all objects.
	 */
	public void deleteAll();
	
}
