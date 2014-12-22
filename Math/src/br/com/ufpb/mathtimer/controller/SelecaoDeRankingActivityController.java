package br.com.ufpb.mathtimer.controller;

import android.app.Activity;
import br.com.ufpb.mathtimer.model.Fachada;
import br.com.ufpb.mathtimer.view.MainActivity;
import br.com.ufpb.mathtimer.view.RankingActivity;

public class SelecaoDeRankingActivityController {

	private Activity selecaoDeRankingActivity;
	private Fachada fachada;

	public SelecaoDeRankingActivityController(Activity activity) {
		this.selecaoDeRankingActivity = activity;
		this.fachada = Fachada.getInstance();
	}

	public void criarProvaPadrao() {
		fachada.criarGerenteDeProva();
		fachada.criarGerenteDeRanking();
		fachada.criarProvaPadrao();
		fachada.salvarProva();
	}

	public void selecionarRanking(int id) {
		fachada.criarGerenteDeProva();
		fachada.criarGerenteDeRanking();
		fachada.setRankingId(id);
		ActivityController.mudarDeActivity(selecaoDeRankingActivity,
				RankingActivity.class);
	}

	public void btVoltarCodigo() {
		ActivityController.mudarDeActivity(selecaoDeRankingActivity,
				MainActivity.class);
	}

	public void btSairCodigo() {
		selecaoDeRankingActivity.finish();
	}
}
