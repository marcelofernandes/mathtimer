package br.com.ufpb.mathtimer.controller;

import android.app.Activity;
import android.content.Intent;

public class ActivityController {

	public static void mudarDeActivity(Activity atual, Class<?> classe) {
		Intent in = new Intent(atual, classe);
		atual.startActivity(in);
		atual.finish();
	}
}
