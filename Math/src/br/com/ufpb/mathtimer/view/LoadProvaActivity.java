package br.com.ufpb.mathtimer.view;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import br.com.ufpb.mathtimer.controller.ActivityController;
import br.com.ufpb.mathtimer.controller.InstrucoesActivityController;
import br.com.ufpb.mathtimer.model.Fachada;
import br.com.ufpb.mathtimer.model.Prova;
import br.com.ufpb.mathtimer.model.ProvasDAO;

public class LoadProvaActivity extends Activity {

	private Fachada fachada;
	private Button btVoltar;
	private HashMap<String, Integer> idsETitulos;
	private List<Integer> ids = new ArrayList<Integer>();
	private ListView listaDeViews;
	
	private static final String URLgetID = "http://mathtimer-proext.rhcloud.com/checkprova";
	private static final String URLbaixarProva = "http://mathtimer-proext.rhcloud.com/baixarprova";
	private int tempoDeEspera = 60000;
	private byte[] dataGeiID;
	private byte[] dataBaixarProva;
	private HashMap<String,Integer> dataFromServlet = new HashMap<String,Integer>();
	private Prova provaBaixada;
	private Integer idProva;	
	private boolean baixouComSucesso;

	@Override
	public void onCreate(Bundle icicle) {
		 super.onCreate(icicle);
		 setContentView(R.layout.loadprova);
		 final Animation animScale = AnimationUtils.loadAnimation(this,
				    R.layout.anim_scale);
		 listaDeViews = (ListView) findViewById(R.id.listView1);
		 this.fachada = Fachada.getInstance();
		 fachada.setActivity(this);
		 idProva = 0;
		 baixouComSucesso = false;
		 btVoltar = (Button) findViewById(R.id.button2);
		 atualizarLista();
			
		 this.btVoltar.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					btVoltar.startAnimation(animScale);
					ActivityController.mudarDeActivity(fachada.getActivity(),
							InstrucoesActivity.class);				}
		 });
	}
	
	public void atualizarLista(){
		ids  = new ArrayList<Integer>();
		ids.addAll(ProvasDAO.getIdETituloDasProvas(this).values());
		
//		if(baixouComSucesso){
//			Toast.makeText(this,
//					"Prova baixada com sucesso", Toast.LENGTH_LONG).show();
//			baixouComSucesso = false;
//		}
			
//		Toast.makeText(this,
//				"Atualizando a lista de provas", Toast.LENGTH_LONG).show();
		new GetIdAsyncTask().execute(URLgetID);
	}

	public void mostarProvas() {
		if(dataFromServlet.size() == 0){
			Toast.makeText(this,
			                 "No momento não há provas a serem baixadas!",
			                 Toast.LENGTH_LONG).show();
		}else{
			Toast.makeText(this,
	                 "Clique na prova para baixá-la!",
	                 Toast.LENGTH_LONG).show();
		}
		
		idsETitulos = new HashMap<String, Integer>();
		idsETitulos.putAll(dataFromServlet);
		
		Set <String> myset= idsETitulos.keySet();
		myset.remove(null);
		String[] arrayDeTitulos = new String[myset.size()];
		int i =0;
		for(String s: myset){	
			arrayDeTitulos[i++] = "" + s;
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1,
				arrayDeTitulos);
		listaDeViews.setAdapter(adapter);
		listaDeViews.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String itemvalue = (String) listaDeViews.getItemAtPosition(position);
				idProva = idsETitulos.get(itemvalue);
				new DownloadProvaAsyncTask().execute(URLbaixarProva);
			    //atualizarLista();
			}
		});
	}
	
	private class GetIdAsyncTask extends AsyncTask<String, String, String>{
		
		private ProgressDialog progresso;
		//private boolean timeout = false;
		
		@Override
		protected void onPreExecute(){
			progresso = ProgressDialog.show(LoadProvaActivity.this, "Atualizando", "Atualizando a lista de provas!", true, false);
		}
		
		@Override
		protected String doInBackground(String... params) {
			try {
			    HttpParams param = new BasicHttpParams();
			    HttpConnectionParams.setStaleCheckingEnabled(param, false);
			    HttpConnectionParams.setConnectionTimeout(param, tempoDeEspera);
			    //HttpConnectionParams.setSoTimeout(param, tempoDeEspera);
			    DefaultHttpClient httpClient = new DefaultHttpClient(param);
			
			    HttpPost postMethod = new HttpPost(params[0]);
			
			    ByteArrayOutputStream baos = new ByteArrayOutputStream();
			    ObjectOutputStream oos = new ObjectOutputStream(baos);
			    oos.writeObject(ids);
			    ByteArrayEntity req_entity = new ByteArrayEntity(baos.toByteArray());
			    req_entity.setContentType("application/octet-stream");
			
			    postMethod.setEntity(req_entity);
			    
			    HttpResponse response = httpClient.execute(postMethod);
			    HttpEntity resp_entity = response.getEntity();
		        if (resp_entity != null) {
		        	try {
		        		dataGeiID = EntityUtils.toByteArray(resp_entity);
		        		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(dataGeiID));
		        		dataFromServlet = (HashMap<String,Integer>) ois.readObject();
		        		return null;
			          }
			          catch (Exception e) {
			          }
		        }
			}
//			catch (ConnectTimeoutException e) {
//				timeout = true;
//			}
			catch (Exception e) {
			}
			return null;
		}
 
		@Override
		protected void onPostExecute(String result) {
			progresso.dismiss();
			mostarProvas();
		}

	}
	
	private class DownloadProvaAsyncTask extends AsyncTask<String, String, String>{
		private ProgressDialog progresso;
		
		@Override
		protected void onPreExecute(){
			progresso = ProgressDialog.show(LoadProvaActivity.this, "Download", "Download da prova, por favor aguarde!", true, false);
		}
		
		@Override
		protected String doInBackground(String... params) {
			try {
			    HttpParams param = new BasicHttpParams();
			    HttpConnectionParams.setStaleCheckingEnabled(param, false);
			    HttpConnectionParams.setConnectionTimeout(param, tempoDeEspera);
			    //HttpConnectionParams.setSoTimeout(param, tempoDeEspera);
			    DefaultHttpClient httpClient = new DefaultHttpClient(param);
			
			    HttpPost postMethod = new HttpPost(params[0]);
			
			    ByteArrayOutputStream baos = new ByteArrayOutputStream();
			    ObjectOutputStream oos = new ObjectOutputStream(baos);
			    oos.writeObject(idProva);
			    ByteArrayEntity req_entity = new ByteArrayEntity(baos.toByteArray());
			    req_entity.setContentType("application/octet-stream");
			
			    postMethod.setEntity(req_entity);
			    
			    HttpResponse response = httpClient.execute(postMethod);
			    HttpEntity resp_entity = response.getEntity();
		        if (resp_entity != null) {
		        	try {
		        		dataBaixarProva = EntityUtils.toByteArray(resp_entity);
		        		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(dataBaixarProva));
		        		provaBaixada = (Prova) ois.readObject();
				        ProvasDAO.salvarProva(fachada.getActivity(), provaBaixada, idProva);
				        baixouComSucesso = true;
//				        Toast.makeText(fachada.getActivity(),
//								"Prova baixada com sucesso", Toast.LENGTH_LONG).show();
				        //return null;
//				        ActivityController.mudarDeActivity(fachada.getActivity(),
//								InstrucoesActivity.class);	
			          }
			          catch (Exception e) {
			          }
		        }
			}
//			catch (ConnectTimeoutException e) {			
//				Toast.makeText(fachada.getActivity(),
//		                 "Não foi possível se conectar com o servidor!",
//		                 Toast.LENGTH_LONG).show();
//			}
			catch (Exception e) {
//				Toast.makeText(fachada.getActivity(),
//		                 "Não foi possível baixar a prova!",
//		                 Toast.LENGTH_LONG).show();
			}
			return null;
		}
 
		@Override
		protected void onPostExecute(String result) {
			progresso.dismiss();
			if(baixouComSucesso){
				Toast.makeText(fachada.getActivity(),
						"Prova baixada com sucesso", Toast.LENGTH_LONG).show();
			}
			
			ActivityController.mudarDeActivity(fachada.getActivity(),
					InstrucoesActivity.class);
		}
	}
	
	@Override
	public void onBackPressed() {
		ActivityController.mudarDeActivity(fachada.getActivity(),
				InstrucoesActivity.class);	
	}

}


