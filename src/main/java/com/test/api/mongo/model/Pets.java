/**
 * 
 */
package com.test.api.mongo.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

/**
 * @author MGupta
 *
 */

public class Pets {
	@Id
	public ObjectId _id;

	public String name;
	public String species;
	public String breed;
	/**
	 * @param _id
	 * @param name
	 * @param species
	 * @param breed
	 */
	public Pets(ObjectId _id, String name, String species, String breed) {
		super();
		this._id = _id;
		this.name = name;
		this.species = species;
		this.breed = breed;
	}
	/**
	 * @return the _id
	 */
	public ObjectId get_id() {
		return _id;
	}
	/**
	 * @param _id the _id to set
	 */
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the species
	 */
	public String getSpecies() {
		return species;
	}
	/**
	 * @param species the species to set
	 */
	public void setSpecies(String species) {
		this.species = species;
	}
	/**
	 * @return the breed
	 */
	public String getBreed() {
		return breed;
	}
	/**
	 * @param breed the breed to set
	 */
	public void setBreed(String breed) {
		this.breed = breed;
	}
}
//End of file