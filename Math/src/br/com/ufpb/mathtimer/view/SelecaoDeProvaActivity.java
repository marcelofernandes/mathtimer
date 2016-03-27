package br.com.ufpb.mathtimer.view;

import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import br.com.ufpb.mathtimer.controller.ConfigActivityController;
import br.com.ufpb.mathtimer.controller.InstrucoesActivityController;
import br.com.ufpb.mathtimer.controller.NomeJogadorActivityController;
import br.com.ufpb.mathtimer.controller.RankingActivityController;
import br.com.ufpb.mathtimer.controller.SelecaoDeProvaActivityController;
import br.com.ufpb.mathtimer.controller.SelecaoDeRankingActivityController;
import br.com.ufpb.mathtimer.model.Fachada;
import br.com.ufpb.mathtimer.model.ProvasDAO;

public class SelecaoDeProvaActivity extends Activity {

	private SelecaoDeProvaActivityController selecaoDeProvaActivityController;
	private Fachada fachada;
	private HashMap<String, Integer> idsETitulos;
	private Button btVoltar, btJogar;
	private EditText nome;
	private ListView listaDeViews;
	private TextView abaConteudo, abaJogador;
	private int idSelecionado;
	private NomeJogadorActivityController nomeJogadorActivityController;
	private LinearLayout layoutConteudo;
	private String texto;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		criarTela();

	}
	
	public void criarTela() {
		View view = criarView();
		setContentView(view);
	}
	
	private ViewGroup criarView() {
		final Animation animScale = AnimationUtils.loadAnimation(this,
			    R.layout.anim_scale);
		this.fachada = Fachada.getInstance();
		this.fachada.setActivity(this);
		selecaoDeProvaActivityController = new SelecaoDeProvaActivityController(
				this);
		nomeJogadorActivityController = new NomeJogadorActivityController(this);
		idSelecionado = -1;
		texto = "Jogador";
		
		Display display = getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();
		
		int setentaW = (int) (width * 0.7);	
		final int sessentaH = (int) (height * 0.5);
		
		ScrollView view = new ScrollView(this);
		view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		view.setBackgroundResource(R.drawable.menubg);
		view.setKeepScreenOn(true);
		view.setPadding(4, 4, 4, 4);
		

		LinearLayout layout1 = new LinearLayout(this);
		layout1.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		layout1.setOrientation(LinearLayout.VERTICAL);
		layout1.setGravity(Gravity.CENTER_HORIZONTAL);

		
		TextView logo = new TextView(this);
		logo.setBackgroundResource(R.drawable.barrasuperior);
		logo.setLayoutParams(new LayoutParams(width,
				(int) (height * 0.1)));
		logo.setGravity(Gravity.CENTER_HORIZONTAL);
		logo.setPadding(10, 0, 10, 0);
		
		LinearLayout layout6 = new LinearLayout(this);
		layout6.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				(int) (height * 0.5) ));
		layout6.setOrientation(LinearLayout.HORIZONTAL);
		
		LinearLayout layout7 = new LinearLayout(this);
		layout7.setLayoutParams(new LayoutParams((int) (width * 0.13),
				LayoutParams.MATCH_PARENT));
		
		FrameLayout frame = new FrameLayout(this);
		frame.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		frame.setPadding(0, 2, 0, 2);
		
		
		LinearLayout layout8 = new LinearLayout(this);
		layout8.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				(int) (height * 0.05) ));
		
		LinearLayout layout9 = new LinearLayout(this);
		layout9.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				(int) (height * 0.1) ));
		
		int padding = (int) (sessentaH * 0.1);
		
		layoutConteudo = new LinearLayout(this);//linear1
		this.layoutConteudo.setLayoutParams(new LayoutParams(setentaW,sessentaH - padding));
		this.layoutConteudo.setGravity(Gravity.CENTER_HORIZONTAL);
		this.layoutConteudo.setBackgroundResource(R.drawable.conteudo);
		this.layoutConteudo.setOrientation(LinearLayout.HORIZONTAL);
		this.layoutConteudo.setPadding(0, 5, 0, 0);

		
		FrameLayout frame2 = new FrameLayout(this);//frame2
		frame2.setLayoutParams(new LayoutParams(new LayoutParams(setentaW,sessentaH - padding)));
		frame2.setForegroundGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);

		nome = new EditText(this);//editText1
		nome.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		nome.setBackgroundResource(R.drawable.nome);
		nome.setFocusable(true);
		nome.setFocusableInTouchMode(true);
		nome.setClickable(true);
		nome.setFreezesText(true);
		nome.setEms(10);
		nome.setText(texto);
		nome.setTextSize( (int)(sessentaH * 0.1) );
		nome.setFilters(new InputFilter[] {new InputFilter.LengthFilter(10)});
		nome.setPadding( (int) (setentaW * 0.58), (int) (sessentaH * 0.15), 20, 20);
		
		LinearLayout layout2 = new LinearLayout(this);
		layout2.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));

		LinearLayout layout3 = new LinearLayout(this);
		layout3.setLayoutParams(new LayoutParams( (int) (setentaW * 0.35),
				LayoutParams.MATCH_PARENT));
		layout3.setOrientation(LinearLayout.VERTICAL);
		
		abaConteudo = new TextView(this);//textview2
		abaConteudo.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,(int) (sessentaH * 0.3)));
		
		
		abaJogador = new TextView(this);//textview1
		abaJogador.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,(int) (sessentaH * 0.33)));
		
		TextView abaSemSelecao = new TextView(this);
		abaSemSelecao = new TextView(this);//textview1
		abaSemSelecao.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,(int) (sessentaH * 0.1)));
		
		listaDeViews = new ListView(this);//listView1
		listaDeViews.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, 
				LayoutParams.MATCH_PARENT));
		listaDeViews.setPadding( (int) (setentaW * 0.05), (int) (sessentaH * 0.3), 
				(int) (setentaW * 0.10), (int) (sessentaH * 0.2));

		LinearLayout layout4 = new LinearLayout(this);
		layout4.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		
		this.btVoltar = new Button(this);//button1
		this.btVoltar.setLayoutParams(new LayoutParams((int) (height * 0.15),
				(int) (height * 0.15)));
		this.btVoltar.setBackgroundResource(R.drawable.voltar);
		this.btVoltar.setGravity(Gravity.CENTER_HORIZONTAL);
	
		LinearLayout layout5 = new LinearLayout(this);
		layout5.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		layout5.setGravity(Gravity.CENTER_HORIZONTAL);

		
		btJogar = new Button(this);//button2
		this.btJogar.setLayoutParams(new LayoutParams(2*((int) (height * 0.15)),
				(int) (height * 0.15)));
		this.btJogar.setBackgroundResource(R.drawable.btjogar);
		
		idsETitulos = ProvasDAO.getIdETituloDasProvas(this);
		if (idsETitulos.size() == 0) {
			selecaoDeProvaActivityController.criarProvaPadrao();
			idsETitulos = ProvasDAO.getIdETituloDasProvas(this);
		}

		String[] arrayDeTitulos = (String[]) idsETitulos.keySet().toArray(
				new String[0]);	

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1,
				arrayDeTitulos);
		
		listaDeViews.setAdapter(adapter);
		
		listaDeViews.setVisibility(ListView.GONE);
		
		listaDeViews.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String itemvalue = (String) listaDeViews.getItemAtPosition(position);
				idSelecionado = idsETitulos.get(itemvalue);
				Toast.makeText(fachada.getActivity(), "Conteúdo selecionado: " + itemvalue  + "\nClique no botão jogar para iniciar o jogo", Toast.LENGTH_SHORT)
				.show();			
			}
		});		
		
		abaJogador.setOnTouchListener(new View.OnTouchListener() {
	        @Override
	        public boolean onTouch(View v, MotionEvent event) {
	        	nome.setBackgroundResource(R.drawable.nome);
	        	nome.setCursorVisible(true);
	        	nome.setTextSize((int)(sessentaH * 0.1));
	        	nome.setText(texto);
	        	nome.setEnabled(true);
	        	layoutConteudo.setBackgroundResource(R.drawable.conteudo);
	        	listaDeViews.setVisibility(ListView.GONE);
	            return true;
	        }
	    });
		
		abaConteudo.setOnTouchListener(new View.OnTouchListener() {
	        @Override
	        public boolean onTouch(View v, MotionEvent event) { 	
	        	if( (nome.getText().toString().equals("") ) 
	        			|| (nome.getText().toString().equals(" ")) ){
	        		texto = "Jogador";
	        	}else{
	        		texto = new String(nome.getText().toString());
	        	}
	        	nome.setBackgroundResource(R.drawable.conteudo);
	        	nome.setCursorVisible(false);
	        	nome.setTextSize(0.01f);
	        	nome.setEnabled(false);
	        	layoutConteudo.setBackgroundResource(R.drawable.nome);
	        	listaDeViews.setVisibility(ListView.VISIBLE);
	            return true;
	        }
	    });

		this.btVoltar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				btVoltar.startAnimation(animScale);
				selecaoDeProvaActivityController.btVoltarCodigo();
			}
		});
		
		this.btJogar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				btJogar.startAnimation(animScale);
				if( (nome.getText().toString().equals("") ) 
	        			|| (nome.getText().toString().equals(" ")) ){
	        		texto = "Jogador";
	        	}else{
	        		texto = new String(nome.getText().toString());
	        	}
				if(idsETitulos.size() == 1){
					String itemvalue = (String) listaDeViews.getItemAtPosition(0);
					idSelecionado = idsETitulos.get(itemvalue);
					nomeJogadorActivityController.btCriarJogadorCodigo(texto);
					selecaoDeProvaActivityController.selecionarProva(idSelecionado);
				}
				else if(idSelecionado == -1 && idsETitulos.size() > 1){
					Toast.makeText(fachada.getActivity(), "Selecione um conteúdo primeiro!", Toast.LENGTH_SHORT)
					.show();
				}else{
					nomeJogadorActivityController.btCriarJogadorCodigo(texto);
					selecaoDeProvaActivityController.selecionarProva(idSelecionado);
				}
			}
		});

		
		layout1.addView(logo);
		layout1.addView(layout9);
			frame.addView(layoutConteudo);
				frame2.addView(nome);
						layout3.addView(abaJogador);
						layout3.addView(abaSemSelecao);
						layout3.addView(abaConteudo);
					layout2.addView(layout3);
					layout2.addView(listaDeViews);
				frame2.addView(layout2);
			
			frame.addView(frame2);
			layout6.addView(layout7);
			layout6.addView(frame);
		
		layout1.addView(layout6);
		layout1.addView(layout8);
			layout4.addView(btVoltar);
				layout5.addView(btJogar);
			layout4.addView(layout5);
		layout1.addView(layout4);
		
		view.addView(layout1);

		return view;
	}


	@Override
	public void onBackPressed() {
		selecaoDeProvaActivityController.btVoltarCodigo();
	}

}
