/**
 * 
 */
package com.test.api.mongo.model.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.api.mongo.model.Pets;

/**
 * @author MGupta
 *
 */
public interface PetsRepository extends MongoRepository<Pets, String> {
	/**
	 * Operation to find pets by ID. </br>
	 * 
	 * @param _id
	 * @return
	 */
	Pets findBy_id(ObjectId _id);
}
