package ru.bakhuss.library.core.book.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bakhuss.library.core.book.dao.BookDao;
import ru.bakhuss.library.core.book.model.Book;
import ru.bakhuss.library.core.book.service.BookService;
import ru.bakhuss.library.core.catalog.dao.CatalogDao;
import ru.bakhuss.library.core.catalog.model.Catalog;
import ru.bakhuss.library.core.person.dao.PersonDao;
import ru.bakhuss.library.core.person.model.Person;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    private final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    private final BookDao bookDao;
    private final PersonDao personDao;
    private final CatalogDao catalogDao;

    @Autowired
    public BookServiceImpl(PersonDao personDao,
                           CatalogDao catalogDao,
                           BookDao bookDao) {
        this.bookDao = bookDao;
        this.personDao = personDao;
        this.catalogDao = catalogDao;
    }

    @Override
    public Long addBook(Book book) {
        Book newBook = bookDao.save(book);
        log.info(newBook.toString());
        return newBook.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public Book getBook(Long id) {
        Book book = bookDao.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        ("Book by id = " + id + " not found")
                ));
        log.info(book.toString());
        return book;
    }

    @Override
    public void updateBook(Book book) {
        Book updatedBook = bookDao.save(book);
        log.info(updatedBook.toString());
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookDao.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Not found book by id " + id
                ));
        book.setWriters(null);
        bookDao.deleteById(id);
        log.info("Deleted book by id " + id);
    }

    @Override
    public void addWriter(Long bookId, Long personId) {
        Book book = bookDao.findById(bookId)
                .orElseThrow(() -> new RuntimeException(
                        "Not found book by id " + bookId
                ));

        boolean hasWriter = book.getWriters().stream()
                .map(Person::getId)
                .collect(Collectors.toList())
                .contains(personId);

        if (!hasWriter) {
            Person person = personDao.findById(personId)
                    .orElseThrow(() -> new RuntimeException(
                            "Not found person by id " + personId
                    ));
            book.addWriter(person);
        } else log.warn("Book by id " + bookId + " has writer by person id " + personId);
    }

    @Override
    public void removeWriter(Long bookId, Long personId) {
        Book book = bookDao.findById(bookId)
                .orElseThrow(() -> new RuntimeException(
                        "Not found book by id " + bookId
                ));
        Person person = personDao.findById(personId)
                .orElseThrow(() -> new RuntimeException(
                        "Not found person by id " + personId
                ));
        book.removeWriter(person);
    }

    @Override
    public void addCatalog(Long bookId, Long catalogId) {
        Book book = bookDao.findById(bookId)
                .orElseThrow(() -> new RuntimeException(
                        "Not found book by id " + bookId
                ));

        boolean hasCatalog = book.getCatalogs().stream()
                .map(Catalog::getId)
                .collect(Collectors.toList())
                .contains(catalogId);

        if (!hasCatalog) {
            Catalog catalog = catalogDao.findById(catalogId)
                    .orElseThrow(() -> new RuntimeException(
                            "Not found catalog by id " + catalogId
                    ));
            book.addCatalog(catalog);
        } else log.warn("Book by id " + bookId + " has catalog by id " + catalogId);
    }

    @Override
    public void removeCatalog(Long bookId, Long catalogId) {
        Book book = bookDao.findById(bookId)
                .orElseThrow(() -> new RuntimeException(
                        "Not found book by id " + bookId
                ));
        Catalog catalog = catalogDao.findById(catalogId)
                .orElseThrow(() -> new RuntimeException(
                        "Not found catalog by id " + catalogId
                ));
        book.removeCatalog(catalog);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Person> getAllWriters(Long bookId) {
        List<Person> writers = bookDao.getWriters(bookId);
        if (writers == null)
            throw new RuntimeException(
                    "Not found writers by book id " + bookId
            );
        return writers;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Catalog> getAllCatalogs(Long bookId) {
        List<Catalog> catalogs = bookDao.getCatalogs(bookId);
        if (catalogs == null)
            throw new RuntimeException(
                    "Not found catalogs by book id " + bookId
            );
        return catalogs;
    }
}
