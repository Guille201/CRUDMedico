package com.citasMedicas.api.repository;

import com.citasMedicas.api.models.Tratamiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {
}
