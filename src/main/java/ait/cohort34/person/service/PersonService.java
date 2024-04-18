package ait.cohort34.person.service;

import ait.cohort34.person.dto.CityDto;
import ait.cohort34.person.dto.PersonDto;
import ait.cohort34.person.dto.PersonUpdateDto;
import ait.cohort34.person.model.Address;
import ait.cohort34.person.model.Person;

import java.util.List;

public interface PersonService {
    Boolean addPerson(PersonDto personDto);
    PersonDto findPersonById(Integer id);
    Iterable<Person> findPersonsByName(String name);
    Iterable<Person> findPersonsByCity(String city);
    Iterable<Person> findPersonsByAge(int minAge, int maxAge);
    PersonDto updatePersonByName(Integer id, String name);
    PersonDto updatePersonByAddress(Integer id, Address address);
    List<CityDto> findCityByPopulation(int population);
    PersonDto deletePersonById(Integer id);
}
