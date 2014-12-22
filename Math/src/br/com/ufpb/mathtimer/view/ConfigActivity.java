package br.com.ufpb.mathtimer.view;

import br.com.ufpb.mathtimer.controller.ConfigActivityController;
import br.com.ufpb.mathtimer.controller.MainActivityController;
import br.com.ufpb.mathtimer.model.Fachada;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConfigActivity extends Activity {

	private ConfigActivityController configActivityController;
	private Button btCarregar, btDeletar, btVoltar, btsair;
	private Fachada fachada;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.config);
		fachada = Fachada.getInstance();
		configActivityController = new ConfigActivityController(this);
		fachada.setActivity(this);
		this.btCarregar = (Button) findViewById(R.id.button1);
		this.btCarregar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				configActivityController.btCarregarCodigo();
			}
		});

		this.btDeletar = (Button) findViewById(R.id.button2);
		this.btDeletar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				configActivityController.btDeletarCodigo();
			}
		});

		this.btVoltar = (Button) findViewById(R.id.button3);
		this.btVoltar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				configActivityController.btVoltarMenuCodigo();
			}
		});

		this.btsair = (Button) findViewById(R.id.button4);
		this.btsair.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				configActivityController.btSairCodigo();
			}
		});
	}

}
