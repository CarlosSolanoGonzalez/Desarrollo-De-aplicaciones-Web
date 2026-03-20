package com.tienda.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(unique = true, nullable = false, length = 30)
    private String username;
    @Column(nullable = false, length = 512)
    private String password;
    @Column(nullable = false, length = 20)
    private String nombre;
    @Column(nullable = false, length = 30)
    private String apellidos;
    @Column(nullable = false, length = 75)
    private String correo;
    @Column(nullable = false, length = 25)
    private String telefono;
    @Column(nullable = false, length = 1024)
    private String rutaImagen;
    private boolean activo;

    //Relación de muchos a muchos entre usuario y rol
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="usuario_rol", joinColumns = @JoinColumn(name="id_usuario"),
            inverseJoinColumns = @JoinColumn(name="id_rol"))
    private Set<Rol> roles = new HashSet<>();

}
