package com.yliu78.vacation_booking_backend.dao;

import com.yliu78.vacation_booking_backend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<Customer, Long> {


    @RestResource(exported = false)
    @Query("""
           select c from Customer c
           where c.firstName = :firstName
             and c.lastName = :lastName
             and c.address = :address
             and c.postal_code = :postalCode
             and c.phone = :phone
           order by c.id desc
           """)
    List<Customer> findMatches(@Param("firstName") String firstName,
                               @Param("lastName") String lastName,
                               @Param("address") String address,
                               @Param("postalCode") String postalCode,
                               @Param("phone") String phone);

}
