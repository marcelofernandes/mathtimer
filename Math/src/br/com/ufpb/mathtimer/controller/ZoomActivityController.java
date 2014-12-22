package br.com.ufpb.mathtimer.controller;

import java.util.concurrent.atomic.AtomicBoolean;

import android.app.Activity;
import br.com.ufpb.mathtimer.model.Fachada;
import br.com.ufpb.mathtimer.view.JogoActivity;

public class ZoomActivityController {

	private Activity zoomActivity;
	private AtomicBoolean continueThread;
	private Fachada fachada;
	private Thread t;
	private int tempo;

	public ZoomActivityController(Activity activity) {
		this.zoomActivity = activity;
		this.fachada = Fachada.getInstance();
		this.continueThread = new AtomicBoolean(true);
		tempo = fachada.getTempo();
		iniciarThread();
	}

	public void btVoltarCodigo() {
		continueThread.set(false);
		ActivityController.mudarDeActivity(zoomActivity, JogoActivity.class);
	}

	public void iniciarThread() {
		this.t = new Thread(new Runnable() {
			@Override
			public void run() {
				while (continueThread.get()) {
					if (tempo < 100) {
						tempo++;
						fachada.setTempo(tempo);
						try {
							Thread.sleep(1000);
						} catch (Throwable t) {
						}
					} else {
						continueThread.set(false);
						ActivityController.mudarDeActivity(zoomActivity,
								JogoActivity.class);
					}
				}
			}
		});
		this.t.start();

	}
}
