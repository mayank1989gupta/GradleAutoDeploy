/**
 * 
 */
package com.test.api.controller;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.api.mongo.model.Pets;
import com.test.api.mongo.model.repositories.PetsRepository;

/**
 * @author MGupta
 *
 * Controller to interact with Collection Pets.</br>
 */
@RestController
@RequestMapping("/pets")
public class PetsController {

	@Autowired
	private PetsRepository petsRepo;

	/**
	 * API to fetch all the pets from the Repo.</br>
	 * 
	 * @return
	 */
	@GetMapping("/")
	public List<Pets> getAllPets() {
		//call to Repo
		Supplier<List<Pets>> pets = () -> petsRepo.findAll();

		return pets.get();//response
	}

	/**
	 * API to fetch the data from the repo for the given ID.</br>
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public Pets getPetById(@PathVariable("id") ObjectId id) {
		Supplier<Pets> pet = () -> petsRepo.findBy_id(id);
		return pet.get();//response
	}

	/**
	 * API to update the record.</br>
	 * 
	 * @param id
	 * @param pets
	 */
	@PutMapping("/{id}")
	public void modifyPetById(@PathVariable("id") ObjectId id, 
			@Valid @RequestBody Pets pets) {
		pets.set_id(id);
		//invoking call to save pets object
		Consumer<Pets> pet = arg -> petsRepo.save(pets);
		pet.accept(pets);//consumer func
	}

	/**
	 * API to create new record in the collection.</br>
	 * 
	 * @param pets
	 * @return
	 */
	@PostMapping("/")
	public Pets createPet(@Valid @RequestBody Pets pets) {
		pets.set_id(ObjectId.get());
		petsRepo.save(pets);
		return pets;
	}
}
