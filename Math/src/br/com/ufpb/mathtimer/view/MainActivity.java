package br.com.ufpb.mathtimer.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import br.com.ufpb.mathtimer.controller.MainActivityController;
import br.com.ufpb.mathtimer.model.Fachada;

public class MainActivity extends Activity {

	private MainActivityController mainActivityController;
	private Button btJogar, btRanking, btInstrucoes, btConfig, btsair;
	private Fachada fachada;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		fachada = Fachada.getInstance();
		mainActivityController = new MainActivityController(this);
		fachada.setActivity(this);
		this.btJogar = (Button) findViewById(R.id.button1);
		this.btJogar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mainActivityController.btJogarCodigo();
			}
		});

		this.btRanking = (Button) findViewById(R.id.button2);
		this.btRanking.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mainActivityController.btRankingCodigo();
			}
		});

		this.btInstrucoes = (Button) findViewById(R.id.button3);
		this.btInstrucoes.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mainActivityController.btInstrucoesCodigo();
			}
		});

		this.btConfig = (Button) findViewById(R.id.button5);
		this.btConfig.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mainActivityController.btConfigCodigo();
			}
		});

		this.btsair = (Button) findViewById(R.id.button4);
		this.btsair.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mainActivityController.btSairCodigo();
			}
		});
	}

}