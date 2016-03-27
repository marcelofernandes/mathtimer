package br.com.ufpb.mathtimer.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import android.app.Activity;
import android.widget.Toast;

public class RankingDAO {

	private static HashMap<Integer, Ranking> rankings = new HashMap<Integer, Ranking>();

	public static Ranking carregarRanking(Activity rankingActivity,
			int idDoRanking) throws ClassNotFoundException,
			FileNotFoundException, IOException {
		File file = rankingActivity.getFileStreamPath("ranking.txt");
		Ranking ranking = null;
		if (!file.exists()) {
			criarRanking(rankingActivity);
		}
		rankings.putAll(getRankings(rankingActivity));
		ranking = rankings.get(idDoRanking);
		if (ranking == null) {
			ranking = new Ranking(idDoRanking);
			salvarRanking(rankingActivity, ranking, idDoRanking);
		}
		return ranking;
	}

	public static void salvarRanking(Activity rankingActivity, Ranking ranking,
			int idDoRanking) throws ClassNotFoundException,
			FileNotFoundException, IOException {
		File file = rankingActivity.getFileStreamPath("ranking.txt");

		if (!file.exists()) {
			criarRanking(rankingActivity);
		}
		rankings.putAll(getRankings(rankingActivity));
		if (rankings == null) {
			rankings = new HashMap<Integer, Ranking>();
		}
		rankings.put(idDoRanking, ranking);
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(rankings);
		oos.flush();
		fos.close();
		oos.close();
	}

	public static HashMap<Integer, Ranking> getRankings(Activity rankingActivity) {
		HashMap<Integer, Ranking> r = null;
		try {
			File file = rankingActivity.getFileStreamPath("ranking.txt");
			if (!file.exists()) {
				criarRanking(rankingActivity);
			}
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			r = (HashMap<Integer, Ranking>) ois.readObject();
			fis.close();
			ois.close();
		} catch (ClassNotFoundException e) {
			Toast.makeText(rankingActivity, "getRankings causando IOException",
					Toast.LENGTH_LONG).show();

		} catch (IOException e) {
			Toast.makeText(rankingActivity, "getRankings causando IOException",
					Toast.LENGTH_LONG).show();
		}
		return r;
	}

	public static void criarRanking(Activity rankingActivity) {
		try {
			File file = rankingActivity.getFileStreamPath("ranking.txt");
			file.createNewFile();
			rankings = new HashMap<Integer, Ranking>();
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(rankings);
			oos.flush();
			fos.close();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void deletarRanking(Activity rankingActivity, int idDoRanking) {
		File file = rankingActivity.getFileStreamPath("ranking.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			criarRanking(rankingActivity);
		}
		rankings = getRankings(rankingActivity);
		rankings.remove(idDoRanking);
	}
}
