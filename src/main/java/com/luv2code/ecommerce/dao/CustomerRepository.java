package com.luv2code.ecommerce.dao;

import com.luv2code.ecommerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

public interface CustomerRepository  extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
    @Query(value = "SELECT * FROM Customer c order by c.birth_date desc",nativeQuery = true)
    List<Customer> findAll();

    @Query(value = "SELECT * FROM Customer c WHERE  c.fname like %?1% order by c.birth_date desc ",nativeQuery = true)
    List<Customer> findByFname(String fname);
    @Query(value = "SELECT * FROM Customer c WHERE  c.cin like %?1% order by c.birth_date desc",nativeQuery = true)
    List<Customer> findByCin(String cin);

    @Query(value = "SELECT * FROM Customer c WHERE  c.lastname like %?1% order by c.birth_date desc",nativeQuery = true)
    List<Customer> findByLastname (String lastname);

    @Query(value = "SELECT * FROM Customer c WHERE c.fname like %?1% and c.lastname like %?2% and c.cin like %?3% order by c.birth_date desc",nativeQuery = true)
    List<Customer> findByLastnameOrFnameOrCin(@Param("fname") String fname,
                                   @Param("lastname") String lastname,
                                    @Param("cin") String cin );
    @Query(value = "SELECT * FROM Customer c WHERE c.fname like %?1% and c.lastname like %?2% order by c.birth_date desc",nativeQuery = true)

    List<Customer> findByLastnameOrFname(@Param("fname") String fname,
                                              @Param("lastname") String lastname
                                              );

    @Query(value = "SELECT * FROM Customer c WHERE c.lastname like %?1% and c.cin like %?2% order by c.birth_date desc",nativeQuery = true)

    List<Customer> findByLastnameAndCin(@Param("lastname") String lastname,
                                         @Param("cin") String cin
    );



}
