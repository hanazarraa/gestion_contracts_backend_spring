/*package com.luv2code.ecommerce.service;

import com.luv2code.ecommerce.entity.Invoice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
 
import java.util.List;

public class invoiceService {
    /**
     * get element using Criteria.
     *
     * @param spec    *
     * @param headers pagination data
     * @param sort    sort criteria
     * @return retrieve elements with pagination
     */
   /* public PagingResponse get(Specification<Invoice> spec, HttpHeaders headers, Sort sort) {
        if (isRequestPaged(headers)) {
            return get(spec, buildPageRequest(headers, sort));
        } else {
            final List<Invoice> entities = get(spec, sort);
            return new PagingResponce((long) entities.size(), 0L, 0L, 0L, 0L, entities);
        }
    }
}*/
