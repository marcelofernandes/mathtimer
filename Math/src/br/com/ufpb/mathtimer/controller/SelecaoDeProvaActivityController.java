package br.com.ufpb.mathtimer.controller;

import android.app.Activity;
import br.com.ufpb.mathtimer.model.Fachada;
import br.com.ufpb.mathtimer.view.JogoActivity;
import br.com.ufpb.mathtimer.view.MainActivity;
import br.com.ufpb.mathtimer.view.NomeJogadorActivity;

public class SelecaoDeProvaActivityController {

	private Activity selecaoDeProvaActivity;
	private Fachada fachada;

	public SelecaoDeProvaActivityController(Activity activity) {
		this.selecaoDeProvaActivity = activity;
		this.fachada = Fachada.getInstance();
	}

	public void criarProvaPadrao() {
		fachada.criarGerenteDeProva();
		fachada.criarGerenteDeRanking();
		fachada.criarProvaPadrao();
		fachada.salvarProva();
	}

	public void selecionarProva(int id) {
		fachada.criarGerenteDeProva();
		fachada.criarGerenteDeRanking();
		fachada.selecionarProva(id);
		fachada.setRankingId(id);
		fachada.setIdProvaAtual(id);
		ActivityController.mudarDeActivity(selecaoDeProvaActivity,
				NomeJogadorActivity.class);
	}

	public void btContinuarCodigo() {
		ActivityController.mudarDeActivity(selecaoDeProvaActivity,
				JogoActivity.class);
	}

	public void btVoltarCodigo() {
		ActivityController.mudarDeActivity(selecaoDeProvaActivity,
				MainActivity.class);
	}

	public void btSairCodigo() {
		selecaoDeProvaActivity.finish();
	}
}
