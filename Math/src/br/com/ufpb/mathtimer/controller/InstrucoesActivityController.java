package br.com.ufpb.mathtimer.controller;

import android.app.Activity;
import br.com.ufpb.mathtimer.view.MainActivity;

public class InstrucoesActivityController {

	private Activity instrucoesActivity;
	private String instrucoes;

	public InstrucoesActivityController(Activity activity) {
		this.instrucoesActivity = activity;
		this.instrucoes = "Clique no bot�o jogar para come�ar o jogo.\n"
				+ "Para jogar � s� apertar a resposta certa para cada pergunta.\n"
				+ "Se voc� n�o conseguir acertar antes da barrinha chegar no final,\n"
				+ "voc� ter� que come�ar de novo.";
	}

	public String getInstrucoes() {
		return this.instrucoes;
	}

	public void setInstrucoes(String instrucoes) {
		this.instrucoes = instrucoes;
	}

	public void btMenuCodigo() {
		ActivityController.mudarDeActivity(instrucoesActivity,
				MainActivity.class);
	}

	public void btSairCodigo() {
		instrucoesActivity.finish();
	}
}
