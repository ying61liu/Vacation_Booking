package com.yliu78.vacation_booking_backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="customers")
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private Long id;

    @NotNull
    @NotEmpty
    @Column(name="customer_first_name")
    private String firstName;

    @NotNull
    @NotEmpty
    @Column(name="customer_last_name")
    private String lastName;

    @NotNull
    @NotEmpty
    @Column(name="address")
    private String address;

    @NotNull
    @NotEmpty
    @Column(name="postal_code")
    private String postal_code;

    @NotNull
    @NotEmpty
    @Column(name="phone")
    private String phone;

    @CreationTimestamp
    @Column(name="create_date", updatable = false)
    private Date create_date;

    @UpdateTimestamp
    @Column(name="last_update")
    private Date last_update;

    @NotNull
    @ManyToOne
    @JoinColumn(name="division_id")
    private Division division;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Cart> carts = new HashSet<>();

    public void add(Cart cart) {
        if (cart != null) {
            if (carts == null) {
                carts = new HashSet<>();
            }

            carts.add(cart);
            cart.setCustomer(this);
        }
    }

}
