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
import br.com.ufpb.mathtimer.controller.SelecaoDeRankingActivityController;
import br.com.ufpb.mathtimer.model.Fachada;
import br.com.ufpb.mathtimer.model.ProvasDAO;

public class SelecaoDeRankingActivity extends Activity {

	private SelecaoDeRankingActivityController selecaoDeRankingActivityController;
	private Fachada fachada;
	private HashMap<String, Integer> idsETitulos;
	private Button btVoltar, btSair;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selecaoderanking);
		this.fachada = Fachada.getInstance();
		selecaoDeRankingActivityController = new SelecaoDeRankingActivityController(
				this);
		fachada.setActivity(this);
		final ListView listaDeViews = (ListView) findViewById(R.id.listView1);
		btVoltar = (Button) findViewById(R.id.button2);
		btSair = (Button) findViewById(R.id.button3);

		idsETitulos = ProvasDAO.getIdETituloDasProvas(this);
		if (idsETitulos.size() == 0) {
			selecaoDeRankingActivityController.criarProvaPadrao();
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
				String itemvalue = (String) listaDeViews
						.getItemAtPosition(position);
				int idSelecionado = idsETitulos.get(itemvalue);
				selecaoDeRankingActivityController
						.selecionarRanking(idSelecionado);
			}
		});

		this.btVoltar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				selecaoDeRankingActivityController.btVoltarCodigo();
			}
		});

		this.btSair.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				selecaoDeRankingActivityController.btSairCodigo();
			}
		});
	}
}
