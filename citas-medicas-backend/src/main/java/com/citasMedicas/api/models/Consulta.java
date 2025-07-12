package com.citasMedicas.api.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con User (muchas consultas por usuario)
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "is_first")
    private Boolean isFirst;

    private String exploracion;
    private String pruebasExtra;
    private String juicioClinico;
    private String tratamiento;

    private LocalDateTime fecha;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Boolean getIsFirst() { return isFirst; }
    public void setIsFirst(Boolean isFirst) { this.isFirst = isFirst; }

    public String getExploracion() { return exploracion; }
    public void setExploracion(String exploracion) { this.exploracion = exploracion; }

    public String getPruebasExtra() { return pruebasExtra; }
    public void setPruebasExtra(String pruebasExtra) { this.pruebasExtra = pruebasExtra; }

    public String getJuicioClinico() { return juicioClinico; }
    public void setJuicioClinico(String juicioClinico) { this.juicioClinico = juicioClinico; }

    public String getTratamiento() { return tratamiento; }
    public void setTratamiento(String tratamiento) { this.tratamiento = tratamiento; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
}
