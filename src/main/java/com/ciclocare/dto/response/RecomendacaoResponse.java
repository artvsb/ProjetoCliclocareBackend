package com.ciclocare.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecomendacaoResponse {

	private String titulo;

	private String descricao;

	private String categoria;

	private String fase;

	private String tempoLeitura;

	private String icone;

	private List<String> tags;
}