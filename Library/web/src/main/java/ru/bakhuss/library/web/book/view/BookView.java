package ru.bakhuss.library.web.book.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ru.bakhuss.library.core.book.model.Book;

import java.util.function.Function;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class BookView {

    public String id;

    public String name;

    public static Function<Book, BookView> getFuncBookToView() {
        return b -> {
            BookView bV = new BookView();
            bV.id = b.getId().toString();
            bV.name = b.getName();
            return bV;
        };
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
