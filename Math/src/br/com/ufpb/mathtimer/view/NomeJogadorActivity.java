package br.com.ufpb.mathtimer.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import br.com.ufpb.mathtimer.controller.NomeJogadorActivityController;
import br.com.ufpb.mathtimer.model.Fachada;

public class NomeJogadorActivity extends Activity {

	private NomeJogadorActivityController nomeJogadorActivityController;
	private Button btCriarJogador, btSair;
	private EditText texto;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nomejogador);
		nomeJogadorActivityController = new NomeJogadorActivityController(this);
		this.texto = (EditText) findViewById(R.id.editText1);
		this.btCriarJogador = (Button) findViewById(R.id.button2);
		this.btCriarJogador.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				nomeJogadorActivityController.btCriarJogadorCodigo(texto
						.getText().toString());
			}
		});

		this.btSair = (Button) findViewById(R.id.button3);
		this.btSair.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				nomeJogadorActivityController.btSairCodigo();
			}
		});
	}
}
