package com.example.demo.controller;

import com.example.demo.model.Product;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private static final Random R = new SecureRandom();
    private static final String[] NAMES = {
            "Apple", "Banana", "Cherry", "Date", "Elderberry",
            "Fig", "Grape", "Honeydew", "Kiwi", "Lemon"
    };

    @GetMapping
    public List<Product> randomProducts(@RequestParam(defaultValue = "5") int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> randomProduct(null))
                .toList();
    }

    @GetMapping("/{id}")
    public Product randomProduct(@PathVariable(required = false) Long id) {
        long randomId = (id != null ? id : R.nextLong(1, 1_000_000));
        String name = NAMES[R.nextInt(NAMES.length)];
        double price = Math.round((10 + R.nextDouble(990)) * 100) / 100.0; // 10–1000 ₽
        return new Product(randomId, name, price);
    }
}
