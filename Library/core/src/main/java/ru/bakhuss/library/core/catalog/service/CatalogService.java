package ru.bakhuss.library.core.catalog.service;

import ru.bakhuss.library.core.catalog.model.Catalog;

public interface CatalogService {
    Long addCatalog(Catalog catalog);

    Catalog getCatalog(Long id);

    void updateCatalog(Catalog catalog);

    void deleteCatalog(Long id);
}
