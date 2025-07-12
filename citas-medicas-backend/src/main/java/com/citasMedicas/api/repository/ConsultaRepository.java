package com.citasMedicas.api.repository;

import com.citasMedicas.api.models.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}
