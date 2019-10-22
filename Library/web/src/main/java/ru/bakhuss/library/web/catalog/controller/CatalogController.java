package ru.bakhuss.library.web.catalog.controller;

import ru.bakhuss.library.web.catalog.view.CatalogView;
import ru.bakhuss.library.web.common.view.ResponseView;

public interface CatalogController {
    ResponseView addCatalog(CatalogView view);

    ResponseView getCatalog(String id);

    ResponseView updateCatalog(CatalogView view);

    ResponseView deleteCatalog(CatalogView view);
}
