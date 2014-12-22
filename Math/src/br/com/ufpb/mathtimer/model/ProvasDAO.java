package br.com.ufpb.mathtimer.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import android.app.Activity;
import android.widget.Toast;

public class ProvasDAO {

	private static HashMap<Integer, Prova> provas = new HashMap<Integer, Prova>();

	public static Prova carregarProva(Activity provaActivity, int idDaProva)
			throws ClassNotFoundException, FileNotFoundException, IOException {
		File file = provaActivity.getFileStreamPath("prova.txt");
		Prova prova = null;
		if (!file.exists()) {
			criarProva(provaActivity);
		}
		provas.putAll(getProvas(provaActivity));
		prova = provas.get(idDaProva);
		return prova;
	}

	public static HashMap<String, Integer> getIdETituloDasProvas(
			Activity provaActivity) {
		File file = provaActivity.getFileStreamPath("prova.txt");
		HashMap<String, Integer> idETitulo = new HashMap<String, Integer>();
		if (!file.exists()) {
			criarProva(provaActivity);
		}
		provas.putAll(getProvas(provaActivity));
		Set<Integer> set = provas.keySet();
		Iterator<Integer> it = set.iterator();
		while (it.hasNext()) {
			int id = it.next();
			Prova prova = provas.get(id);
			idETitulo.put(prova.getTitulo(), prova.getId());
		}
		return idETitulo;
	}

	public static void salvarProva(Activity provaActivity, Prova prova,
			int idDaProva) throws ClassNotFoundException,
			FileNotFoundException, IOException {
		File file = provaActivity.getFileStreamPath("prova.txt");
		if (!file.exists()) {
			criarProva(provaActivity);
		}

		provas.putAll(getProvas(provaActivity));

		if (provas == null) {
			provas = new HashMap<Integer, Prova>();
		}
		provas.put(idDaProva, prova);
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(provas);
		oos.flush();
		fos.close();
		oos.close();
	}

	public static HashMap<Integer, Prova> getProvas(Activity provaActivity) {
		try {
			File file = provaActivity.getFileStreamPath("prova.txt");
			if (!file.exists()) {
				criarProva(provaActivity);
			}
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			provas = (HashMap<Integer, Prova>) ois.readObject();
			fis.close();
			ois.close();
		} catch (ClassNotFoundException e) {
			Toast.makeText(provaActivity,
					"getProvas causando ClassNotFoundException",
					Toast.LENGTH_LONG).show();

		} catch (IOException e) {
			Toast.makeText(provaActivity, "getProvas causando IOException dao",
					Toast.LENGTH_LONG).show();
		}
		return provas;
	}

	public int getQtdDeProvas(Activity provaActivity)
			throws ClassNotFoundException, FileNotFoundException, IOException {
		return getProvas(provaActivity).size();
	}

	public static void criarProva(Activity provaActivity) {
		try {
			File file = provaActivity.getFileStreamPath("prova.txt");
			file.createNewFile();
			provas = new HashMap<Integer, Prova>();
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(provas);
			oos.flush();
			fos.close();
			oos.close();
			Toast.makeText(provaActivity, "a prova foi criada",
					Toast.LENGTH_LONG).show();

		} catch (IOException e) {
			Toast.makeText(provaActivity, "a prova nao foi criada",
					Toast.LENGTH_LONG).show();

		}

	}

	public static void deletarProva(Activity provaActivity, Integer idDaProva) {
		try {
			File file = provaActivity.getFileStreamPath("prova.txt");
			if (!file.exists()) {
				return;
			}
			provas.putAll(getProvas(provaActivity));
			if (provas == null) {
				provas = new HashMap<Integer, Prova>();
			}
			provas.remove(idDaProva);
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(provas);
			oos.flush();
			fos.close();
			oos.close();
		} catch (IOException e) {
			Toast.makeText(provaActivity, "a prova nao foi criada",
					Toast.LENGTH_LONG).show();

		}
	}
}
