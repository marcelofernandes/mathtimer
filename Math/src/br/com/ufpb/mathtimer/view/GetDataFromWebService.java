package br.com.ufpb.mathtimer.view;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.widget.Toast;
import br.com.ufpb.mathtimer.model.Prova;
import br.com.ufpb.mathtimer.model.ProvasDAO;

public class GetDataFromWebService {

	private static final String LoginServiceUri = "http://10.0.2.2:8080/servlet/DataPingServlet";
	private int tempoDeEspera = 15000;
	private static LoadProvaActivity activity;
	private byte[] data;
	private Integer id;	
	private Prova provaBaixada;
	
	public void execute(LoadProvaActivity act, int id) {
		activity = act;
		this.id = id;
		requisitarProva();
	}
	
	private void requisitarProva() {
		try {
		    HttpParams params = new BasicHttpParams();
		    HttpConnectionParams.setStaleCheckingEnabled(params, false);
		    HttpConnectionParams.setConnectionTimeout(params, tempoDeEspera);
		    HttpConnectionParams.setSoTimeout(params, tempoDeEspera);
		    DefaultHttpClient httpClient = new DefaultHttpClient(params);
		
		    HttpPost postMethod = new HttpPost(LoginServiceUri);
		
		    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    ObjectOutputStream oos = new ObjectOutputStream(baos);
		    oos.writeObject(id);
		    
		    ByteArrayEntity req_entity = new ByteArrayEntity(baos.toByteArray());
		    req_entity.setContentType("application/octet-stream");
		
		    postMethod.setEntity(req_entity);
		   
		    httpClient.execute(postMethod, new ResponseHandler<Void>() {
			      public Void handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
			    	 
			    	  HttpEntity resp_entity = response.getEntity();
				        if (resp_entity != null) {
				          try {
				            data = EntityUtils.toByteArray(resp_entity);
				            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
				            provaBaixada = (Prova) ois.readObject();
				            ProvasDAO.salvarProva(activity, provaBaixada, id);
							Toast.makeText(activity, "Prova carregada com sucesso!",
							                 Toast.LENGTH_LONG).show();
				            activity.atualizarLista();
				          }
				          catch (Exception e) {
				          }
				        }
				        else {
				          throw new IOException(
				              new StringBuffer()
				                  .append("HTTP response : ").append(response.getStatusLine())
				                  .toString());
				        }
						return null;
			      }
		    });
	  }
	  catch (Exception e) {
	  }
	}

}


