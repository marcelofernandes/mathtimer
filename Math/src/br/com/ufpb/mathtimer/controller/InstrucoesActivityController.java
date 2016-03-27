package br.com.ufpb.mathtimer.controller;

import android.app.Activity;
import br.com.ufpb.mathtimer.view.DeleteProvaActivity;
import br.com.ufpb.mathtimer.view.LoadProvaActivity;
import br.com.ufpb.mathtimer.view.MainActivity;

public class InstrucoesActivityController {

	private Activity instrucoesActivity;
	private String instrucoes;

	public InstrucoesActivityController(Activity activity) {
		this.instrucoesActivity = activity;
		this.instrucoes = "- O objetivo � acertar as perguntas antes de a barra de tempo se esgotar.\n"
				+ "- Voc� tem v�rias chances de acertar, mas o quanto antes acertar, maior � a pontua��o!\n"
				+ "- Voc� pode dar zoom nas imagens clicando em cima delas.";
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
	
	public void btBaixarProvaCodigo() {
		ActivityController.mudarDeActivity(instrucoesActivity,
				LoadProvaActivity.class);
	}
	
	public void btDeletarProvaCodigo() {
		ActivityController.mudarDeActivity(instrucoesActivity,
				DeleteProvaActivity.class);
	}

	public void btSairCodigo() {
		instrucoesActivity.finish();
	}
}
