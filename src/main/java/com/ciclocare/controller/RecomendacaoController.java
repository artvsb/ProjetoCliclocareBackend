package com.ciclocare.controller;

import com.ciclocare.dto.response.ApiResponse;
import com.ciclocare.dto.response.RecomendacaoResponse;
import com.ciclocare.service.RecomendacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/recomendacoes")
@RequiredArgsConstructor
public class RecomendacaoController {

	private final RecomendacaoService recomendacaoService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<ApiResponse> listarRecomendacoes(
			@RequestParam UUID usuarioId
	) {
		var recomendacoes =
				recomendacaoService.listarRecomendacoes(usuarioId);

		return ResponseEntity.ok(
				ApiResponse.sucesso("Recomendações carregadas", recomendacoes)
		);
	}

	@GetMapping("/gestante")
	@ResponseStatus(HttpStatus.OK)
	public List<RecomendacaoResponse> listarRecomencoesGestante(@RequestParam UUID id){
		return recomendacaoService.listarRecomendacoesGestante(id);
	}
}