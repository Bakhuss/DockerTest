package ru.bakhuss.library.web.person.controller;

import ru.bakhuss.library.web.book.view.BookView;
import ru.bakhuss.library.web.common.view.ResponseView;

/**
 * Контроллер для действий класса Person с написанной книгой (class Book)
 */
public interface PersonWithWrittenBookController {
    ResponseView addWrittenBook(String personId, BookView bookView);

    ResponseView removeWrittenBook(String personId, BookView bookView);

    ResponseView getAllWrittenBooks(String personId);
}
