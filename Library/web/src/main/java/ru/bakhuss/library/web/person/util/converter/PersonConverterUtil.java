package ru.bakhuss.library.web.person.util.converter;

import ru.bakhuss.library.core.person.model.Person;
import ru.bakhuss.library.web.person.view.PersonView;

public class PersonConverterUtil {

    public static Person personViewToPerson(PersonView view) {
        Person person = new Person();
        person.setFirstName(view.getFirstName());
        person.setSecondName(view.getSecondName());
        person.setSurname(view.getSurname());
        person.setBirthday(view.getBirthday());
        person.setPhone(view.getPhone());
        person.setEmail(view.getEmail());
        person.setDescription(view.getDescription());
        return person;
    }

    public static PersonView personToPersonView(Person person) {
        PersonView pV = new PersonView();
        pV.setId(person.getId().toString());
        pV.setFirstName(person.getFirstName());
        pV.setSecondName(person.getSecondName());
        pV.setSurname(person.getSurname());
        pV.setBirthday(person.getBirthday());
        pV.setPhone(person.getPhone());
        pV.setEmail(person.getEmail());
        pV.setDescription(person.getDescription());
        return pV;
    }

    public static PersonView personToPersonViewWithId(Person person) {
        PersonView pV = new PersonView();
        pV.setId(person.getId().toString());
        pV.setId(person.getId().toString());
        pV.setFirstName(person.getFirstName());
        pV.setSecondName(person.getSecondName());
        pV.setSurname(person.getSurname());
        pV.setBirthday(person.getBirthday());
        pV.setPhone(person.getPhone());
        pV.setEmail(person.getEmail());
        pV.setDescription(person.getDescription());
        return pV;
    }
}
