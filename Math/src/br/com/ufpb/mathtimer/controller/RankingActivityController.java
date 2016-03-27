package br.com.ufpb.mathtimer.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.widget.Toast;
import br.com.ufpb.mathtimer.model.Fachada;
import br.com.ufpb.mathtimer.view.MainActivity;

public class RankingActivityController {

	private Activity rankingActivity;
	private Fachada fachada;

	public RankingActivityController(Activity activity) {
		this.rankingActivity = activity;
		this.fachada = Fachada.getInstance();
	}

	public String getDadosDoRanking() {
		try {
			return fachada.getDadosDoRanking();
		} catch (FileNotFoundException e) {
			Toast.makeText(rankingActivity,
					"Não foi possível carregar o ranking", Toast.LENGTH_LONG)
					.show();
		} catch (ClassNotFoundException e) {
			Toast.makeText(rankingActivity,
					"Não foi possível carregar o ranking", Toast.LENGTH_LONG)
					.show();
		} catch (IOException e) {
			Toast.makeText(rankingActivity,
					"Não foi possível carregar o ranking", Toast.LENGTH_LONG)
					.show();
		}
		return "";
	}

	public void btMenuCodigo() {
		ActivityController.mudarDeActivity(rankingActivity, MainActivity.class);
	}

	public void btSairCodigo() {
		rankingActivity.finish();
	}

	public void btZerarRankingCodigo() {
		try {
			fachada.zerarRanking();
		} catch (FileNotFoundException e) {
			Toast.makeText(rankingActivity, "Não foi possível zerar o ranking",
					Toast.LENGTH_LONG).show();
		} catch (ClassNotFoundException e) {
			Toast.makeText(rankingActivity, "Não foi possível zerar o ranking",
					Toast.LENGTH_LONG).show();
		} catch (IOException e) {
			Toast.makeText(rankingActivity, "Não foi possível zerar o ranking",
					Toast.LENGTH_LONG).show();
		}
	}
}
