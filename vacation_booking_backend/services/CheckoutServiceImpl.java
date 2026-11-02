package com.yliu78.vacation_booking_backend.services;

import com.yliu78.vacation_booking_backend.dao.CartRepository;
import com.yliu78.vacation_booking_backend.dao.CustomerRepository;
import com.yliu78.vacation_booking_backend.entities.Cart;
import com.yliu78.vacation_booking_backend.entities.CartItem;
import com.yliu78.vacation_booking_backend.entities.Customer;
import com.yliu78.vacation_booking_backend.entities.StatusType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;

    public CheckoutServiceImpl(CustomerRepository customerRepository,
                               CartRepository cartRepository) {
            this.customerRepository = customerRepository;
            this.cartRepository = cartRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        // retrieve the cart and customer info
        Cart cart = purchase.getCart();
        Customer incoming = purchase.getCustomer();
        Long incomingId = incoming.getId();

        Customer customer;

        if (incomingId != null && incomingId != 0L) {
            customer = customerRepository.findById(incomingId)
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.BAD_REQUEST, "Customer not found: " + incomingId));
        } else {
            // the DTO always sends id 0, so find the row the customer page already saved
            customer = customerRepository.findMatches(
                            incoming.getFirstName(), incoming.getLastName(), incoming.getAddress(),
                            incoming.getPostal_code(), incoming.getPhone())
                    .stream().findFirst()
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.BAD_REQUEST, "No saved customer matches the checkout details"));
        }

        // CartDto sends id 0, so clear ids to force inserts
        cart.setId(null);
        purchase.getCartItems().forEach(item -> item.setId(null));

        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);
        cart.setStatus(StatusType.ordered);

        purchase.getCartItems().forEach(cart::add);
        customer.add(cart);   // also replaces the nested customer in the cart JSON

        // persist the new cart (cascades to cart items); customer is already managed
        cartRepository.save(cart);

        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {

        // generate a random UUID number
        return UUID.randomUUID().toString();
    }
}
