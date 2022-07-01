package com.brayanmnz.market.domain.service;

import com.brayanmnz.market.domain.Purchase;
import com.brayanmnz.market.persistence.CompraRepository;
import com.brayanmnz.market.persistence.entity.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompraService {

    @Autowired
    CompraRepository compraRepository;


    public List<Purchase> getAll() {
        return compraRepository.getAll();
    }

    public Optional<List<Purchase>> getByCustomer(String customerId) {
        return compraRepository.getByCustomer(customerId);
    }

    public Purchase save(Purchase purchase) {
        return compraRepository.save(purchase);
    }

}
