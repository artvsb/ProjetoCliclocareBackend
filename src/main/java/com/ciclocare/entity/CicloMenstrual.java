package com.ciclocare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ciclos_menstruais")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CicloMenstrual {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private LocalDate dataInicio;

    @Column(nullable = false)
    private LocalDate dataFim;

    @Builder.Default
    @Column(nullable = false)
    private Integer duracaoCiclo = 28;

    @Builder.Default
    @Column(nullable = false)
    private Integer duracaoMenstruacao = 5;

    @Column(nullable = false)
    private LocalDate ultimaMenstruacao;

    @Column(nullable = false)
    private LocalDate proximaPrevisao;

    @Column(nullable = false)
    private LocalDate previsaoOvulacao;

    @Column(nullable = false)
    private LocalDate janelaFertilInicio;

    @Column(nullable = false)
    private LocalDate janelaFertilFim;

    @Column
    private String intensidadeFluxo;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime criadoEm;
}
