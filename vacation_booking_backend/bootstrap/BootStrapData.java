package com.yliu78.vacation_booking_backend.bootstrap;

import com.yliu78.vacation_booking_backend.dao.CustomerRepository;
import com.yliu78.vacation_booking_backend.dao.DivisionRepository;
import com.yliu78.vacation_booking_backend.entities.Customer;
import com.yliu78.vacation_booking_backend.entities.Division;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class BootStrapData implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final DivisionRepository divisionRepository;

    public BootStrapData(CustomerRepository customerRepository,
                         DivisionRepository divisionRepository) {
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
    }

    @Override
    public void run(String... args) {

        List<Division> divisions = divisionRepository.findAll();
        if (divisions.isEmpty()) {
            System.out.println("No divisions found, skipping sample customers");
            return;
        }

        addIfMissing("Alice", "Johnson", "12 Maple Street", "75001", "555-0101", divisions.get(0));
        addIfMissing("Brian", "Smith", "88 Oak Avenue", "75002", "555-0102", divisions.get(0));
        addIfMissing("Carla", "Nguyen", "301 Pine Road", "75003", "555-0103", divisions.get(divisions.size() > 1 ? 1 : 0));
        addIfMissing("David", "Lopez", "7 Cedar Lane", "75004", "555-0104", divisions.get(divisions.size() > 1 ? 1 : 0));
        addIfMissing("Emma", "Brown", "45 Birch Court", "75005", "555-0105", divisions.get(divisions.size() > 2 ? 2 : 0));
    }

    private void addIfMissing(String first, String last, String address,
                              String postal, String phone, Division division) {

        // reuse the lookup you already wrote for checkout
        boolean exists = !customerRepository
                .findMatches(first, last, address, postal, phone).isEmpty();
        if (exists) {
            return;
        }

        Customer c = new Customer();
        c.setFirstName(first);
        c.setLastName(last);
        c.setAddress(address);
        c.setPostal_code(postal);
        c.setPhone(phone);
        c.setDivision(division);
        c.setCreate_date(new Date());
        c.setLast_update(new Date());
        customerRepository.save(c);
    }
}