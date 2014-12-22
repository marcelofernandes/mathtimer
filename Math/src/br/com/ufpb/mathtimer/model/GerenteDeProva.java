package br.com.ufpb.mathtimer.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.widget.Toast;

public class GerenteDeProva {

	private Prova prova;
	private List<Questao> questoes;
	private List<Integer> indiceAleatorio;
	private Questao questaoAtual;
	private int indiceAtual;
	private Fachada fachada;

	public GerenteDeProva() {
		fachada = Fachada.getInstance();
		questoes = new ArrayList<Questao>();
		indiceAleatorio = new ArrayList<Integer>();
	}

	public void criarProvaPadrao(Activity activity) {
		this.questoes = CriadoraDeQuestoes.getListaDeQuestoes(activity);
		prova = new Prova(0);
//		prova.setTitulo("Prova Brasil 9º ano.");
		prova.setTitulo("Questões sobre números inteiros");
		prova.adicionarQuestoes(questoes);
		this.indiceAleatorio = new ArrayList<Integer>();
		setDadosDaProva();
	}

	private void setDadosDaProva() {
		questoes = prova.getListaDeQuestoes();
		this.indiceAleatorio.clear();
		for (int i = 0; i < questoes.size(); i++) {
			this.indiceAleatorio.add(i);
		}
		Collections.shuffle(indiceAleatorio);
		try {
			proximaQuestao();
		} catch (QuestoesAcabaramException e) {
			Toast.makeText(fachada.getActivity(), "Não há mais questões!", Toast.LENGTH_SHORT)
					.show();
		}
	}

	public void selecionarProva(Activity activity, int idDaProva) {
		try {
			prova = ProvasDAO.carregarProva(activity, idDaProva);
			setDadosDaProva();
		} catch (FileNotFoundException e) {
		} catch (ClassNotFoundException e) {
		} catch (IOException e) {
		}
	}

	public void salvarProva(Activity activity, int id) {
		try {
			ProvasDAO.salvarProva(activity, prova, id);
		} catch (FileNotFoundException e) {
		} catch (ClassNotFoundException e) {
		} catch (IOException e) {
		}
	}

	public void proximaQuestao() throws QuestoesAcabaramException {
		if (this.indiceAleatorio.size() == 0) {
			throw new QuestoesAcabaramException("Não há mais questões!");
		}
		this.indiceAtual = this.indiceAleatorio.get(0);
		this.questaoAtual = this.questoes.get(this.indiceAtual);
		this.indiceAleatorio.remove(0);
	}

	public void pularQuestao() throws QuestoesAcabaramException {
		this.indiceAleatorio.add(this.indiceAtual);
		proximaQuestao();
	}

	public List<Alternativa> getAlternativas() {
		return this.questaoAtual.getAlternativas();
	}

	public Alternativa getAlternativaCorreta() {
		return this.questaoAtual.getAlternativaCorreta();
	}

	public String getEnunciado() {
		return this.questaoAtual.getEnunciado();
	}

	public void setProva(Prova prova) {
		this.prova = prova;
	}

	public int getIdDaProva() {
		return prova.getId();
	}

	public int getQtdDeAlternativas() {
		return questaoAtual.getAlternativas().size();
	}

	public HashMap<String, Integer> getIdETituloDasProvas(Activity provaActivity) {
		return ProvasDAO.getIdETituloDasProvas(provaActivity);
	}

	public boolean hasImagem() {
		return questaoAtual.hasImagem();
	}

	public byte[] getImagem() {
		return questaoAtual.getImagem();
	}

	public void setImagem(byte[] imagem) {
		questaoAtual.setImagem(imagem);

	}

}
