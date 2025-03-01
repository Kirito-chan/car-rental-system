package com.carrental.system.adapter.out.persistence;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageDefinitionUtil {

    private PageDefinitionUtil() {
    }

    public static Pageable toPageable(PageDefinition pageDefinition) {
        var sort = Sort.by(new Sort.Order(Sort.Direction.ASC, pageDefinition.propertyName(), Sort.NullHandling.NULLS_LAST));
        return PageRequest.of(pageDefinition.pageNumber() - 1, pageDefinition.pageSize(), sort);
    }
}
