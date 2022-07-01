package com.brayanmnz.market.persistence;

import com.brayanmnz.market.domain.Product;
import com.brayanmnz.market.domain.repository.ProductRepository;
import com.brayanmnz.market.persistence.crud.ProductoCrudRepository;
import com.brayanmnz.market.persistence.entity.Producto;
import com.brayanmnz.market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository //Indica a Spring que esta clase interactúa con la db.
public class ProductoRepository implements ProductRepository {
    @Autowired
    private ProductoCrudRepository productoCrudRepository;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> getAll() {
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return productMapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
       // List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderbyNombreAsc(categoryId);
        return Optional.of(productMapper.toProducts(null));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int qty) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(qty, true);
        return productos.map(prods -> productMapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository.findById(productId).map(prod -> productMapper.toProduct(prod));
    }

    @Override
    public Product save(Product product) {
        Producto prod = productMapper.toProducto(product);
        return productMapper.toProduct(productoCrudRepository.save(prod));
    }

    @Override
    public void delete(int idProducto) {
        productoCrudRepository.deleteById(idProducto);
    }

}
