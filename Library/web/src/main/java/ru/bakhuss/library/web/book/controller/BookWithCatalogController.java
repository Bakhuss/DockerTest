package ru.bakhuss.library.web.book.controller;

import ru.bakhuss.library.web.catalog.view.CatalogView;
import ru.bakhuss.library.web.common.view.ResponseView;

/**
 * Контроллер для действий класса Book с классом Catalog
 */
public interface BookWithCatalogController {
    ResponseView addCatalog(String bookId, CatalogView catalogView);

    ResponseView removeCatalog(String bookId, CatalogView catalogView);

    ResponseView getAllCatalogs(String bookId);
}
