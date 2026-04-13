package com.tienda.service;

import com.tienda.domain.Rol;
import com.tienda.repository.RolRepository;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RolService {

    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Transactional(readOnly = true)
    public List<Rol> getRoles() {
        var lista = rolRepository.findAll();
        return lista;
    }

    @Transactional(readOnly = true)
    public Rol getRol(Integer idRol) {
        return rolRepository.findById(idRol).orElseThrow(
            () -> new NoSuchElementException("Rol con ID " + idRol + " no encontrado."));
    }

    @Transactional
    public void save(Rol rol) {
        rolRepository.save(rol);
    }

    @Transactional
    public void delete(Integer idRol) {
        if (!rolRepository.existsById(idRol)) {
            throw new IllegalArgumentException("El Rol con ID " + idRol + " no existe.");
        }
        try {
            rolRepository.deleteById(idRol);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException("No se puede eliminar el rol. Tiene datos asociados.", e);
        }
    }
}
