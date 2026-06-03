package com.ciclocare.service;

import com.ciclocare.dto.response.RecomendacaoResponse;
import com.ciclocare.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecomendacaoService {

	private final UsuarioService usuarioService;
	private final GestanteService gestanteService;

	public List<RecomendacaoResponse> listarRecomendacoes(UUID usuarioId) {
		Usuario usuario = usuarioService.buscarEntidadePorId(usuarioId);

		if (Boolean.TRUE.equals(usuario.getModoGestante())) {
			return listarRecomendacoesGestante(usuarioId);
		}

		return listarRecomendacoesCiclo(usuarioId);
	}

	public List<RecomendacaoResponse> listarRecomendacoesGestante(UUID usuarioId) {
		var dadosGestante = gestanteService.exibirModoGestante(usuarioId);

		Integer semanaAtual = dadosGestante.getSemanaAtual();

		if (semanaAtual == null) {
			return recomendacoesPrimeiroTrimestre();
		}

		if (semanaAtual <= 13) {
			return recomendacoesPrimeiroTrimestre();
		}

		if (semanaAtual <= 27) {
			return recomendacoesSegundoTrimestre();
		}

		return recomendacoesTerceiroTrimestre();
	}

	private List<RecomendacaoResponse> recomendacoesPrimeiroTrimestre() {
		return List.of(
				RecomendacaoResponse.builder()
						.titulo("Priorize hidratação ao longo do dia")
						.descricao("Durante o primeiro trimestre, náuseas e cansaço podem ser comuns. Beber água em pequenos volumes ao longo do dia pode ajudar no bem-estar.")
						.categoria("alimentacao")
						.fase("PRIMEIRO_TRIMESTRE")
						.tempoLeitura("2 min")
						.icone("fa-solid fa-droplet")
						.tags(List.of("hidratação", "náuseas", "bem-estar"))
						.build(),

				RecomendacaoResponse.builder()
						.titulo("Faça refeições menores e mais frequentes")
						.descricao("Comer porções menores ao longo do dia pode ajudar a reduzir enjoo, fraqueza e desconforto gástrico.")
						.categoria("alimentacao")
						.fase("PRIMEIRO_TRIMESTRE")
						.tempoLeitura("3 min")
						.icone("fa-solid fa-bowl-food")
						.tags(List.of("alimentação", "enjoo", "energia"))
						.build(),

				RecomendacaoResponse.builder()
						.titulo("Inicie ou mantenha o pré-natal")
						.descricao("O acompanhamento pré-natal é essencial para orientar exames, suplementação e cuidados adequados desde o início da gestação.")
						.categoria("cuidados")
						.fase("PRIMEIRO_TRIMESTRE")
						.tempoLeitura("3 min")
						.icone("fa-solid fa-user-doctor")
						.tags(List.of("pré-natal", "exames", "acompanhamento"))
						.build(),

				RecomendacaoResponse.builder()
						.titulo("Respeite o cansaço do corpo")
						.descricao("Sono e fadiga são frequentes nessa fase. Sempre que possível, priorize pausas, descanso e uma rotina de sono mais regular.")
						.categoria("bem-estar")
						.fase("PRIMEIRO_TRIMESTRE")
						.tempoLeitura("2 min")
						.icone("fa-regular fa-moon")
						.tags(List.of("descanso", "sono", "fadiga"))
						.build(),

				RecomendacaoResponse.builder()
						.titulo("Evite automedicação")
						.descricao("Mesmo medicamentos comuns podem não ser indicados na gestação. Use remédios apenas com orientação profissional.")
						.categoria("cuidados")
						.fase("PRIMEIRO_TRIMESTRE")
						.tempoLeitura("2 min")
						.icone("fa-solid fa-prescription-bottle-medical")
						.tags(List.of("segurança", "medicamentos", "orientação"))
						.build(),

				RecomendacaoResponse.builder()
						.titulo("Prefira movimentos leves se estiver liberada")
						.descricao("Caminhadas curtas e alongamentos leves podem ajudar na disposição, desde que não haja contraindicação médica.")
						.categoria("atividade")
						.fase("PRIMEIRO_TRIMESTRE")
						.tempoLeitura("3 min")
						.icone("fa-solid fa-person-walking")
						.tags(List.of("movimento", "atividade leve", "disposição"))
						.build()
		);
	}

	private List<RecomendacaoResponse> recomendacoesSegundoTrimestre() {
		return List.of(
				RecomendacaoResponse.builder()
						.titulo("Acompanhe o crescimento da barriga")
						.descricao("No segundo trimestre, o corpo passa por mudanças mais visíveis. Observe desconfortos, postura e sinais que mereçam atenção.")
						.categoria("cuidados")
						.fase("SEGUNDO_TRIMESTRE")
						.tempoLeitura("3 min")
						.icone("fa-solid fa-heart-pulse")
						.tags(List.of("crescimento", "acompanhamento", "corpo"))
						.build(),

				RecomendacaoResponse.builder()
						.titulo("Inclua alimentos ricos em ferro")
						.descricao("Carnes magras, feijões, ovos e folhas verde-escuras podem ajudar a apoiar as necessidades nutricionais da gestação.")
						.categoria("alimentacao")
						.fase("SEGUNDO_TRIMESTRE")
						.tempoLeitura("3 min")
						.icone("fa-solid fa-bowl-food")
						.tags(List.of("ferro", "alimentação", "energia"))
						.build(),

				RecomendacaoResponse.builder()
						.titulo("Cuide da postura nas atividades diárias")
						.descricao("Com o aumento do peso abdominal, ajustes na postura ao sentar, levantar e dormir podem reduzir desconfortos nas costas.")
						.categoria("bem-estar")
						.fase("SEGUNDO_TRIMESTRE")
						.tempoLeitura("3 min")
						.icone("fa-solid fa-spa")
						.tags(List.of("postura", "costas", "conforto"))
						.build(),

				RecomendacaoResponse.builder()
						.titulo("Mantenha atividade física segura")
						.descricao("Se houver liberação profissional, caminhadas, alongamentos e exercícios leves podem contribuir para disposição e bem-estar.")
						.categoria("atividade")
						.fase("SEGUNDO_TRIMESTRE")
						.tempoLeitura("4 min")
						.icone("fa-solid fa-person-walking")
						.tags(List.of("atividade", "movimento", "bem-estar"))
						.build(),

				RecomendacaoResponse.builder()
						.titulo("Observe movimentos do bebê")
						.descricao("Nessa fase, algumas gestantes começam a perceber movimentos fetais. Converse com seu profissional sobre o que observar.")
						.categoria("cuidados")
						.fase("SEGUNDO_TRIMESTRE")
						.tempoLeitura("2 min")
						.icone("fa-regular fa-heart")
						.tags(List.of("bebê", "movimentos", "atenção"))
						.build(),

				RecomendacaoResponse.builder()
						.titulo("Organize exames e consultas do período")
						.descricao("O segundo trimestre costuma envolver exames importantes. Mantenha uma agenda para não perder consultas e orientações.")
						.categoria("cuidados")
						.fase("SEGUNDO_TRIMESTRE")
						.tempoLeitura("3 min")
						.icone("fa-regular fa-calendar-check")
						.tags(List.of("consultas", "exames", "pré-natal"))
						.build()
		);
	}

	private List<RecomendacaoResponse> recomendacoesTerceiroTrimestre() {
		return List.of(
				RecomendacaoResponse.builder()
						.titulo("Observe sinais de alerta")
						.descricao("Sangramento, dor intensa, falta de ar, febre, dor de cabeça forte ou alterações na visão exigem atendimento médico.")
						.categoria("cuidados")
						.fase("TERCEIRO_TRIMESTRE")
						.tempoLeitura("3 min")
						.icone("fa-solid fa-triangle-exclamation")
						.tags(List.of("alerta", "segurança", "atendimento"))
						.build(),

				RecomendacaoResponse.builder()
						.titulo("Prepare a rotina para a chegada do bebê")
						.descricao("Organizar documentos, itens essenciais e apoio para os primeiros dias pode reduzir ansiedade perto do parto.")
						.categoria("bem-estar")
						.fase("TERCEIRO_TRIMESTRE")
						.tempoLeitura("4 min")
						.icone("fa-solid fa-baby")
						.tags(List.of("preparação", "parto", "rotina"))
						.build(),

				RecomendacaoResponse.builder()
						.titulo("Priorize descanso e pausas frequentes")
						.descricao("O sono pode ficar mais difícil no final da gestação. Pausas durante o dia e posições confortáveis podem ajudar.")
						.categoria("bem-estar")
						.fase("TERCEIRO_TRIMESTRE")
						.tempoLeitura("3 min")
						.icone("fa-regular fa-moon")
						.tags(List.of("descanso", "sono", "conforto"))
						.build(),

				RecomendacaoResponse.builder()
						.titulo("Mantenha hidratação e refeições equilibradas")
						.descricao("Água, fibras e refeições equilibradas ajudam no bem-estar geral e podem reduzir desconfortos comuns do final da gestação.")
						.categoria("alimentacao")
						.fase("TERCEIRO_TRIMESTRE")
						.tempoLeitura("3 min")
						.icone("fa-solid fa-droplet")
						.tags(List.of("hidratação", "fibras", "alimentação"))
						.build(),

				RecomendacaoResponse.builder()
						.titulo("Faça movimentos leves com segurança")
						.descricao("Se estiver liberada, caminhadas leves e alongamentos suaves podem ajudar na circulação e no conforto corporal.")
						.categoria("atividade")
						.fase("TERCEIRO_TRIMESTRE")
						.tempoLeitura("3 min")
						.icone("fa-solid fa-person-walking")
						.tags(List.of("circulação", "movimento", "conforto"))
						.build(),

				RecomendacaoResponse.builder()
						.titulo("Converse sobre plano de parto")
						.descricao("Falar com a equipe de saúde sobre preferências, sinais de trabalho de parto e quando procurar atendimento traz mais clareza.")
						.categoria("cuidados")
						.fase("TERCEIRO_TRIMESTRE")
						.tempoLeitura("4 min")
						.icone("fa-solid fa-clipboard-list")
						.tags(List.of("plano de parto", "orientação", "pré-natal"))
						.build()
		);
	}

	private List<RecomendacaoResponse> listarRecomendacoesCiclo(UUID usuarioId) {
		// aqui ficariam as recomendações do ciclo menstrual
		return List.of();
	}

}
