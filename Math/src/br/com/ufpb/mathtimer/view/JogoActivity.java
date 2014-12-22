package br.com.ufpb.mathtimer.view;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import br.com.ufpb.mathtimer.controller.JogoActivityController;

public class JogoActivity extends Activity {

	private JogoActivityController jogoActivityController;
	private TextView nome, score, pergunta;
	private ImageView imageView;
	private Button btmenu, btproximo, btsair;
	private List<Button> btAlternativas;
	private ProgressBar barraProgresso;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		jogoActivityController = new JogoActivityController(this);
		criarTela();
	}

	public void onPause() {
		super.onPause();
		jogoActivityController.pararJogo();
	}

	public void criarTela() {
		View view = criarView();
		setContentView(view);
	}

	private ViewGroup criarView() {
		ScrollView view = new ScrollView(this);
		view.setPadding(4, 4, 4, 4);

		LinearLayout layout1 = new LinearLayout(this);
		layout1.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		layout1.setKeepScreenOn(true);
		layout1.setOrientation(LinearLayout.VERTICAL);

		LinearLayout layout2 = new LinearLayout(this);
		layout2.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));

		nome = new TextView(this);
		nome.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		nome.setPadding(4, 4, 4, 4);
		nome.setTextAppearance(this, android.R.attr.textAppearanceLarge);
		nome.setText(jogoActivityController.getNomeDoJogador());

		LinearLayout layout3 = new LinearLayout(this);
		layout3.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		layout3.setGravity(Gravity.RIGHT);

		score = new TextView(this);
		score.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		score.setGravity(Gravity.RIGHT);
		score.setPadding(4, 4, 4, 4);
		score.setTextAppearance(this, android.R.attr.textAppearanceLarge);
		score.setText("Pontos: "
				+ jogoActivityController.getPontuacaoDoJogador());

		pergunta = new TextView(this);
		pergunta.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		pergunta.setGravity(Gravity.FILL_HORIZONTAL);
		pergunta.setPadding(2, 4, 2, 4);
		pergunta.setTextAppearance(this, android.R.attr.textAppearanceLarge);
		pergunta.setText(jogoActivityController.getEnunciado());

		if (jogoActivityController.hasImagem()) {
			imageView = new ImageView(this);
			imageView.setLayoutParams(new LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			imageView.setPadding(4, 1, 4, 1);
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
		}

		btAlternativas = new ArrayList<Button>();
		for (int i = 0; i < jogoActivityController.getQtdDeAlternativas(); i++) {
			final Button botao = new Button(this);
			//MediaPlayer player;
			botao.setText(""
					+ this.jogoActivityController.getAlternativas().get(i)
							.getTexto());
			final int index = i;
			botao.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					if (jogoActivityController.acertouQuestao(index)) {
						jogoActivityController.acertouQuestaoCodigo();
						score.setText("Pontos: "
								+ jogoActivityController
										.getPontuacaoDoJogador());
						jogoActivityController.novoDesafio();
						criarTela();
					} else {
						jogoActivityController.errouQuestaoCodigo();
					}
				}
			});
			btAlternativas.add(botao);
		}

		barraProgresso = new ProgressBar(this, null,
				android.R.attr.progressBarStyleHorizontal);
		jogoActivityController.setProgressBar(barraProgresso);

		this.btmenu = new Button(this);
		this.btproximo = new Button(this);
		this.btsair = new Button(this);

		this.btmenu.setText("menu");
		this.btproximo.setText("Próximo");
		this.btsair.setText("Sair");

		this.btmenu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				jogoActivityController.btMenuCodigo();
			}
		});

		this.btproximo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				jogoActivityController.pularDesafio();
				criarTela();
			}
		});

		this.btsair.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				jogoActivityController.btSairCodigo();
			}
		});

		LinearLayout layout4 = new LinearLayout(this);
		layout4.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		layout4.setGravity(Gravity.CENTER);
		layout4.setPadding(4, 4, 4, 4);

		layout3.addView(score);
		layout2.addView(nome);
		layout2.addView(layout3);
		layout1.addView(layout2);
		layout1.addView(pergunta);
		if (jogoActivityController.hasImagem())
			layout1.addView(imageView);

		for (int i = 0; i < jogoActivityController.getQtdDeAlternativas(); i++) {
			layout1.addView(btAlternativas.get(i));
		}

		layout1.addView(barraProgresso);
		layout4.addView(btmenu);
		layout4.addView(btproximo);
		layout4.addView(btsair);
		layout1.addView(layout4);

		view.addView(layout1);

		return view;
	}

}
