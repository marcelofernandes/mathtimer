package br.com.ufpb.mathtimer.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import br.com.ufpb.mathtimer.controller.ActivityController;
import br.com.ufpb.mathtimer.model.Fachada;
import br.com.ufpb.mathtimer.model.ProvasDAO;

public class LoadProvaActivity extends Activity {

	private Fachada fachada;
	private Button btVoltar;
	private Button btSair;
	private HashMap<String, Integer> idsETitulos;
	private List<Integer> ids = new ArrayList<Integer>();
	private ListView listaDeViews;

	@Override
	public void onCreate(Bundle icicle) {
		 super.onCreate(icicle);
		 setContentView(R.layout.loadprova);
		 listaDeViews = (ListView) findViewById(R.id.listView1);
		 this.fachada = Fachada.getInstance();
		 fachada.setActivity(this);
		 btVoltar = (Button) findViewById(R.id.button2);
		 btSair = (Button) findViewById(R.id.button3);
		 atualizarLista();
			
		 this.btVoltar.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					ActivityController.mudarDeActivity(fachada.getActivity(),
							MainActivity.class);				}
		 });

		 this.btSair.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					fachada.getActivity().finish();
				}
		 });
	}
	
	public void atualizarLista(){
		ids  = new ArrayList<Integer>();
		ids.addAll(ProvasDAO.getIdETituloDasProvas(this).values());
	    new GetIdFromServlet().execute(LoadProvaActivity.this, ids);

	}

	public void mostarProvas(HashMap<String, Integer> dataFromServlet) {
		if(dataFromServlet.size() == 0){
			Toast.makeText(this,
			                 "No momento não há provas a serem baixadas!",
			                 Toast.LENGTH_LONG).show();
		}
		
		idsETitulos = new HashMap<String, Integer>();
		idsETitulos.putAll(dataFromServlet);

		String[] arrayDeTitulos = (String[]) idsETitulos.keySet().toArray(new String[0]);

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
			    new GetDataFromWebService().execute(LoadProvaActivity.this, idSelecionado);
			    //atualizarLista();
			}
		});
	}

}


