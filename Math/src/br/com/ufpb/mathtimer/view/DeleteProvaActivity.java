package br.com.ufpb.mathtimer.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import br.com.ufpb.mathtimer.controller.DeleteProvaActivityController;
import br.com.ufpb.mathtimer.controller.InstrucoesActivityController;
import br.com.ufpb.mathtimer.model.Fachada;

public class DeleteProvaActivity extends Activity {

	private DeleteProvaActivityController deleteProvaActivityController;
	
	private Fachada fachada;
	//private HashMap<String, Integer> idsETitulos;
	private Button btVoltar;
	private ListView listaDeViews;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deleteprova);
		final Animation animScale = AnimationUtils.loadAnimation(this,
			    R.layout.anim_scale);
		this.fachada = Fachada.getInstance();
		deleteProvaActivityController = new DeleteProvaActivityController(this);
		fachada.setActivity(this);
		listaDeViews = (ListView) findViewById(R.id.listView1);
		btVoltar = (Button) findViewById(R.id.button2);

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
				btVoltar.startAnimation(animScale);
				deleteProvaActivityController.btVoltarCodigo();
			}
		});

	}
	
	@Override
	public void onBackPressed() {
		deleteProvaActivityController.btVoltarCodigo();
	}

}
