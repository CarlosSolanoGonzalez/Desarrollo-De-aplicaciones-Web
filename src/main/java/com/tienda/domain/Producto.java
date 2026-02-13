package com.tienda.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "producto")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer idProducto;

    @Column(unique = true, nullable = false, length = 50)
    @NotNull
    @Size(max = 50)
    private String descripcion;

    @Column(columnDefinition = "TEXT")
    private String detalle;

    @Column(precision = 12, scale = 2)
    @NotNull(message = "Favor insertar un precio.")
    @DecimalMin(value = "0.00", inclusive = true, message = "No se acceptan precios negativos.")
    private BigDecimal precio;

    @NotNull(message = "Debe agregar la cantidad de productos.")
    @Min(value = 0, message = "La cantidad tiene que ser mayor a 0")
    private Integer existencias;

    @Column(length = 1024)
    @Size(max = 1024)
    private String rutaImagen;

    private boolean activo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Categoria categoria;
}
