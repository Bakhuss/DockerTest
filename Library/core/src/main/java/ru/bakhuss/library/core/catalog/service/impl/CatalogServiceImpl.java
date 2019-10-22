package ru.bakhuss.library.core.catalog.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bakhuss.library.core.catalog.dao.CatalogDao;
import ru.bakhuss.library.core.catalog.model.Catalog;
import ru.bakhuss.library.core.catalog.service.CatalogService;

@Service
@Transactional
public class CatalogServiceImpl implements CatalogService {
    private final Logger log = LoggerFactory.getLogger(CatalogServiceImpl.class);

    private final CatalogDao catalogDao;

    @Autowired
    public CatalogServiceImpl(CatalogDao catalogDao) {
        this.catalogDao = catalogDao;
    }


    @Override
    public Long addCatalog(Catalog catalog) {
        Catalog newCatalog = catalogDao.save(catalog);
        log.info(newCatalog.toString());
        return newCatalog.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public Catalog getCatalog(Long id) {
        Catalog catalog = catalogDao.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        ("Catalog by id = " + id + " not found")
                ));
        log.info(catalog.toString());
        return catalog;
    }

    @Override
    public void updateCatalog(Catalog catalog) {
        Catalog updatedCatalog = catalogDao.save(catalog);
        log.info(updatedCatalog.toString());
    }

    @Override
    public void deleteCatalog(Long id) {
        if (!catalogDao.existsById(id))
            throw new RuntimeException("Not found catalog by id " + id);
        else {
            catalogDao.deleteById(id);
            log.info("Deleted catalog by id " + id);
        }
    }
}
