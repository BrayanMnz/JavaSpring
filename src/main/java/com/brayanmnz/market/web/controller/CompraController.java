package com.brayanmnz.market.web.controller;

import com.brayanmnz.market.domain.Product;
import com.brayanmnz.market.domain.Purchase;
import com.brayanmnz.market.domain.service.CompraService;
import com.brayanmnz.market.persistence.entity.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/compras")
public class CompraController {
    @Autowired
    private CompraService compraService;

    @GetMapping("/")
    public ResponseEntity<List<Purchase>> getAll() {
        return new ResponseEntity<>(compraService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/customer/{customer}")
    public ResponseEntity<List<Purchase>> getAll(@PathVariable("customer") String cust) {
        return compraService.getByCustomer(cust).map(
                purchases -> new ResponseEntity<>(purchases, HttpStatus.OK)
        ).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase){
        return new ResponseEntity<>(compraService.save(purchase), HttpStatus.CREATED);
    }
}
