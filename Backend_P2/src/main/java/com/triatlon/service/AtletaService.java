package com.triatlon.service;

import com.triatlon.model.Atleta;
import com.triatlon.repository.AtletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AtletaService {

    @Autowired
    private AtletaRepository atletaRepository;

    // LISTAR
    public List<Atleta> obtenerTodos() {
        return atletaRepository.findAll();
    }

    // GUARDAR
    public Atleta guardar(Atleta atleta) {
        return atletaRepository.save(atleta);
    }

    // BUSCAR POR ID
    public Optional<Atleta> obtenerPorId(Long id) {
        return atletaRepository.findById(id);
    }

    // ELIMINAR
    public void eliminar(Long id) {
        atletaRepository.deleteById(id);
    }
}
