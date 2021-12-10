package com.bae.Cats.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bae.Cats.domain.Cat;
import com.bae.Cats.repo.CatRepo;

@Service
public class CatService {

	

	
	private CatRepo repo;
	
	@Autowired
	public CatService(CatRepo repo) {
		super();
		this.repo = repo; 
	}
	
	public Cat createCat(Cat cat) {
		Cat created = this.repo.save(cat);
		return created;
	}

	public List<Cat> getAllCats() {
		return this.repo.findAll();
	}

	public Cat getCat(Integer id) {
		Optional<Cat> found = this.repo.findById(id);
		return found.get();
	}
	public List<Cat> getAllCatsByName(String name) {
		List<Cat> found = this.repo.findByNameIgnoreCase(name);
		return found;
	}

	public Cat replaceCat(Integer id, Cat newCat) {
		Cat existing = this.repo.findById(id).get();
		
		existing.setName(newCat.getName());
		existing.setAge(newCat.getAge());
		existing.setColour(newCat.getColour());
		existing.setCute(newCat.isCute());
		
		Cat updated = this.repo.save(existing);

		return updated;
	}

	public void removeCat(Integer id) {
		this.repo.deleteById(id);
	}

}
