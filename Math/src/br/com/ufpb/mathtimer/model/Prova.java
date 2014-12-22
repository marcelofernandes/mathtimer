package br.com.ufpb.mathtimer.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Prova implements Serializable {

	private String titulo;
	private int id;
	private List<Questao> questoes = new ArrayList<Questao>();
	//private Questao questaoAtual;

	public Prova(int id) {
		this.id = id;
	}
	
	public Prova(int id, String titulo) {
		this.id = id;
		this.titulo = titulo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

//	public Questao getQuestaoAtual() {
//		return questaoAtual;
//	}
//
//	public void setQuestaoAtual(Questao questao) {
//		this.questaoAtual = questao;
//	}

	public void adicionarQuestoes(List<Questao> questoes) {
		this.questoes.addAll(questoes);
	}

	public List<Questao> getListaDeQuestoes() {
		return this.questoes;
	}
}
