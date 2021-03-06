package com.mapringg.bab.controllers;

import com.mapringg.bab.models.Menu;
import com.mapringg.bab.services.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.*;

/**
 * @author mapring
 */
@RestController
@RequestMapping("/api/coffee")
public class CoffeeController {

    private final CoffeeService coffeeService;

    @Autowired
    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @PostMapping(path = "/add")
    public Menu add(@RequestBody String json) {
        return coffeeService.add(json);
    }

    @GetMapping
    @CachePut("coffees")
    public Iterable<Menu> findAll() {
        return coffeeService.list();
    }

    @PutMapping(path = "/update")
    public Menu update(@RequestBody String json) {
        return coffeeService.update(json);
    }
}
