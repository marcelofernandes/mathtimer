package br.com.ufpb.mathtimer.model;

import java.io.Serializable;
import java.util.*;

public class Ranking implements Serializable {
	private List<Jogador> jogadoresDoRanking;
	private int qtdMaximaDeJogadoresNoRanking;
	private Jogador ultimoJogadorAdicionado;
	private int id;

	public Ranking(int id) {
		this.id = id;
		this.jogadoresDoRanking = new ArrayList<Jogador>();
		this.qtdMaximaDeJogadoresNoRanking = 5;
		carregarRanking();
	}

	public void carregarRanking() {
		for (int i = 0; i < this.qtdMaximaDeJogadoresNoRanking; i++) {
			Jogador jogador = new Jogador();
			this.jogadoresDoRanking.add(jogador);
		}
	}

	public int getIdDoRanking() {
		return this.id;
	}

	public void setIdDoRanking(int id) {
		this.id = id;
	}

	public List<String> getNomesDosJogadores() {
		List<String> nomes = new ArrayList<String>();
		for (Jogador jogador : this.jogadoresDoRanking) {
			nomes.add(jogador.getNome());
		}
		return nomes;
	}

	public List<Integer> getPontuacoesDosJogadores() {
		List<Integer> pontuacoes = new ArrayList<Integer>();
		for (Jogador jogador : this.jogadoresDoRanking) {
			pontuacoes.add(jogador.getPontuacao());
		}
		return pontuacoes;
	}

	public boolean jogadorEntraNoRanking(Jogador jogador) {
		if (jogador.compareTo(this.jogadoresDoRanking
				.get(this.qtdMaximaDeJogadoresNoRanking - 1)) == 1) {
			this.jogadoresDoRanking
					.remove(this.qtdMaximaDeJogadoresNoRanking - 1);
			this.jogadoresDoRanking.add(jogador);
			this.ultimoJogadorAdicionado = jogador;
			ordenarRanking();

			return true;
		}
		return false;
	}

	public void atualizarRanking(Jogador jogador) {
		this.jogadoresDoRanking.set(
				this.jogadoresDoRanking.indexOf(ultimoJogadorAdicionado),
				jogador);
		ordenarRanking();
		this.ultimoJogadorAdicionado = jogador;
	}

	private void ordenarRanking() {
		Collections.sort(this.jogadoresDoRanking);
		Collections.reverse(this.jogadoresDoRanking);
	}

	public String getDadosDoRanking() {
		String dados = "";
		for (int i = 0; i < this.qtdMaximaDeJogadoresNoRanking; i++) {
			Jogador jogador = this.jogadoresDoRanking.get(i);
			dados += "\n " + jogador.getNome() + " " + jogador.getPontuacao();
		}
		return dados;
	}

	public void zerarRanking() {
		for (Jogador jogador : this.jogadoresDoRanking) {
			jogador.setNome("Jogador");
			jogador.setPontuacao(0);
		}
	}
}
