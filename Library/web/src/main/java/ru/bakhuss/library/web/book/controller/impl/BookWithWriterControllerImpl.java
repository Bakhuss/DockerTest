package ru.bakhuss.library.web.book.controller.impl;

import io.swagger.annotations.Api;
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
import ru.bakhuss.library.core.book.service.BookService;
import ru.bakhuss.library.core.person.model.Person;
import ru.bakhuss.library.web.book.controller.BookWithWriterController;
import ru.bakhuss.library.web.common.view.ResponseView;
import ru.bakhuss.library.web.person.util.converter.PersonConverterUtil;
import ru.bakhuss.library.web.person.view.PersonView;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static ru.bakhuss.library.web.common.Util.parseLongFromString;

@RestController
@RequestMapping(value = "/api/book/{id}", produces = APPLICATION_JSON_VALUE)
@Api(value = "BookWithWriterControllerApi", produces = APPLICATION_JSON_VALUE)
public class BookWithWriterControllerImpl implements BookWithWriterController {
    private final Logger log = LoggerFactory.getLogger(BookWithWriterControllerImpl.class);

    private final BookService bookService;

    @Autowired
    public BookWithWriterControllerImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    @PostMapping(value = "/add-writer")
    public ResponseView addWriter(@PathVariable String id,
                                  @RequestBody PersonView personView) {
        Long bookId = parseLongFromString(id);
        Long personId = parseLongFromString(personView.getId());
        bookService.addWriter(bookId, personId);
        return new ResponseView(true);
    }

    @Override
    @DeleteMapping(value = "/remove-writer")
    public ResponseView removeWriter(@PathVariable String id,
                                     @RequestBody PersonView personView) {
        Long bookId = parseLongFromString(id);
        Long personId = parseLongFromString(personView.getId());
        bookService.removeWriter(bookId, personId);
        return new ResponseView(true);
    }

    @Override
    @GetMapping(value = "/all-writers")
    public ResponseView getAllWriters(@PathVariable("id") String bookId) {
        Long id = parseLongFromString(bookId);
        List<Person> allWriters = bookService.getAllWriters(id);
        List<PersonView> writers = allWriters.stream()
                .map(PersonConverterUtil::personToPersonView)
                .collect(Collectors.toList());
        log.info(writers.toString());
        return new ResponseView(writers);
    }
}
