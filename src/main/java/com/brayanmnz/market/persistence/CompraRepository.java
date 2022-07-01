package com.brayanmnz.market.persistence;

import com.brayanmnz.market.domain.Purchase;
import com.brayanmnz.market.domain.repository.PurchaseRepository;
import com.brayanmnz.market.persistence.crud.CompraCrudRepository;
import com.brayanmnz.market.persistence.entity.Compra;
import com.brayanmnz.market.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {
    @Autowired
    private CompraCrudRepository compraCrudRepository;
    @Autowired
    private PurchaseMapper purchaseMapper;

    @Override
    public List<Purchase> getAll() {
        return purchaseMapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByCustomer(String customerId) {
        return compraCrudRepository.findByIdCliente(customerId)
                .map(compras -> purchaseMapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = purchaseMapper.toCompra(purchase);
        compra.getProductos().forEach(producto -> producto.setCompra(compra));

        return purchaseMapper.toPurchase(compraCrudRepository.save(compra));
    }
}
