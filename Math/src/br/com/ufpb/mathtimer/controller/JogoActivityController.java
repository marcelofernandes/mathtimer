package br.com.ufpb.mathtimer.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import br.com.ufpb.mathtimer.model.Alternativa;
import br.com.ufpb.mathtimer.model.Fachada;
import br.com.ufpb.mathtimer.model.QuestoesAcabaramException;
import br.com.ufpb.mathtimer.model.Tentativa;
import br.com.ufpb.mathtimer.view.JogoActivity;
import br.com.ufpb.mathtimer.view.MainActivity;
import br.com.ufpb.mathtimer.view.R;
import br.com.ufpb.mathtimer.view.RankingActivity;
import br.com.ufpb.mathtimer.view.ZoomActivity;

public class JogoActivityController {

	private AtomicBoolean continueThread;
	private ProgressBar bar;
	private Fachada fachada;
	private volatile Thread t;
	private Handler handler, handler2;
	private Message message;
	private int tempo;
	private Random random;
	private ArrayList<String> mensagens;
	private Activity jogoActivity;
	private int mwidth;
	private int myheight;

	public JogoActivityController(Activity activity) {
		this.jogoActivity = activity;
		this.fachada = Fachada.getInstance();
		this.continueThread = new AtomicBoolean(false);
		this.tempo = fachada.getTempo();
		mensagens = new ArrayList<String>();
		mensagens.add("Parab�ns!");
		mensagens.add("Muito bem!");
		mensagens.add("Excelente!");
		mensagens.add("Resposta correta!");
		criarHandle1();
		criarThread();
		criarHandle2();
		this.continueThread.set(true);
		this.t.start();
	    Display display = this.jogoActivity.getWindowManager().getDefaultDisplay(); 
	    this.mwidth = display.getWidth();
	    this.myheight = display.getHeight();
	}

	public void setProgressBar(ProgressBar bar) {
		this.bar = bar;
		this.bar.setProgress(tempo);
	}

	public void novoDesafio() {
		try {
			this.fachada.proximoDesafio();
		} catch (QuestoesAcabaramException e) {
			Toast.makeText(jogoActivity.getApplicationContext(),
					"Parab�ns! Voc� conseguiu responder todas as perguntas!", 2).show();
			continueThread.set(false);
			fachada.entrouNoRanking();
			//fachada.setJogoSalvo(false);
			ActivityController.mudarDeActivity(jogoActivity, MainActivity.class);
		}
		fachada.setTentativaDoJogador(Tentativa.PRIMEIRA);
		tempo = 0;
		fachada.setTempo(tempo);
	}

	public void pularDesafio() {
		try {
			this.fachada.pularDesafio();
			fachada.setTentativaDoJogador(Tentativa.PRIMEIRA);
			tempo = 0;
			fachada.setTempo(tempo);
		} catch (QuestoesAcabaramException e) {
			Toast.makeText(jogoActivity.getApplicationContext(),
					"Parab�ns! Voc� conseguiu responder todas as perguntas!", 2).show();
			ActivityController.mudarDeActivity(jogoActivity, MainActivity.class);
		}
	}

	public String getMensagemDeAcerto() {
		random = new Random();
		return mensagens.get(random.nextInt(4));
	}

	public void acertouQuestaoCodigo(final TextView score, final JogoActivity jogo) {
		fachada.incrementarPontuacaoDoJogador();
 		fachada.incrementarTempoDasQuestoes(tempo);
 		
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(jogoActivity);
	      alertDialogBuilder.setMessage(getMensagemDeAcerto());
	      alertDialogBuilder.setNeutralButton("Ok", 
	      new DialogInterface.OnClickListener() {
	         @Override
	         public void onClick(DialogInterface arg0, int arg1) {
	     		score.setText("Pontos: " + getPontuacaoDoJogador());
	     		novoDesafio();
	     		jogo.criarTela();
	         }
	      });
	      AlertDialog alertDialog = alertDialogBuilder.create();
	      alertDialog.show();
	      
	      WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
	      lp.copyFrom(alertDialog.getWindow().getAttributes());
	      lp.width = LayoutParams.WRAP_CONTENT;
	      lp.height = LayoutParams.WRAP_CONTENT;
	      alertDialog.getWindow().setAttributes(lp);
	}

	public void errouQuestaoCodigo(final Button botao) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(jogoActivity);
	      alertDialogBuilder.setMessage("Resposta Errada! Tente novamente!");
	      alertDialogBuilder.setNeutralButton("Ok", 
	      new DialogInterface.OnClickListener() {
	         @Override
	         public void onClick(DialogInterface arg0, int arg1) {
	        	 incrementarTentativas();
	        	 botao.setBackgroundResource(R.drawable.resposta);
	         }
	      });
	      AlertDialog alertDialog = alertDialogBuilder.create();
	      alertDialog.show();
	
	      WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
	      lp.copyFrom(alertDialog.getWindow().getAttributes());
	      lp.width = LayoutParams.WRAP_CONTENT;
	      lp.height = LayoutParams.WRAP_CONTENT;
	      alertDialog.getWindow().setAttributes(lp);
	}

	public void btMenuCodigo() {
		continueThread.set(false);
		fachada.entrouNoRanking();
		fachada.setJogoSalvo(true);
		fachada.setTentativaDoJogador(Tentativa.PRIMEIRA);
		fachada.setTempo(0);
		
		ActivityController.mudarDeActivity(jogoActivity, MainActivity.class);
		//jogoActivity.moveTaskToBack(true);
		//jogoActivity.finish();
	}

	public void btSairCodigo() {
		continueThread.set(false);
		if (fachada.entrouNoRanking()) {
			//fachada.setJogoSalvo(true);
			fachada.setTentativaDoJogador(Tentativa.PRIMEIRA);
			fachada.setTempo(0);
			ActivityController.mudarDeActivity(jogoActivity,RankingActivity.class);
		} else {
			jogoActivity.finish();
		}
	}

	public void imageViewCodigo() {
		continueThread.set(false);
		ActivityController.mudarDeActivity(jogoActivity, ZoomActivity.class);
	}

	public boolean hasImagem() {
//		if (fachada.hasImagem()) {
//			if (!(fachada.getMostrouMensagem())) {
//				Toast.makeText(jogoActivity.getApplicationContext(),
//						"Clique na imagem para dar o zoom!", 4).show();
//				fachada.setMostrouMensagem(true);
//			}
//			return true;
//		}
//		return false;
		return fachada.hasImagem();
	}

	public void incrementarTentativas() {
		switch (fachada.getTentativaDoJogador()) {
		case PRIMEIRA:
			fachada.setTentativaDoJogador(Tentativa.SEGUNDA);
			break;
		case SEGUNDA:
			fachada.setTentativaDoJogador(Tentativa.TERCEIRA);
			break;
		case TERCEIRA:
			fachada.setTentativaDoJogador(Tentativa.QUARTA);
			break;
		default:
			break;
		}
	}

	public boolean acertouQuestao(int index) {
		int idAlternativaSelecionada = fachada.getAlternativas().get(index)
				.getId();
		int idAlternativaCerta = fachada.getAlternativaCorreta().getId();
		return idAlternativaSelecionada == idAlternativaCerta;
	}

	public void criarHandle1() {
		this.handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (bar != null)
					tempo++;
				fachada.setTempo(tempo);
				bar.setProgress(tempo);
			}
		};
	}

	public void criarHandle2() {
		this.handler2 = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 1) {
					Toast.makeText(jogoActivity.getApplicationContext(),
							"Tempo encerrado!", 2).show();
					Thread.interrupted();
					ActivityController.mudarDeActivity(jogoActivity,
							MainActivity.class);
				}
			}
		};
	}

	public void criarThread() {
		this.t = new Thread(new Runnable() {
			@Override
			public void run() {
				while (continueThread.get()) {
					if (tempo < 100) {
						try {
							if (handler != null)
								handler.sendMessage(handler.obtainMessage());
							Thread.sleep(1000);
						} catch (Throwable t) {
						}
					} else {
						message = new Message();
						message.what = 1;
						handler2.sendMessage(message);
						continueThread.set(false);
					}
				}
			}
		});
	}

	public void pararJogo() {
		this.continueThread.set(false);
	}

	public String getNomeDoJogador() {
		return fachada.getNomeDoJogador();
	}

	public int getPontuacaoDoJogador() {
		return fachada.getPontuacaoDoJogador();
	}

	public String getEnunciado() {
		return fachada.getEnunciado();
	}

	public int getQtdDeAlternativas() {
		return fachada.getQtdDeAlternativas();
	}
	
	public int getQtdQuestoes(){
		return fachada.getQtdQuestoes();
	}

	public List<Alternativa> getAlternativas() {
		return fachada.getAlternativas();
	}

	public byte[] getImagem() {
		return fachada.getImagem();
	}

	public int getQuestaoAtual() {
		return fachada.getQuestaoAtual();
	}
	
	public void incrementarQuestaoAtual() {
		fachada.incrementarQuestaoAtual();
	}

}
