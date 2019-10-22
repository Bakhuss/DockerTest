package ru.bakhuss.library.web.book.controller;

import ru.bakhuss.library.web.book.view.BookView;
import ru.bakhuss.library.web.common.view.ResponseView;

/**
 * Контроллер для Book
 */
public interface BookController {
    ResponseView addBook(BookView view);

    ResponseView getBook(String id);

    ResponseView updateBook(BookView view);

    ResponseView deleteBook(BookView view);
}
