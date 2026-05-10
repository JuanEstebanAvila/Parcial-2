package com.triatlon.controller;

import com.triatlon.model.Atleta;
import com.triatlon.service.AtletaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/atletas")
@CrossOrigin("*")

public class AtletaController {

    @Autowired
    private AtletaService atletaService;

    // LISTAR TODOS
    @GetMapping
    public List<Atleta> obtenerTodos() {
        return atletaService.obtenerTodos();
    }

    // GUARDAR
    @PostMapping
    public Atleta guardar(@RequestBody Atleta atleta) {
        return atletaService.guardar(atleta);
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public Optional<Atleta> obtenerPorId(@PathVariable Long id) {
        return atletaService.obtenerPorId(id);
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        atletaService.eliminar(id);
    }
}
