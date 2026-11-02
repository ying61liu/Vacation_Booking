package com.yliu78.vacation_booking_backend.dao;

import com.yliu78.vacation_booking_backend.entities.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource
public interface DivisionRepository extends JpaRepository<Division, Long> {

}
