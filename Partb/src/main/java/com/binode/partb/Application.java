package com.binode.partb;

import com.binode.partb.Person;
import com.binode.partb.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories
public class Application implements CommandLineRunner {

    @Autowired
    private PersonRepository personRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        long startTime = System.currentTimeMillis();

        // Creating 10,000 Person objects with 10 Pet objects each
        List<Person> persons = new ArrayList<>();
        for (int i = 1; i <= 10000; i++) {
            Person person = new Person();
            person.setFirstName("FirstName" + i);
            person.setLastName("LastName" + i);

            List<Pet> pets = new ArrayList<>();
            for (int j = 1; j <= 10; j++) {
                Pet pet = new Pet();
                pet.setName("Pet" + j);
                pet.setAge(j);
                pets.add(pet);
            }
            person.setPets(pets);
            persons.add(person);
        }

        personRepository.saveAll(persons);

        long endTime = System.currentTimeMillis();
        System.out.println("Time to store 10,000 Persons: " + (endTime - startTime) + "ms");

        startTime = System.currentTimeMillis();

        List<Person> retrievedPersons = personRepository.findAll();

        endTime = System.currentTimeMillis();
        System.out.println("Time to retrieve 10,000 Persons: " + (endTime - startTime) + "ms");
    }
}
