package br.com.ufpb.mathtimer.controller;

import android.app.Activity;
import br.com.ufpb.mathtimer.view.ConfigActivity;
import br.com.ufpb.mathtimer.view.InstrucoesActivity;
import br.com.ufpb.mathtimer.view.SelecaoDeProvaActivity;
import br.com.ufpb.mathtimer.view.SelecaoDeRankingActivity;

public class MainActivityController {
	private Activity mainActivity;

	public MainActivityController(Activity activiy) {
		this.mainActivity = activiy;
	}

	public void btJogarCodigo() {
		ActivityController.mudarDeActivity(mainActivity,
				SelecaoDeProvaActivity.class);
	}

	public void btRankingCodigo() {
		ActivityController.mudarDeActivity(mainActivity,
				SelecaoDeRankingActivity.class);
	}

	public void btInstrucoesCodigo() {
		ActivityController.mudarDeActivity(mainActivity,
				InstrucoesActivity.class);
	}

	public void btSairCodigo() {
		mainActivity.finish();
	}

	public void btConfigCodigo() {
		ActivityController.mudarDeActivity(mainActivity, ConfigActivity.class);
	}

}
