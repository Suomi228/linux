package com.example.demo.controller;

import com.example.demo.model.Customer;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private static final Random R = new SecureRandom();
    private static final String[] NAMES = {
            "Alex Johnson", "Maria Garcia", "James Smith", "Emma Wilson", "David Brown",
            "Sarah Davis", "Michael Miller", "Olivia Martinez", "Robert Anderson", "Sophia Taylor"
    };

    @GetMapping
    public List<Customer> randomCustomers(@RequestParam(defaultValue = "5") int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> randomCustomer(null))
                .toList();
    }

    @GetMapping("/{id}")
    public Customer randomCustomer(@PathVariable(required = false) Long id) {
        long randomId = (id != null ? id : R.nextLong(1, 1_000_000));
        String fullName = NAMES[R.nextInt(NAMES.length)];
        return new Customer(randomId, fullName);
    }
}