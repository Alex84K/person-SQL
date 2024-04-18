package ait.cohort34.person.service;

import ait.cohort34.person.dao.PersonRepository;
import ait.cohort34.person.dto.AddressDto;
import ait.cohort34.person.dto.CityDto;
import ait.cohort34.person.dto.PersonDto;
import ait.cohort34.person.dto.exeptions.PersonNotFoundException;
import ait.cohort34.person.model.Address;
import ait.cohort34.person.model.Person;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService{
    @Autowired
    final PersonRepository personRepository;
    final ModelMapper modelMapper;

    @Override
    public Boolean addPerson(PersonDto personDto) {
        if(personRepository.existsById(personDto.getId())) {
            return false;
        }
        personRepository.save(modelMapper.map(personDto, Person.class));
        return true;
    }

    @Override
    public PersonDto findPersonById(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    public Iterable<Person> findPersonsByName(String name) {
        return personRepository.findAll().
                stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .map(p -> new Person(p.getId(), p.getName(), p.getBirthDate(), p.getAddress()))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Person> findPersonsByCity(String city) {
        return personRepository.findAll().
                stream()
                .filter(p -> p.getAddress().getCity().equalsIgnoreCase(city))
                .map(p -> new Person(p.getId(), p.getName(), p.getBirthDate(), p.getAddress()))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Person> findPersonsByAge(int young, int old) {
        return personRepository.findAll()
                .stream()
                //.filter(s -> s.getScores().containsKey(exam) && s.getScores().get(exam) > minScore)
                .filter(p -> p.calculateAge(p.getBirthDate()) > young && p.calculateAge(p.getBirthDate()) < old)
                .map(p -> new Person(p.getId(), p.getName(), p.getBirthDate(), p.getAddress()))
                .collect(Collectors.toList());
    }

    @Override
    public PersonDto updatePersonByName(Integer id, String name) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        if (person.getName() != null) {
            person.setName(person.getName());
        }
        AddressDto address = new AddressDto(person.getAddress().getCity(), person.getAddress().getStreet(), person.getAddress().getBuilding());
        personRepository.save(person);
        return new PersonDto(id, name, person.getBirthDate(), address);
    }

    @Override
    public PersonDto updatePersonByAddress(Integer id, Address address) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        if (person.getAddress() != null) {
            person.setAddress(address);
        }
        AddressDto newAddress = new AddressDto(person.getAddress().getCity(), person.getAddress().getStreet(), person.getAddress().getBuilding());
        personRepository.save(person);
        return new PersonDto(id, person.getName(), person.getBirthDate(), newAddress);
    }

    @Override
    public List<CityDto> findCityByPopulation(int population) {
        return List.of();
    }

    @Override
    public PersonDto deletePersonById(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        personRepository.deleteById(id);
        return modelMapper.map(person, PersonDto.class);
    }
}
