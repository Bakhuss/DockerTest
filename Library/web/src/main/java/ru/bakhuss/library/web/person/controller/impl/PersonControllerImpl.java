package ru.bakhuss.library.web.person.controller.impl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bakhuss.library.core.person.model.Person;
import ru.bakhuss.library.core.person.service.PersonService;
import ru.bakhuss.library.web.common.view.ResponseView;
import ru.bakhuss.library.web.person.controller.PersonController;
import ru.bakhuss.library.web.person.view.PersonView;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static ru.bakhuss.library.web.common.Util.parseLongFromString;
import static ru.bakhuss.library.web.person.util.converter.PersonConverterUtil.personToPersonView;
import static ru.bakhuss.library.web.person.util.converter.PersonConverterUtil.personViewToPerson;

@RestController
@RequestMapping(value = "/api/person", produces = APPLICATION_JSON_VALUE)
@Api(value = "PersonControllerApi", description = "Person Controller Main")
public class PersonControllerImpl implements PersonController {
    private final Logger log = LoggerFactory.getLogger(PersonControllerImpl.class);

    private final PersonService personService;

    @Autowired
    public PersonControllerImpl(PersonService personService) {
        this.personService = personService;
    }

    @Override
    @PostMapping(value = "/save")
    public ResponseView addPerson(@RequestBody PersonView view) {
        personService.addPerson(personViewToPerson(view));
        return new ResponseView(true);
    }

    @Override
    @GetMapping(value = "/{id}")
    @ApiOperation("Gets the person with specific id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Person.class)})
    public ResponseView getPerson(@PathVariable("id") Person person,
                                  @PathVariable String id) {
        if (person == null) throw new RuntimeException("Person by id " + id + " not found");
        PersonView view = personToPersonView(person);
        view.setId(person.getId().toString());
        System.out.println(person);
        return new ResponseView(view);
    }

    @Override
    @PostMapping(value = "/update")
    public ResponseView updatePerson(@RequestBody PersonView view) {
        Long id = parseLongFromString(view.getId());
        Person person = personService.getPerson(id);
        person.updateState(personViewToPerson(view));
        personService.updatePerson(person);
        return new ResponseView(true);
    }

    @Override
    @DeleteMapping(value = "/delete")
    public ResponseView deletePerson(@RequestBody PersonView view) {
        Long id = parseLongFromString(view.getId());
        personService.deletePerson(id);
        return new ResponseView(true);
    }
}
