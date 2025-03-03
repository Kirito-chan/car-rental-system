package com.carrental.system.adapter.out.persistence;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PageDefinitionUtil {

    private PageDefinitionUtil() {
    }

    public static Pageable toPageable(PageDefinition pageDefinition) {
        return PageRequest.of(pageDefinition.pageNumber() - 1, pageDefinition.pageSize());
    }
}
