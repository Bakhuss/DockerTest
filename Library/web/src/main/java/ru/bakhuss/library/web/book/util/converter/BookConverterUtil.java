package ru.bakhuss.library.web.book.util.converter;

import ru.bakhuss.library.core.book.model.Book;
import ru.bakhuss.library.web.book.view.BookView;

public class BookConverterUtil {
    public static BookView bookToBookView(Book book, boolean withId) {
        BookView bV = new BookView();
        if (withId) bV.setId(book.getId().toString());
        bV.setName(book.getName());
        return bV;
    }
}
