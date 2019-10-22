package ru.bakhuss.library.web.book.controller.impl;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bakhuss.library.core.book.model.Book;
import ru.bakhuss.library.core.book.service.BookService;
import ru.bakhuss.library.web.book.controller.BookController;
import ru.bakhuss.library.web.book.view.BookView;
import ru.bakhuss.library.web.common.view.ResponseView;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static ru.bakhuss.library.web.common.Util.parseLongFromString;

@RestController
@RequestMapping(value = "/api/book", produces = APPLICATION_JSON_VALUE)
@Api(value = "BookControllerApi", produces = APPLICATION_JSON_VALUE)
public class BookControllerImpl implements BookController {
    private final Logger log = LoggerFactory.getLogger(BookControllerImpl.class);

    private final BookService bookService;

    @Autowired
    public BookControllerImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    @PostMapping(value = "/save")
    public ResponseView addBook(@RequestBody BookView view) {
        log.info(view.toString());
        Book book = new Book();
        book.setName(view.getName());
        bookService.addBook(book);
        return new ResponseView(true);
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseView getBook(@PathVariable String id) {
        Long longId = parseLongFromString(id);
        Book book = bookService.getBook(longId);
        BookView view = new BookView();
        view.setId(book.getId().toString());
        view.setName(book.getName());
        log.info(view.toString());
        return new ResponseView(view);
    }

    @Override
    @PostMapping(value = "/update")
    public ResponseView updateBook(@RequestBody BookView view) {
        log.info(view.toString());
        Long id = parseLongFromString(view.getId());
        Book book = bookService.getBook(id);
        book.setName(view.getName());
        bookService.updateBook(book);
        return new ResponseView(true);
    }

    @Override
    @DeleteMapping(value = "/delete")
    public ResponseView deleteBook(@RequestBody BookView view) {
        Long longId = parseLongFromString(view.getId());
        bookService.deleteBook(longId);
        return new ResponseView(true);
    }
}
