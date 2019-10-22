package ru.bakhuss.library.core.catalog.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.bakhuss.library.core.book.model.Book;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

/**
 * Список книг
 */
@Data
@Entity
public class Catalog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Version
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Integer version;

    /**
     * ISBN
     */
    @Column(length = 15)
    private String isbn;

    /**
     * Описание
     */
    @Column(length = 1000)
    private String description;

    /**
     * Количество экземпляров книги в библиотеке
     */
    @Column(name = "total_count")
    private Integer totalCount;

    /**
     * Книга
     */
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;


    public void updateState(Catalog catalog) {
        setIsbn(catalog.getIsbn());
        setDescription(catalog.getDescription());
        setTotalCount(catalog.getTotalCount());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "{id:" + getId() +
                ";description:" + getDescription() +
                ";totalCount:" + getTotalCount() +
                ";isbn:" + getIsbn() +
                "}";
    }
}
