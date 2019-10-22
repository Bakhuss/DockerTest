package ru.bakhuss.library.web.book.controller;

import ru.bakhuss.library.web.common.view.ResponseView;
import ru.bakhuss.library.web.person.view.PersonView;

/**
 * Контроллер для действий класса Book с писателем (class Person)
 */
public interface BookWithWriterController {
    ResponseView addWriter(String bookId, PersonView personView);

    ResponseView removeWriter(String bookId, PersonView personView);

    ResponseView getAllWriters(String bookId);
}
