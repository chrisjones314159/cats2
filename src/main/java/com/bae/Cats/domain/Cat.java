package com.bae.Cats.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Cat {

	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private int age;
	
	@Column(nullable = false)
	private String colour;
	
	@Column(nullable = false)
	private boolean isCute;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	 
	public Cat() {
		super();
	}

	
	
	
	public Cat(String name, int age, String colour, boolean isCute, Integer id) {
		super();
		this.name = name;
		this.age = age;
		this.colour = colour;
		this.isCute = isCute;
		this.id = id;
	}
	
	
	




	public Cat(String name, int age, String colour, boolean isCute) {
		super();
		this.name = name;
		this.age = age;
		this.colour = colour;
		this.isCute = isCute;
	}




	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public boolean isCute() {
		return isCute;
	}

	public void setCute(boolean isCute) {
		this.isCute = isCute;
	}
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Cat [name=" + name + ", age=" + age + ", colour=" + colour + ", isCute=" + isCute + "]";
	}

}
