package ru.bakhuss.library.web.catalog.controller.impl;

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
import ru.bakhuss.library.core.catalog.model.Catalog;
import ru.bakhuss.library.core.catalog.service.CatalogService;
import ru.bakhuss.library.web.catalog.controller.CatalogController;
import ru.bakhuss.library.web.catalog.view.CatalogView;
import ru.bakhuss.library.web.common.view.ResponseView;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static ru.bakhuss.library.web.catalog.util.converter.CatalogConverterUtil.catalogToCatalogView;
import static ru.bakhuss.library.web.catalog.util.converter.CatalogConverterUtil.catalogViewToCatalog;
import static ru.bakhuss.library.web.common.Util.parseIntegerFromString;
import static ru.bakhuss.library.web.common.Util.parseLongFromString;

@RestController
@RequestMapping(value = "/api/catalog", produces = APPLICATION_JSON_VALUE)
@Api(value = "CatalogControllerApi", produces = APPLICATION_JSON_VALUE)
public class CatalogControllerImpl implements CatalogController {
    private final Logger log = LoggerFactory.getLogger(CatalogControllerImpl.class);

    private final CatalogService catalogService;

    @Autowired
    public CatalogControllerImpl(CatalogService catalogService) {
        this.catalogService = catalogService;
    }


    @Override
    @PostMapping(value = "/save")
    public ResponseView addCatalog(CatalogView view) {
        log.info(view.toString());
        Catalog catalog = new Catalog();
        catalog.setIsbn(view.getIsbn());
        catalog.setDescription(view.getDescription());
        catalog.setTotalCount(parseIntegerFromString(view.getTotalCount()));
        catalogService.addCatalog(catalog);
        return new ResponseView(true);
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseView getCatalog(@PathVariable String id) {
        Long catalogId = parseLongFromString(id);
        Catalog catalog = catalogService.getCatalog(catalogId);
        CatalogView view = catalogToCatalogView(catalog);
        log.info(view.toString());
        return new ResponseView(view);
    }

    @Override
    @PostMapping(value = "/update")
    public ResponseView updateCatalog(@RequestBody CatalogView view) {
        Long catalogId = parseLongFromString(view.getId());
        Catalog catalog = catalogService.getCatalog(catalogId);
        catalog.updateState(catalogViewToCatalog(view));
        catalogService.updateCatalog(catalog);
        return new ResponseView(true);
    }

    @Override
    @DeleteMapping(value = "/delete")
    public ResponseView deleteCatalog(@RequestBody CatalogView view) {
        Long catalogId = parseLongFromString(view.getId());
        catalogService.deleteCatalog(catalogId);
        return new ResponseView(true);
    }
}
