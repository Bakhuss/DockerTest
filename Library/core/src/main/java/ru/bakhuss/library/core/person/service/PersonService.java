package ru.bakhuss.library.core.person.service;

import ru.bakhuss.library.core.book.model.Book;
import ru.bakhuss.library.core.person.model.Person;

import java.util.List;

public interface PersonService {
    Long addPerson(Person person);

    Person getPerson(Long id);

    void updatePerson(Person person);

    void deletePerson(Long id);

    void addWrittenBook(Long personId, Long bookId);

    void removeWrittenBook(Long personId, Long bookId);

    List<Book> getAllWrittenBooks(Long personId);
}
