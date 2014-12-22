package br.com.ufpb.mathtimer.view;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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

import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class GetIdFromServlet {

	private static final String LoginServiceUri = "http://10.0.2.2:8080/alo/AloMundo";
	private int NetworkConnectionTimeout_ms = 15000;
	private LoadProvaActivity activity;
	private byte[] data;
	private HashMap<String,Integer> dataFromServlet = new HashMap<String,Integer>();
	private List<Integer> ids = new ArrayList<Integer>();
	
	public void execute(LoadProvaActivity act, List<Integer> ids) {
		this.ids =(ids);
		activity = act;
		requisitarProvas();
	}
	
	private void requisitarProvas() {
		try {
		    HttpParams params = new BasicHttpParams();
		    HttpConnectionParams.setStaleCheckingEnabled(params, false);
		    HttpConnectionParams.setConnectionTimeout(params, NetworkConnectionTimeout_ms);
		    HttpConnectionParams.setSoTimeout(params, NetworkConnectionTimeout_ms);
		    DefaultHttpClient httpClient = new DefaultHttpClient(params);
		
		    HttpPost postMethod = new HttpPost(LoginServiceUri);
		
		    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    ObjectOutputStream oos = new ObjectOutputStream(baos);
		    oos.writeObject(ids);
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
				            dataFromServlet = (HashMap<String,Integer>) ois.readObject();
							activity.mostarProvas(dataFromServlet);
				          }
				          catch (Exception e) {
				            Log.e(getClass().getSimpleName(), "problem processing post response", e);
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
		    StringWriter sw = new StringWriter();
		    PrintWriter pw = new PrintWriter(sw);
		    e.printStackTrace(pw);
		    Log.e(getClass().getSimpleName(), sw.getBuffer().toString(), e);
	  }
	}
	
}
