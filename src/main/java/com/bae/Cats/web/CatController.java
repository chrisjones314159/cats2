package com.bae.Cats.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bae.Cats.domain.Cat;
import com.bae.Cats.service.CatService;

@RestController // tells Spring this is a controller
				// REST compliant

public class CatController {

	private CatService service;

	@Autowired // tells Spring to fetch the DinoService from the context

	public CatController(CatService service) {
		super();
		this.service = service;
	}

	@GetMapping("/hello") 
	public String hello() {
		return "Hello, World!";
	}

	@PostMapping("/create") // 201 - Created
	public ResponseEntity<Cat> createCat(@RequestBody Cat cat) {
		Cat created = this.service.createCat(cat);
		ResponseEntity<Cat> response = new ResponseEntity<Cat>(created, HttpStatus.CREATED);
		return response;
	}

	@GetMapping("/getAll") // 200 - OK
	public ResponseEntity<List<Cat>> getAllCats() {
		return ResponseEntity.ok(this.service.getAllCats());
	}

	@GetMapping("/get/{id}") // 200
	public Cat getCat(@PathVariable Integer id) {
		return this.service.getCat(id);
	}
	
	@GetMapping("/getByName/{name}")
	public ResponseEntity<List<Cat>> getCatByName(@PathVariable String name) {
		List<Cat> found = this.service.getAllCatsByName(name);
		return ResponseEntity.ok(found);
	}

	@PutMapping("/replace/{id}") // 202 - Accepted
	public ResponseEntity<Cat> replaceCat(@PathVariable Integer id, @RequestBody Cat newCat) {
		Cat body = this.service.replaceCat(id, newCat);

		ResponseEntity<Cat> response = new ResponseEntity<Cat>(body, HttpStatus.ACCEPTED);
		return response;
	}

	@DeleteMapping("/remove/{id}") // 204
	public ResponseEntity<?> removeCat(@PathVariable Integer id) {
		this.service.removeCat(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
