package br.com.ufpb.mathtimer.controller;

import android.app.Activity;
import br.com.ufpb.mathtimer.model.Fachada;
import br.com.ufpb.mathtimer.view.JogoActivity;

public class NomeJogadorActivityController {

	private Fachada fachada;
	private Activity nomeJogadorActivity;

	public NomeJogadorActivityController(Activity activity) {
		this.nomeJogadorActivity = activity;
		this.fachada = Fachada.getInstance();
	}

	public void btCriarJogadorCodigo(String nome) {
		criarJogador(nome);
		ActivityController.mudarDeActivity(nomeJogadorActivity,
				JogoActivity.class);
	}

	public void btSairCodigo() {
		nomeJogadorActivity.finish();
	}

	private void criarJogador(String nome) {
		fachada.criarJogador();
		if (nome.length() == 0)
			nome = "Jogador";
		else if (nome.length() > 10)
			nome = nome.substring(0, 10);
		fachada.setNomeDoJogador(nome);
	}

}
