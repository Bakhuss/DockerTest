package ru.bakhuss.library.core.book.service;

import ru.bakhuss.library.core.book.model.Book;
import ru.bakhuss.library.core.catalog.model.Catalog;
import ru.bakhuss.library.core.person.model.Person;

import java.util.List;

public interface BookService {
    Long addBook(Book book);

    Book getBook(Long id);

    void updateBook(Book book);

    void deleteBook(Long id);

    void addWriter(Long bookId, Long personId);

    void removeWriter(Long bookId, Long personId);

    void addCatalog(Long bookId, Long catalogId);

    void removeCatalog(Long bookId, Long catalogId);

    List<Person> getAllWriters(Long bookId);

    List<Catalog> getAllCatalogs(Long bookId);
}
