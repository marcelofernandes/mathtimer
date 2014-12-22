package br.com.ufpb.mathtimer.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import br.com.ufpb.mathtimer.controller.InstrucoesActivityController;

public class InstrucoesActivity extends Activity {

	private InstrucoesActivityController instrucoesActivityController;
	private TextView texto;
	private Button btMenu, btSair;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.instrucoes);
		instrucoesActivityController = new InstrucoesActivityController(this);

		this.btMenu = (Button) findViewById(R.id.button1);
		this.texto = (TextView) findViewById(R.id.textView3);
		this.texto.setText(instrucoesActivityController.getInstrucoes());
		this.btMenu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				instrucoesActivityController.btMenuCodigo();
			}
		});
		this.btSair = (Button) findViewById(R.id.button2);
		this.btSair.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				instrucoesActivityController.btSairCodigo();
			}
		});
	}
}
