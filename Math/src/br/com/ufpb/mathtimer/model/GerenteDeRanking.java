package br.com.ufpb.mathtimer.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import android.app.Activity;

public class GerenteDeRanking {

	private int rankingId;

	public void setRankingId(int id) {
		this.rankingId = id;
	}

	public int getRankingId() {
		return this.rankingId;
	}

	public String getDadosDoRanking(Activity activity)
			throws FileNotFoundException, ClassNotFoundException, IOException {
		Ranking ranking = RankingDAO.carregarRanking(activity, rankingId);
		return ranking.getDadosDoRanking();
	}

	public Ranking getRanking(Activity activityAtual) {
		Ranking ranking = null;
		try {
			ranking = RankingDAO.carregarRanking(activityAtual, rankingId);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ranking;
	}

	public void zerarRanking(Activity activityAtual)
			throws FileNotFoundException, ClassNotFoundException, IOException {
		Ranking ranking = RankingDAO.carregarRanking(activityAtual, rankingId);
		ranking.zerarRanking();
		RankingDAO.salvarRanking(activityAtual, ranking, rankingId);
	}

	public void salvarRanking(Activity activityAtual, Ranking ranking) {
		try {
			RankingDAO.salvarRanking(activityAtual, ranking, rankingId);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean entrouNoRanking(Activity activity, Jogador jogador) {
		try {
			Ranking ranking = RankingDAO.carregarRanking(activity, rankingId);
			if (jogador.isNoRanking()) {
				ranking.atualizarRanking(jogador);
				RankingDAO.salvarRanking(activity, ranking, rankingId);
				return true;
			} else {
				if (ranking.jogadorEntraNoRanking(jogador)) {
					RankingDAO.salvarRanking(activity, ranking, rankingId);
					jogador.setNoRanking(true);
					return true;
				}
			}

		} catch (FileNotFoundException e) {

		} catch (ClassNotFoundException e) {

		} catch (IOException e) {

		}
		return false;
	}

}
