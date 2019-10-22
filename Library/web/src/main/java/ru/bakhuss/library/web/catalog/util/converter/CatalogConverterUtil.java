package ru.bakhuss.library.web.catalog.util.converter;

import ru.bakhuss.library.core.catalog.model.Catalog;
import ru.bakhuss.library.web.catalog.view.CatalogView;

import static ru.bakhuss.library.web.common.Util.parseIntegerFromString;

public class CatalogConverterUtil {
    public static CatalogView catalogToCatalogView(Catalog catalog) {
        CatalogView cV = new CatalogView();
        cV.setId(catalog.getId().toString());
        cV.setBookId(catalog.getBook().getId().toString());
        cV.setBookName(catalog.getBook().getName());
        cV.setIsbn(catalog.getIsbn());
        cV.setDescription(catalog.getDescription());
        cV.setTotalCount(catalog.getTotalCount().toString());
        return cV;
    }

    public static Catalog catalogViewToCatalog(CatalogView view) {
        Catalog catalog = new Catalog();
        catalog.setIsbn(view.getIsbn());
        catalog.setDescription(view.getDescription());
        catalog.setTotalCount(parseIntegerFromString(view.getTotalCount()));
        return catalog;
    }
}
