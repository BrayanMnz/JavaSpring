package com.brayanmnz.market.persistence.crud;

import com.brayanmnz.market.persistence.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    //Query nativo
    //@Query(value = "SELECT * FROM productos where id_categoria = ? ",nativeQuery = true);
//    List<Producto> findByIdCategoriaOrderbyNombreAsc(int idCategoria); //QueryMethod

    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado); //QueryMethod

}
