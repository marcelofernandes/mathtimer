package br.com.ufpb.mathtimer.view;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import br.com.ufpb.mathtimer.controller.JogoActivityController;

public class JogoActivity extends Activity {

	private JogoActivityController jogoActivityController;
	private TextView score, pergunta, match;
	private ImageView imageView;
	private Button btmenu;
	private List<Button> btAlternativas;
	private ProgressBar barraProgresso;
	private JogoActivity jogo;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		jogoActivityController = new JogoActivityController(this);
		jogo = this;
		criarTela();
	}

	public void onPause() {
		super.onPause();
		jogoActivityController.pararJogo();
	}
	
	@Override
	public void onBackPressed() {
		jogoActivityController.btMenuCodigo();
	}

	public void criarTela() {
		View view = criarView();
		setContentView(view);
	}

	private ViewGroup criarView() {
		final Animation animScale = AnimationUtils.loadAnimation(this,
			    R.layout.anim_scale);
		Display display = getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();

		int layoutPergunta = 0;
		int layoutBotao = 0;
		int layoutImagem = 0;
		
		if(jogoActivityController.hasImagem()){
			layoutPergunta = (int) (height * 0.3);
			layoutBotao = (int) (height * 0.2);
			layoutImagem = (int) (height * 0.25);
		}else{
			layoutPergunta = (int) (height * 0.45);
			layoutBotao = (int) (height * 0.25);
			layoutImagem = (int) (height * 0.01);
		}
		
		int metadeDaTela = width/2;
		int vinteCinco = metadeDaTela/2;
		int setentaEcinco = metadeDaTela + vinteCinco;
		
		ScrollView view = new ScrollView(this);
		view.setBackgroundResource(R.drawable.menubg);
		view.setPadding(4, 4, 4, 4);
		

		LinearLayout layout1 = new LinearLayout(this);
		layout1.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		layout1.setKeepScreenOn(true);
		layout1.setOrientation(LinearLayout.VERTICAL);
		layout1.setGravity(Gravity.CENTER_HORIZONTAL);
		
		//FrameLayout frameLayout = new FrameLayout(this);
		LinearLayout l = new LinearLayout(this);
		l.setBackgroundResource(R.drawable.barrasuperior);
		l.setLayoutParams(new LayoutParams(width,
				(int) (height * 0.1)));
		l.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);

		LinearLayout layout2 = new LinearLayout(this);
		layout2.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				layoutPergunta));
		//TODO componentes dentro deste tem q ajustar o tamanho
		layout2.setPadding(1, 1, 1, 1);
		layout2.setGravity(Gravity.TOP);
		
		score = new TextView(this);
		score.setBackgroundResource(R.drawable.pont); //pontuação
		score.setLayoutParams(new LayoutParams(vinteCinco,
				LayoutParams.WRAP_CONTENT));
		score.setTextAppearance(this, android.R.attr.textAppearanceLarge);
		score.setGravity(Gravity.BOTTOM|Gravity.CENTER);
		score.setTextSize((int) (vinteCinco * 0.13));
		score.setText("" + jogoActivityController.getPontuacaoDoJogador());
		

		LinearLayout layout3 = new LinearLayout(this);
		layout3.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				(int) (height * 0.05) ));

		ScrollView view2 = new ScrollView(this);
		int padding = (int)(layoutPergunta*0.07);
		view2.setLayoutParams(new LayoutParams(setentaEcinco-10,
				layoutPergunta - (int)(layoutPergunta*0.1)));
		view2.setPadding(padding, padding, padding, padding);
		view2.setBackgroundResource(R.drawable.bannerperguntas);

		
		pergunta = new TextView(this);
		pergunta.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		//pergunta.setGravity(Gravity.RIGHT);
		//pergunta.setVerticalScrollBarEnabled(true);
		//pergunta.setPadding(padding, padding, padding, padding*2);
		pergunta.setTextSize((int)(layoutPergunta*0.07));
		pergunta.setTypeface(null, Typeface.BOLD);
		pergunta.setText(jogoActivityController.getEnunciado());
	

		view2.addView(pergunta);
		
		if (jogoActivityController.hasImagem()) {
			imageView = new ImageView(this);
			imageView.setLayoutParams(new LayoutParams(
					LayoutParams.WRAP_CONTENT, layoutImagem));
			imageView.setPadding(1, 2, 1, 2);
			ByteArrayInputStream is = new ByteArrayInputStream(
					jogoActivityController.getImagem());
			Bitmap b = BitmapFactory.decodeStream(is);
			imageView.setImageBitmap(b);

			this.imageView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					jogoActivityController.imageViewCodigo();
				}
			});
		}else{
			imageView = new ImageView(this);
			imageView.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, layoutImagem));
		}
		
//
//		btAlternativas = new ArrayList<Button>();
//		for (int i = 0; i < jogoActivityController.getQtdDeAlternativas(); i++) {
//			final Button botao = new Button(this);
//			botao.setBackgroundResource(R.drawable.resposta);
//			botao.setWidth(0);
//			botao.setText(""
//					+ this.jogoActivityController.getAlternativas().get(i)
//							.getTexto());
//			final int index = i;
//			botao.setOnClickListener(new View.OnClickListener() {
//				@Override
//				public void onClick(View arg0) {
//					if (jogoActivityController.acertouQuestao(index)) {
//						botao.setBackgroundResource(R.drawable.respostacorreta);
//						jogoActivityController.acertouQuestaoCodigo(pergunta, jogo);
//						questaoAtual++;
//					} else {
//						botao.setBackgroundResource(R.drawable.respostaerrada);
//						jogoActivityController.errouQuestaoCodigo(botao);
//					}
//				}
//			});
//			btAlternativas.add(botao);
//		}
		
		barraProgresso = new ProgressBar(this, null,
				android.R.attr.progressBarStyleHorizontal);
		Drawable d = this.getApplication().getResources().getDrawable(R.layout.custom_progressbar);
		barraProgresso.setProgressDrawable(d);
		
		barraProgresso.setLayoutParams(new LayoutParams( width/2, LayoutParams.MATCH_PARENT));
		barraProgresso.setPadding(1, 1, 1, 1);
		l.addView(barraProgresso);
		
		match = new TextView(this);
		match.setLayoutParams(new LayoutParams(width/4, LayoutParams.MATCH_PARENT));
		match.setTextAppearance(this, android.R.attr.textAppearanceLarge);
		match.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
		match.setTextSize(30);
		match.setText("" + jogoActivityController.getQuestaoAtual() + "/" + jogoActivityController.getQtdQuestoes());
		
		l.addView(match);
		
		jogoActivityController.setProgressBar(barraProgresso);

		this.btmenu = new Button(this);
		this.btmenu.setBackgroundResource(R.drawable.voltar);
		this.btmenu.setLayoutParams(new LayoutParams( (int) (layoutBotao*0.7), (int) (layoutBotao*0.7)));

		this.btmenu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				btmenu.startAnimation(animScale);
				jogoActivityController.btMenuCodigo();
			}
		});

		LinearLayout layout4 = new LinearLayout(this);
		layout4.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		//TODO componentes dentro deste tem q ajustar o tamanho
		layout4.setGravity(Gravity.BOTTOM);
		layout4.setPadding(1, 1, 1, 1);

//		frameLayout.addView(barraSuperior);
//		frameLayout.addView(barraProgresso);
		
		layout1.addView(l);
		layout1.addView(layout3);
		//layout3.addView(view2);
		layout2.addView(score);
		layout2.addView(view2);
		layout1.addView(layout2);

		//if (jogoActivityController.hasImagem())
			layout1.addView(imageView);
	
		int qtd = jogoActivityController.getQtdDeAlternativas();
		int linhas = ( qtd%2 == 0 ? qtd/2 : qtd/2 + 1);
		int alturaBt = (int) ((layoutBotao)/linhas);

		btAlternativas = new ArrayList<Button>();
		for (int i = 0; i < jogoActivityController.getQtdDeAlternativas(); i++) {
			final Button botao = new Button(this);
			botao.setBackgroundResource(R.drawable.resposta);
			//botao.setWidth(0);
			botao.setLayoutParams(new LayoutParams(alturaBt*4,
					alturaBt));

			botao.setTextSize(alturaBt/3);
			botao.setTypeface(null, Typeface.BOLD);
			botao.setText(""
					+ this.jogoActivityController.getAlternativas().get(i)
							.getTexto());
			final int index = i;
			botao.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					if (jogoActivityController.acertouQuestao(index)) {
						botao.setBackgroundResource(R.drawable.respostacorreta);
						jogoActivityController.acertouQuestaoCodigo(pergunta, jogo);
						jogoActivityController.incrementarQuestaoAtual();
					} else {
						botao.setBackgroundResource(R.drawable.respostaerrada);
						jogoActivityController.errouQuestaoCodigo(botao);
					}
				}
			});
			btAlternativas.add(botao);
		}

		
		LinearLayout layout5 = null;		
		LinearLayout layout6 = new LinearLayout(this);
		layout6.setOrientation(LinearLayout.VERTICAL);
	
		layout6.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));

		for (int i = 0, j = 0; i < linhas; i++) {
			layout5 = new LinearLayout(this);
			layout5.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT));
			layout5.setGravity(Gravity.CENTER);
			layout5.addView(btAlternativas.get(j));
			j++;
			if(j != qtd){
				layout5.addView(btAlternativas.get(j));
				j++;
			}
			layout6.addView(layout5);
		}
		
		layout4.addView(btmenu);
		layout4.addView(layout6);
		layout1.addView(layout4);
		view.addView(layout1);

		return view;
	}
	

}
