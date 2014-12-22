package br.com.ufpb.mathtimer.model;

import java.io.Serializable;

public class Jogador implements Comparable<Jogador>, Serializable {

	private String nome;
	private int pontuacao;
	private int qtdQuestoesRespondidas;
	private int tempoDasQuestoes;
	private Tentativa tentativa;
	private boolean estaNoRanking;

	public Jogador() {
		this.nome = "jogador";
		this.pontuacao = 0;
		this.qtdQuestoesRespondidas = 0;
		this.tempoDasQuestoes = 0;
		this.tentativa = Tentativa.PRIMEIRA;
		this.estaNoRanking = false;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPontuacao() {
		return this.pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

	public void setTentativa(Tentativa t) {
		this.tentativa = t;
	}

	public Tentativa getTentativa() {
		return this.tentativa;
	}

	public boolean isNoRanking() {
		return estaNoRanking;
	}

	public void setNoRanking(boolean estaNoRanking) {
		this.estaNoRanking = estaNoRanking;
	}

	public void incrementarPontuacao() {
		switch (this.tentativa) {
		case PRIMEIRA:
			this.pontuacao += 10;
			break;
		case SEGUNDA:
			this.pontuacao += 7;
			break;
		case TERCEIRA:
			this.pontuacao += 3;
			break;
		case QUARTA:
			this.pontuacao += 1;
			break;
		}
		incrementarQtdQuestoesRespondidas();
	}

	public void incrementarQtdQuestoesRespondidas() {
		this.qtdQuestoesRespondidas++;
	}

	public void incrementarTempoDasQuestoes(int tempo) {
		this.tempoDasQuestoes += tempo;
	}

	public double getMediaDeTempo() {
		if (qtdQuestoesRespondidas == 0)
			return 0;
		else
			return tempoDasQuestoes / qtdQuestoesRespondidas;
	}

	@Override
	public int compareTo(Jogador jogador) {
		if (this.pontuacao > jogador.pontuacao) {
			return 1;
		} else if (this.pontuacao == jogador.pontuacao) {
			if (this.getMediaDeTempo() < jogador.getMediaDeTempo()) {
				return 1;
			} else if (this.getMediaDeTempo() == jogador.getMediaDeTempo()) {
				return 0;
			} else {
				return -1;
			}

		} else {
			return -1;
		}

	}

	public boolean equals(Jogador j) {
		if (j.nome.equals(this.nome) && j.pontuacao == this.pontuacao) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return this.nome + " " + this.pontuacao;

	}
}
