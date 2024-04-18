package ait.cohort34.person.controller;

import ait.cohort34.person.dto.CityDto;
import ait.cohort34.person.dto.PersonDto;
import ait.cohort34.person.model.Address;
import ait.cohort34.person.model.Person;
import ait.cohort34.person.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController{
    final PersonService personService;

    @PostMapping
    public Boolean addPerson(@RequestBody PersonDto personDto) {
        return personService.addPerson(personDto);
    }

    @GetMapping("/{id}")
    public PersonDto findPersonById(@PathVariable Integer id) {
        return personService.findPersonById(id);
    }

    @GetMapping("/name/{name}")
    public Iterable<Person> findPersonsByName(@PathVariable String name) {
        return personService.findPersonsByName(name);
    }

    @GetMapping("/city/{city}")
    public Iterable<Person> findPersonsByCity(@PathVariable String city) {
        return personService.findPersonsByCity(city);
    }

    @GetMapping("/ages/{minAge}/{maxAge}")
    public Iterable<Person> findPersonsByAge(@PathVariable("minAge") int minAge, @PathVariable("maxAge") int maxAge) {
        return personService.findPersonsByAge(minAge, maxAge);
    }

    @PutMapping("/{id}/name/{name}")
    public PersonDto updatePersonByName(@PathVariable("id") Integer id, @PathVariable("name") String name) {
        return personService.updatePersonByName(id, name);
    }

    @PutMapping("/{id}/{address}")
    public PersonDto updatePersonByAddress(@PathVariable("id") Integer id, @PathVariable("address") Address address) {
        return personService.updatePersonByAddress(id, address);
    }

    @GetMapping("/population/city")
    //TODO
    public List<CityDto> findCityByPopulation(int population) {
        return List.of();
    }

    @DeleteMapping("/{id}")
    public PersonDto deletePersonById(Integer id) {
        return personService.deletePersonById(id);
    }
}
