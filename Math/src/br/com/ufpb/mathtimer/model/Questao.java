package br.com.ufpb.mathtimer.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Questao implements Serializable {

	private String enunciado;
	private List<Alternativa> alternativas = new ArrayList<Alternativa>();
	private Alternativa alternativaCorreta;
	private byte[] imagem;
	private boolean temImagem;

	public Questao(String enunciado) {
		this.enunciado = enunciado;
		// this.idImagem = 0;
		this.temImagem = false;
	}

	public boolean hasImagem() {
		return this.temImagem;
	}

	public void setTemImagem(boolean has) {
		this.temImagem = has;
	}

	public void adicionarAlternativa(Alternativa alternativa) {
		alternativas.add(alternativa);
	}

	public void adicionarAlternativas(List<Alternativa> listaAlternativas) {
		alternativas.addAll(listaAlternativas);
	}

	public void setAlternativaCorreta(Alternativa alternativa) {
		this.alternativaCorreta = alternativa;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public List<Alternativa> getAlternativas() {
		return this.alternativas;
	}

	public Alternativa getAlternativaCorreta() {
		return alternativaCorreta;
	}

	public String getEnunciado() {
		return this.enunciado;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public byte[] getImagem() {
		return this.imagem;
	}

}

