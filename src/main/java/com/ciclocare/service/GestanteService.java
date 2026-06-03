package com.ciclocare.service;

import com.ciclocare.dto.response.ModoGestanteResponse;
import com.ciclocare.entity.CicloMenstrual;
import com.ciclocare.entity.Usuario;
import com.ciclocare.exception.ResourceNotFoundException;
import com.ciclocare.repository.CicloMenstrualRepository;
import com.ciclocare.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GestanteService {

	@Autowired
	private final UsuarioRepository usuarioRepository;

	@Autowired
	private final CicloMenstrualRepository cicloMenstrualRepository;

	public ModoGestanteResponse exibirModoGestante(UUID idUsuaria) {
		Usuario usuaria = usuarioRepository.findById(idUsuaria)
				.orElseThrow(() -> new ResourceNotFoundException("Usuária não encontrada"));

		List<CicloMenstrual> ultimosCiclos =
				cicloMenstrualRepository.findTop3ByUsuarioOrderByDataInicioDesc(usuaria);

		if (ultimosCiclos.isEmpty()) {
			throw new ResourceNotFoundException("Nenhum ciclo encontrado.");
		}

		CicloMenstrual cicloAtual = ultimosCiclos.get(0);

		LocalDate ultimaMenstruacao = cicloAtual.getDataInicio();
		LocalDate hoje = LocalDate.now();

		long diasGravidez = ChronoUnit.DAYS.between(ultimaMenstruacao, hoje);

		int semanasCompletas = (int) diasGravidez / 7;
		int meses = semanasCompletas / 4;
		int semanasMes = semanasCompletas % 4;
		int diasSemana = (int) diasGravidez % 7;

		int semanaAtual = semanasCompletas + 1;

		int diasGravidezTotal = 280;
		int diasRestantes = Math.max(0, diasGravidezTotal - (int) diasGravidez);

		int semanasRestantes = diasRestantes / 7;
		int diasRestantesSemana = diasRestantes % 7;

		LocalDate previsaoParto = ultimaMenstruacao.plusDays(280);

		String textoMeses = meses == 1
				? "1 mês"
				: meses + " meses";

		String textoSemanas = semanasMes == 1
				? "1 semana"
				: semanasMes + " semanas";
		String mensagemSecundaria = textoMeses + " e " + textoSemanas;

		return ModoGestanteResponse.builder()
				.semanaAtual(semanaAtual)
				.semanasCompletas(semanasCompletas)
				.diasSemana(diasSemana)
				.diasGravidez((int) diasGravidez)
				.diasRestantes(diasRestantes)
				.previsaoParto(previsaoParto)
				.mensagemPrincipal("Você está na " + semanaAtual + "ª semana de gestação")
				.meses(meses)
				.semanasMes(semanasMes)
				.semanasRestantes(semanasRestantes)
				.diasRestantesSemana(diasRestantesSemana)
				.mensagemSecundaria(mensagemSecundaria)
				.faseGestacional(identificarFaseGestacional(semanaAtual))
				.mensagemFaseGestacao(gerarMensagemGestacao(semanaAtual))
				.build();
	}

	private String gerarMensagemGestacao(Integer semanaAtual) {
		if (semanaAtual == null || semanaAtual <= 0) {
			return "Acompanhe sua gestação com orientação profissional.";
		}

		if (semanaAtual <= 13) {
			return "Você está no primeiro trimestre. É comum sentir mais sono, náuseas ou cansaço. Priorize descanso, hidratação e acompanhamento pré-natal.";
		}

		if (semanaAtual <= 27) {
			return "Você está no segundo trimestre. Muitas gestantes percebem mais disposição nessa fase. Continue acompanhando consultas, exames e sinais do corpo.";
		}

		if (semanaAtual <= 40) {
			return "Você está no terceiro trimestre. O corpo se prepara para o parto. Observe movimentos do bebê, desconfortos e mantenha o acompanhamento pré-natal.";
		}

		return "Sua gestação passou de 40 semanas. Procure orientação da equipe de saúde para acompanhamento adequado.";
	}

	private String identificarFaseGestacional(Integer semanaAtual) {
		if (semanaAtual == null || semanaAtual <= 0) {
			return "Fase gestacional indisponível";
		}

		if (semanaAtual <= 13) {
			return "Primeiro trimestre";
		}

		if (semanaAtual <= 27) {
			return "Segundo trimestre";
		}

		if (semanaAtual <= 40) {
			return "Terceiro trimestre";
		}

		return "Gestação pós-termo";
	}
}
