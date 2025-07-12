package com.citasMedicas.api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tratamiento")
public class Tratamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con Consulta (un tratamiento pertenece a una consulta)
    @ManyToOne
    @JoinColumn(name = "consulta_id", nullable = false)
    private Consulta consulta;

    private String tipoDieta;
    private String tipoEjercicio;
    private String tipoMedicamento;
    private String tipoEstiloVida;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Consulta getConsulta() { return consulta; }
    public void setConsulta(Consulta consulta) { this.consulta = consulta; }

    public String getTipoDieta() { return tipoDieta; }
    public void setTipoDieta(String tipoDieta) { this.tipoDieta = tipoDieta; }

    public String getTipoEjercicio() { return tipoEjercicio; }
    public void setTipoEjercicio(String tipoEjercicio) { this.tipoEjercicio = tipoEjercicio; }

    public String getTipoMedicamento() { return tipoMedicamento; }
    public void setTipoMedicamento(String tipoMedicamento) { this.tipoMedicamento = tipoMedicamento; }

    public String getTipoEstiloVida() { return tipoEstiloVida; }
    public void setTipoEstiloVida(String tipoEstiloVida) { this.tipoEstiloVida = tipoEstiloVida; }
}
