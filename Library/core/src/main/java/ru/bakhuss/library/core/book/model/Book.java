package ru.bakhuss.library.core.book.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.bakhuss.library.core.catalog.model.Catalog;
import ru.bakhuss.library.core.person.model.Person;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import java.util.ArrayList;
import java.util.List;

/**
 * Книга (название, автор)
 */
@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Version
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Integer version;

    /**
     * Название
     */
    @Column(nullable = false)
    private String name;

    /**
     * Запись в каталоге книг
     */
    @OneToMany(mappedBy = "book",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<Catalog> catalogs;

    /**
     * Автор(-ы)
     */
    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_writer",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Person> writers;


    public void updateState(Book book) {
        setName(book.getName());
    }

    public List<Person> getWriters() {
        if (writers == null) {
            writers = new ArrayList<>();
        }
        return writers;
    }

    public void addWriter(Person person) {
        getWriters().add(person);
    }

    public void removeWriter(Person person) {
        getWriters().remove(person);
        person.getWrittenBooks().remove(this);
    }

    public List<Catalog> getCatalogs() {
        if (catalogs == null)
            catalogs = new ArrayList<>();
        return catalogs;
    }

    public void addCatalog(Catalog catalog) {
        getCatalogs().add(catalog);
        catalog.setBook(this);
    }

    public void removeCatalog(Catalog catalog) {
        getCatalogs().remove(catalog);
        catalog.setBook(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "{id:" + getId() +
                ";name:" + getName() +
                "}";
    }
}
