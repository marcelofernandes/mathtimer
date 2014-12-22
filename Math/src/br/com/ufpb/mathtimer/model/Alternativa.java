package br.com.ufpb.mathtimer.model;

import java.io.Serializable;

public class Alternativa implements Serializable {

	private int id;
	private String texto;

	public Alternativa(int id) {
		this(id, null);
	}

	public Alternativa(int id, String texto) {
		this.id = id;
		this.texto = texto;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}


