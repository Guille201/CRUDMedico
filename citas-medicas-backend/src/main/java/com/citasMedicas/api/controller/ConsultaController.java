package com.citasMedicas.api.controller;

import com.citasMedicas.api.models.Consulta;
import com.citasMedicas.api.repository.ConsultaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

    private final ConsultaRepository consultaRepository;

    public ConsultaController(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    @GetMapping
    public List<Consulta> getConsultas() {
        return consultaRepository.findAll();
    }

    @PostMapping
    public Consulta crearConsulta(@RequestBody Consulta consulta) {
        return consultaRepository.save(consulta);
    }
}
