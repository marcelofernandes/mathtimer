package br.com.ufpb.mathtimer.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;

public class Fachada {

	private static Fachada instance = null;
	private GerenteDeProva gerenteDeProva;
	private GerenteDeRanking gerenteDeRanking;
	private transient Activity activityAtual;
	private Jogador jogador;
	private int tempo;
	private boolean jogoSalvo;
	private boolean mostrouMensagem;
	private int idProvaAtual;

	private Fachada() {
		criarJogador();
		this.mostrouMensagem = false;
		this.gerenteDeProva = null;
		this.gerenteDeRanking = null;
	}

	public static Fachada getInstance() {
		if (instance == null) {
			instance = new Fachada();
		}
		return instance;
	}

	public Activity getActivity() {
		return activityAtual;
	}

	public void setActivity(Activity activity) {
		this.activityAtual = activity;
	}

	public void selecionarProva(int idDaProva) {
		gerenteDeProva.selecionarProva(activityAtual, idDaProva);
	}

	public void proximoDesafio() throws QuestoesAcabaramException {
		this.gerenteDeProva.proximaQuestao();
	}

	public void pularDesafio() throws QuestoesAcabaramException {
		this.gerenteDeProva.pularQuestao();
	}

	public byte[] getImagem() {
		return this.gerenteDeProva.getImagem();
	}

	public void setImageId(byte[] imagem) {
		this.gerenteDeProva.setImagem(imagem);
	}

	public int getIdDaProva() {
		return gerenteDeProva.getIdDaProva();
	}

	public String getEnunciado() {
		return this.gerenteDeProva.getEnunciado();
	}

	public Alternativa getAlternativaCorreta() {
		return this.gerenteDeProva.getAlternativaCorreta();
	}

	public List<Alternativa> getAlternativas() {
		return this.gerenteDeProva.getAlternativas();
	}

	public int getQtdDeAlternativas() {
		return gerenteDeProva.getQtdDeAlternativas();
	}

	public String getNomeDoJogador() {
		return this.jogador.getNome();
	}

	public void setNomeDoJogador(String nome) {
		this.jogador.setNome(nome);
	}

	public int getPontuacaoDoJogador() {
		return this.jogador.getPontuacao();
	}

	public void incrementarPontuacaoDoJogador() {
		this.jogador.incrementarPontuacao();
	}

	public void incrementarTempoDasQuestoes(int tempo) {
		this.jogador.incrementarTempoDasQuestoes(tempo);
	}

	public String getDadosDoRanking() throws FileNotFoundException,
			ClassNotFoundException, IOException {
		return gerenteDeRanking.getDadosDoRanking(activityAtual);
	}

	public Ranking getRanking() {
		return gerenteDeRanking.getRanking(activityAtual);
	}

	public void setTentativaDoJogador(Tentativa t) {
		this.jogador.setTentativa(t);
	}

	public Tentativa getTentativaDoJogador() {
		return jogador.getTentativa();
	}

	public void setMostrouMensagem(boolean m) {
		this.mostrouMensagem = m;
	}

	public boolean getMostrouMensagem() {
		return this.mostrouMensagem;
	}

	public void zerarRanking() throws FileNotFoundException,
			ClassNotFoundException, IOException {
		gerenteDeRanking.zerarRanking(activityAtual);
	}

	public Jogador getJogador() {
		return this.jogador;
	}

	public void criarGerenteDeProva() {
		if (this.gerenteDeProva == null)
			this.gerenteDeProva = new GerenteDeProva();
	}

	public void setJogoSalvo(boolean salvo) {
		this.jogoSalvo = salvo;
	}

	public boolean isJogoSalvo() {
		return this.jogoSalvo;
	}

	public boolean entrouNoRanking() {
		return gerenteDeRanking.entrouNoRanking(activityAtual, jogador);
	}

	public void criarJogador() {
		this.jogador = new Jogador();
		this.jogoSalvo = false;
		this.tempo = 0;
		this.idProvaAtual = 0;
	}

	public int getTempo() {
		return this.tempo;
	}

	public void setTempo(int t) {
		this.tempo = t;
	}

	public void criarProvaPadrao() {
		gerenteDeProva.criarProvaPadrao(activityAtual);

	}

	public void salvarProva() {
		gerenteDeProva
				.salvarProva(activityAtual, gerenteDeProva.getIdDaProva());

	}

	public void setRankingId(int id) {
		gerenteDeRanking.setRankingId(id);
	}

	public int getRankingId() {
		return gerenteDeRanking.getRankingId();
	}

	public void criarGerenteDeRanking() {
		if (this.gerenteDeRanking == null)
			this.gerenteDeRanking = new GerenteDeRanking();
	}

	public boolean hasImagem() {
		return this.gerenteDeProva.hasImagem();
	}

	public int getIdProvaAtual() {
		return idProvaAtual;
	}

	public void setIdProvaAtual(int idProvaAtual) {
		this.idProvaAtual = idProvaAtual;
	}

}
