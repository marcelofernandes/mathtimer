package br.com.ufpb.mathtimer.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import br.com.ufpb.mathtimer.controller.RankingActivityController;
import br.com.ufpb.mathtimer.model.Fachada;

public class RankingActivity extends Activity {

	private RankingActivityController rankingActivityController;
	private TextView texto;
	private Button btMenu, btSair, btZerarRanking;
	private Fachada fachada;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ranking);
		this.btMenu = (Button) findViewById(R.id.button1);
		this.btSair = (Button) findViewById(R.id.button2);
		this.btZerarRanking = (Button) findViewById(R.id.button3);
		this.texto = (TextView) findViewById(R.id.textView3);
		this.fachada = Fachada.getInstance();
		rankingActivityController = new RankingActivityController(this);
		// fachada.setActivity(this);
		// fachada.setRankingId(1);
		this.texto.setText(rankingActivityController.getDadosDoRanking());

		this.btMenu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				rankingActivityController.btMenuCodigo();
			}
		});
		this.btSair.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				rankingActivityController.btSairCodigo();
			}
		});

		this.btZerarRanking.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				rankingActivityController.btZerarRankingCodigo();
				texto.setText(rankingActivityController.getDadosDoRanking());
			}
		});

	}
}
