package com.lrn.cci.stackandqueue;

import java.util.concurrent.atomic.AtomicInteger;

public class AnimalShelter {

	QueueImpl<Animal> queueForCats = new QueueImpl<>();
	QueueImpl<Animal> queueForDogs = new QueueImpl<>();
	private final AtomicInteger serialNo = new AtomicInteger(0);

	class Animal {
		private final String type;
		private final String name;
		private final Integer sno;

		public Animal(final String type, final String name, final Integer sno) {
			super();
			this.type = type;
			this.name = name;
			this.sno = sno;
		}

		public String getType() {
			return type;
		}

		public String getName() {
			return name;
		}

		public Integer getSno() {
			return sno;
		}

	}

	public void addCat(final String name) {
		Animal cat = new Animal("cat", name, serialNo.incrementAndGet());
		queueForCats.enqueue(cat);
		System.out.println("Enqueue cat :" + name);
	}

	public void addDog(final String name) {
		Animal dog = new Animal("dog", name, serialNo.incrementAndGet());
		queueForDogs.enqueue(dog);
		System.out.println("Enqueue dog :" + name);
	}

	public Animal dequeueAnimal() {
		Animal animal = null;
		if (!queueForCats.isEmpty() && !queueForDogs.isEmpty()) {
			if (queueForCats.peek().getSno() < queueForDogs.peek().getSno()) {
				animal = queueForCats.dequque();
			} else {
				animal = queueForDogs.dequque();
			}
		} else if (!queueForCats.isEmpty()) {
			animal = queueForCats.dequque();
		} else if (!queueForDogs.isEmpty()) {
			animal = queueForDogs.dequque();
		}
		if (animal != null) {
			System.out.println("Dequeue " + animal.getType() + " : " + animal.getName());
		}
		return animal;
	}

	public Animal getCat() {
		Animal animal = null;
		if (!queueForCats.isEmpty()) {
			animal = queueForCats.dequque();
			System.out.println("Dequeue cat:" + animal.getName());
		} else {
			System.out.println("Current no cats are in animal shelter");
		}
		return animal;
	}

	public Animal getDog() {
		Animal animal = null;
		if (!queueForDogs.isEmpty()) {
			animal = queueForDogs.dequque();
			System.out.println("Dequeue dog:" + animal.getName());
		} else {
			System.out.println("Current no dogs are in animal shelter");
		}
		return animal;
	}

	public static void main(final String[] args) {
		AnimalShelter animalShelter = new AnimalShelter();
		animalShelter.addCat("cat1");
		animalShelter.addCat("cat2");
		animalShelter.addDog("dog1");
		animalShelter.addDog("dog2");
		animalShelter.addDog("dog3");
		animalShelter.addDog("dog4");
		animalShelter.addCat("cat3");
		animalShelter.addCat("cat4");
		animalShelter.addCat("cat5");
		animalShelter.addDog("dog6");
		animalShelter.addCat("cat6");
		animalShelter.addCat("cat7");
		animalShelter.addCat("cat8");
		animalShelter.dequeueAnimal();
		animalShelter.dequeueAnimal();
		animalShelter.dequeueAnimal();
		animalShelter.dequeueAnimal();
		animalShelter.getCat();
		animalShelter.getCat();
		animalShelter.getCat();
		animalShelter.getCat();
		animalShelter.getDog();
		animalShelter.getDog();
		animalShelter.getDog();
		animalShelter.getDog();
		animalShelter.getDog();
		animalShelter.getDog();
		animalShelter.getDog();
		animalShelter.getDog();
		animalShelter.getDog();
	}

}
