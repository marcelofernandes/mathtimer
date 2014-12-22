package br.com.ufpb.mathtimer.controller;

import br.com.ufpb.mathtimer.view.DeleteProvaActivity;
import br.com.ufpb.mathtimer.view.LoadProvaActivity;
import br.com.ufpb.mathtimer.view.MainActivity;
import android.app.Activity;

public class ConfigActivityController {

	private Activity activity;

	public ConfigActivityController(Activity activiy) {
		this.activity = activiy;
	}

	public void btCarregarCodigo() {
		ActivityController.mudarDeActivity(activity, LoadProvaActivity.class);
	}

	public void btDeletarCodigo() {
		ActivityController.mudarDeActivity(activity, DeleteProvaActivity.class);
	}

	public void btVoltarMenuCodigo() {
		ActivityController.mudarDeActivity(activity, MainActivity.class);
	}

	public void btSairCodigo() {
		activity.finish();
	}

}
