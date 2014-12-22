package br.com.ufpb.mathtimer.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import br.com.ufpb.mathtimer.controller.DeleteProvaActivityController;
import br.com.ufpb.mathtimer.model.Fachada;

public class DeleteProvaActivity extends Activity {

	private DeleteProvaActivityController deleteProvaActivityController;
	private Fachada fachada;
	//private HashMap<String, Integer> idsETitulos;
	private Button btVoltar, btSair;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deleteprova);
		this.fachada = Fachada.getInstance();
		deleteProvaActivityController = new DeleteProvaActivityController(this);
		fachada.setActivity(this);
		final ListView listaDeViews = (ListView) findViewById(R.id.listView1);
		btVoltar = (Button) findViewById(R.id.button2);
		btSair = (Button) findViewById(R.id.button3);

		listaDeViews.setAdapter(deleteProvaActivityController
				.btGetAdapterCodigo());
		listaDeViews.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String itemvalue = (String) listaDeViews
						.getItemAtPosition(position);
				deleteProvaActivityController.btDeletarCodigo(itemvalue);
				listaDeViews.setAdapter(deleteProvaActivityController
						.btGetAdapterCodigo());
			}
		});

		this.btVoltar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				deleteProvaActivityController.btVoltarCodigo();
			}
		});

		this.btSair.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				deleteProvaActivityController.btSairCodigo();
			}
		});

	}

}
