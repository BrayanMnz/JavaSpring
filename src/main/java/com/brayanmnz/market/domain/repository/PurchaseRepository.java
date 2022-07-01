package com.brayanmnz.market.domain.repository;

import com.brayanmnz.market.domain.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {
    List<Purchase> getAll();
    Optional<List<Purchase>> getByCustomer(String customerId);
    Purchase save(Purchase purchase);

}
