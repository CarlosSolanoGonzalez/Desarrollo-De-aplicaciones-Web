package com.tienda.repository;

import com.tienda.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query("select p from Producto p join fetch p.categoria where p.activo = true")
    List<Producto> findByActivoTrue();

    @Query("select p from Producto p join fetch p.categoria")
    List<Producto> findAllWithCategoria();
}
