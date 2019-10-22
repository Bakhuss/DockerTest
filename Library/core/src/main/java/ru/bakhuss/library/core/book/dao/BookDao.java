package ru.bakhuss.library.core.book.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.bakhuss.library.core.book.model.Book;
import ru.bakhuss.library.core.catalog.model.Catalog;
import ru.bakhuss.library.core.person.model.Person;

import java.util.List;

@Repository
public interface BookDao extends JpaRepository<Book, Long> {

    @Query(name = "Book.getWriters")
    List<Person> getWriters(Long bookId);

    @Query(name = "Book.getCatalogs")
    List<Catalog> getCatalogs(Long bookId);
}
