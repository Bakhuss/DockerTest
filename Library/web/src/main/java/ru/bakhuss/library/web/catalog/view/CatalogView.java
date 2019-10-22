package ru.bakhuss.library.web.catalog.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class CatalogView {

    public String id;

    public String bookId;

    public String bookName;

    public String isbn;

    public String description;

    public String totalCount;

    public String availableCount;


    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "{id:" + getId() +
                ";bookId:" + getBookId() +
                ";bookName:" + getBookName() +
                ";isbn:" + getIsbn() +
                ";description:" + getDescription() +
                ";totalCount:" + getTotalCount() +
                ";availableCount:" + getAvailableCount() +
                "}";
    }
}
