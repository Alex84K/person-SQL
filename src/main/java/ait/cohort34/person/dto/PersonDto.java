package ait.cohort34.person.dto;

import ait.cohort34.person.model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {
    Integer id;
    String name;
    LocalDate birthDate;
    AddressDto address;

    public static int calculateAge(LocalDate dateOfBirth) {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(dateOfBirth, currentDate);
        return period.getYears();
    }
}

