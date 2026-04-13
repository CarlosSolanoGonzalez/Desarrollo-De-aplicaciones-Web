package com.tienda.service;

import com.tienda.domain.Ruta;
import com.tienda.repository.RutaRepository;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RutaService {

    private final RutaRepository rutaRepository;

    public RutaService(RutaRepository rutaRepository) {
        this.rutaRepository = rutaRepository;
    }

    @Transactional(readOnly = true)
    public List<Ruta> getRutas() {
        var lista = rutaRepository.findAll();
        return lista;
    }

    @Transactional(readOnly = true)
    public Ruta getRuta(Integer idRuta) {
        return rutaRepository.findById(idRuta).orElseThrow(
            () -> new NoSuchElementException("Ruta con ID " + idRuta + " no encontrada."));
    }

    @Transactional
    public void save(Ruta ruta) {
        rutaRepository.save(ruta);
    }

    @Transactional
    public void delete(Integer idRuta) {
        if (!rutaRepository.existsById(idRuta)) {
            throw new IllegalArgumentException("La Ruta con ID " + idRuta + " no existe.");
        }
        try {
            rutaRepository.deleteById(idRuta);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException("No se puede eliminar la ruta. Tiene datos asociados.", e);
        }
    }
}
