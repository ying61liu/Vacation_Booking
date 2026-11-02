package com.yliu78.vacation_booking_backend.services;

import com.yliu78.vacation_booking_backend.entities.Cart;
import com.yliu78.vacation_booking_backend.entities.CartItem;
import com.yliu78.vacation_booking_backend.entities.Customer;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Cart cart;
    private Set<CartItem> cartItems = new HashSet<>();

}
