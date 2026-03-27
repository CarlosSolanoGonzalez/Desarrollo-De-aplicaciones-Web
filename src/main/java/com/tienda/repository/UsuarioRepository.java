package com.tienda.repository;

import com.tienda.domain.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    //Se crea una consulta derivada
    public Optional<Usuario> findByUsernameAndActivoTrue(String username);

    //Se utiliza para el crud de usuarios... el listado...
    public List<Usuario> findByActivoTrue();

    //Se utiliza al modificar un usuario...
    public Optional<Usuario> findByUsername(String username);

    public Optional<Usuario> findByUsernameAndPassword(String username, String password);

    public Optional<Usuario> findByUsernameOrCorreo(String username, String correo);

    public boolean existsByUsernameOrCorreo(String username, String correo);
}
