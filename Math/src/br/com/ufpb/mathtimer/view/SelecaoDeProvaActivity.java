package br.com.ufpb.mathtimer.view;

import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import br.com.ufpb.mathtimer.controller.SelecaoDeProvaActivityController;
import br.com.ufpb.mathtimer.model.Fachada;
import br.com.ufpb.mathtimer.model.ProvasDAO;

public class SelecaoDeProvaActivity extends Activity {

	private SelecaoDeProvaActivityController selecaoDeProvaActivityController;
	private Fachada fachada;
	private HashMap<String, Integer> idsETitulos;
	private Button btContinuar, btVoltar, btSair;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selecaodeprova);
		this.fachada = Fachada.getInstance();
		selecaoDeProvaActivityController = new SelecaoDeProvaActivityController(
				this);
		fachada.setActivity(this);
		final ListView listaDeViews = (ListView) findViewById(R.id.listView1);
		btContinuar = (Button) findViewById(R.id.button1);
		btVoltar = (Button) findViewById(R.id.button2);
		btSair = (Button) findViewById(R.id.button3);

		idsETitulos = ProvasDAO.getIdETituloDasProvas(this);
		if (idsETitulos.size() == 0) {
			selecaoDeProvaActivityController.criarProvaPadrao();
			idsETitulos = ProvasDAO.getIdETituloDasProvas(this);
		}

		String[] arrayDeTitulos = (String[]) idsETitulos.keySet().toArray(
				new String[0]);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1,
				arrayDeTitulos);
		listaDeViews.setAdapter(adapter);
		listaDeViews.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String itemvalue = (String) listaDeViews.getItemAtPosition(position);
				int idSelecionado = idsETitulos.get(itemvalue);
				selecaoDeProvaActivityController.selecionarProva(idSelecionado);
			}
		});

		this.btContinuar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				selecaoDeProvaActivityController.btContinuarCodigo();
			}
		});
		if (!fachada.isJogoSalvo()) {
			this.btContinuar.setEnabled(false);
		}

		this.btVoltar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				selecaoDeProvaActivityController.btVoltarCodigo();
			}
		});

		this.btSair.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				selecaoDeProvaActivityController.btSairCodigo();
			}
		});
	}

}
