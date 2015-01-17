package br.com.ufpb.mathtimer.controller;

import java.util.HashMap;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import br.com.ufpb.mathtimer.model.Fachada;
import br.com.ufpb.mathtimer.model.ProvasDAO;
import br.com.ufpb.mathtimer.view.MainActivity;

public class DeleteProvaActivityController {

	private Activity selecaoDeProvaActivity;
	private Fachada fachada;
	private HashMap<String, Integer> idsETitulos;
	private Activity activity;

	public DeleteProvaActivityController(Activity activity) {
		this.selecaoDeProvaActivity = activity;
		this.fachada = Fachada.getInstance();
		this.activity = activity;
	}

	public ArrayAdapter<String> btGetAdapterCodigo() {
		idsETitulos = ProvasDAO.getIdETituloDasProvas(activity);
		if (idsETitulos.size() == 0) {
			Toast.makeText(activity.getApplicationContext(),
					"Não há provas cadastradas!", 2).show();
		}
		String[] arrayDeTitulos = (String[]) idsETitulos.keySet().toArray(
				new String[0]);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity,
				android.R.layout.simple_list_item_1, android.R.id.text1,
				arrayDeTitulos);
		return adapter;
	}

	public void btDeletarCodigo(String itemvalue) {
		int idSelecionado = idsETitulos.get(itemvalue);
		ProvasDAO.deletarProva(fachada.getActivity(), idSelecionado);
		Toast.makeText(fachada.getActivity(),
				"Prova deletada com sucesso", Toast.LENGTH_SHORT).show();
		fachada.setIdProvaAtual(0);
		fachada.setJogoSalvo(false);
	}

	public void btVoltarCodigo() {
		ActivityController.mudarDeActivity(selecaoDeProvaActivity,
				MainActivity.class);

	}

	public void btSairCodigo() {
		selecaoDeProvaActivity.finish();
	}
}
