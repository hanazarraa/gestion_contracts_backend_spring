package com.luv2code.ecommerce.dao;

import com.luv2code.ecommerce.entity.Contract;
import com.luv2code.ecommerce.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "invoices",path = "invoices")

public interface InvoiceRepository extends JpaRepository<Invoice,Long>, JpaSpecificationExecutor<Invoice> {
    List<Invoice> findByAmount(float amount);
    @Query(value = "SELECT * FROM invoices i WHERE i.customer_id = ?1 ",nativeQuery = true)
    List<Invoice> findByCustomerid(long customer_id);

}
