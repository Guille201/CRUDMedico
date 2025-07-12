package com.citasMedicas.api.controller;

import com.citasMedicas.api.models.Tratamiento;
import com.citasMedicas.api.repository.TratamientoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/tratamientos")
public class TratamientoController {

    private final TratamientoRepository tratamientoRepository;

    public TratamientoController(TratamientoRepository tratamientoRepository) {
        this.tratamientoRepository = tratamientoRepository;
    }

    @GetMapping
    public List<Tratamiento> getTratamientos() {
        return tratamientoRepository.findAll();
    }

    @PostMapping
    public Tratamiento crearTratamiento(@RequestBody Tratamiento tratamiento) {
        return tratamientoRepository.save(tratamiento);
    }
    @PutMapping("/{id}")
    public Tratamiento actualizarTratamiento(@PathVariable Long id, @RequestBody Tratamiento actualizado) {
        return tratamientoRepository.findById(id)
            .map(tratamiento -> {
                tratamiento.setTipoDieta(actualizado.getTipoDieta());
                tratamiento.setTipoEjercicio(actualizado.getTipoEjercicio());
                tratamiento.setTipoMedicamento(actualizado.getTipoMedicamento());
                tratamiento.setTipoEstiloVida(actualizado.getTipoEstiloVida());
                return tratamientoRepository.save(tratamiento);
            })
            .orElseThrow(() -> new RuntimeException("Tratamiento no encontrado con ID: " + id));
    }

}
