package br.com.ufpb.mathtimer.view;

import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
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
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import br.com.ufpb.mathtimer.controller.ConfigActivityController;
import br.com.ufpb.mathtimer.controller.InstrucoesActivityController;
import br.com.ufpb.mathtimer.controller.RankingActivityController;
import br.com.ufpb.mathtimer.controller.SelecaoDeRankingActivityController;
import br.com.ufpb.mathtimer.model.Fachada;
import br.com.ufpb.mathtimer.model.ProvasDAO;

public class InstrucoesActivity extends Activity {

	private InstrucoesActivityController instrucoesActivityController;
	private TextView  instrucoes, ranking;
	private TextView tagRanking;
	private TextView abaRanking, abaConfiguracoes, abaInstrucoes; 
	private Button btMenu;
	private HashMap<String, Integer> idsETitulos;
	private SelecaoDeRankingActivityController selecaoDeRankingActivityController;
	private RankingActivityController rankingActivityController;
	private ConfigActivityController configActivityController;
	private ListView listaDeViews, listaDeRankings; 
	private Fachada fachada;
	private LinearLayout layoutConfiguracoes, layoutRanking, layoutInstrucoes;

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
		instrucoesActivityController = new InstrucoesActivityController(this);
		rankingActivityController = new RankingActivityController(this);
		selecaoDeRankingActivityController = new SelecaoDeRankingActivityController(
				this);
		configActivityController = new ConfigActivityController(this);
		
		Display display = getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();
		
		int setentaW = (int) (width * 0.7);
		int trintaW = (int) (setentaW * 0.3);
		int sessentaW = (int) (setentaW * 0.6);

		
		int sessentaH = (int) (height * 0.5);
		int vinteCincoH = (int) (sessentaH * 0.27);
		
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
		
		TextView logo = new TextView(this);
		logo.setBackgroundResource(R.drawable.barrasuperior);
		logo.setLayoutParams(new LayoutParams(width,
				(int) (height * 0.1)));
		logo.setGravity(Gravity.CENTER_HORIZONTAL);
		logo.setPadding(10, 0, 10, 0);
		
		LinearLayout layout4 = new LinearLayout(this);
		layout4.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				(int) (height * 0.58)));
		layout4.setOrientation(LinearLayout.HORIZONTAL);
		
		LinearLayout layout5 = new LinearLayout(this);
		layout5.setLayoutParams(new LayoutParams((int) (width * 0.13),
				LayoutParams.MATCH_PARENT));
		
		LinearLayout layout6 = new LinearLayout(this);
		layout6.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				(int) (height * 0.1)));

		
		FrameLayout frame = new FrameLayout(this);
		frame.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		frame.setPadding(0, 0, 0, 0);
	
		this.layoutConfiguracoes = new LinearLayout(this);//linear
		this.layoutConfiguracoes.setLayoutParams(new LayoutParams(setentaW,sessentaH));
		this.layoutConfiguracoes.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
		this.layoutConfiguracoes.setBackgroundResource(R.drawable.config);
		this.layoutConfiguracoes.setOrientation(LinearLayout.HORIZONTAL);
		
		
		this.layoutRanking = new LinearLayout(this);//linear2
		this.layoutRanking.setLayoutParams(new LayoutParams(setentaW,sessentaH));
		this.layoutRanking.setGravity(Gravity.CENTER_HORIZONTAL);
		this.layoutRanking.setBackgroundResource(R.drawable.rank);
		this.layoutRanking.setOrientation(LinearLayout.HORIZONTAL);
		
		
		this.layoutInstrucoes = new LinearLayout(this);//linear3
		this.layoutInstrucoes.setLayoutParams(new LayoutParams(setentaW,sessentaH));
		this.layoutInstrucoes.setGravity(Gravity.CENTER_HORIZONTAL);
		this.layoutInstrucoes.setBackgroundResource(R.drawable.inst);
		this.layoutInstrucoes.setOrientation(LinearLayout.HORIZONTAL);
		
		LinearLayout layout2 = new LinearLayout(this);
		layout2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.MATCH_PARENT));
		layout2.setOrientation(LinearLayout.VERTICAL);		
		
		this.abaRanking = new TextView (this);//textView2
		this.abaRanking.setLayoutParams(new LayoutParams(trintaW,vinteCincoH));
		//this.abaRanking.setPadding(0, 3, 0, 5);
		
		this.abaConfiguracoes = new TextView (this);//textView6
		this.abaConfiguracoes.setLayoutParams(new LayoutParams(trintaW,vinteCincoH));
		//this.abaConfiguracoes.setPadding(0, 5, 0, 5);

		
		this.abaInstrucoes = new TextView (this);//textView5
		this.abaInstrucoes.setLayoutParams(new LayoutParams(trintaW,vinteCincoH));
		//this.abaInstrucoes.setPadding(0, 5, 0, 5);
		
		FrameLayout frame2 = new FrameLayout(this);
		frame2.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		frame2.setPadding(20, 20, 20, 20);

		
		this.ranking = new TextView (this);//textView4
		this.ranking.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		this.ranking.setGravity(Gravity.CENTER_HORIZONTAL);
		this.ranking.setPadding(1, 50, 1, 1);
		this.ranking.setTextSize( (int)(sessentaH * 0.06));
	
		this.instrucoes = new TextView (this);//textView3
		this.instrucoes.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		this.instrucoes.setPadding(10, 20, 1, 1);
		this.instrucoes.setTextSize( (int)(sessentaH * 0.08));

		
		this.instrucoes.setText(instrucoesActivityController.getInstrucoes());
		
		this.listaDeViews = new ListView(this); //listView1
		this.listaDeViews.setLayoutParams(new LayoutParams(sessentaW,
				LayoutParams.MATCH_PARENT));
		this.listaDeViews.setPadding((int) (sessentaW * 0.04), (int) (sessentaH * 0.01), 
				0, (int) (sessentaH * 0.05));
		
		
		LinearLayout layout3 = new LinearLayout(this); //linear4
		layout3.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		layout3.setOrientation(LinearLayout.VERTICAL);
		
		this.tagRanking = new TextView (this);//textView13
		this.tagRanking.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				50));
		this.tagRanking.setPadding(0, 5, 0, 0);
		this.tagRanking.setGravity(Gravity.CENTER_HORIZONTAL);
		tagRanking.setText("Escolha o ranking");
		tagRanking.setTextSize(15);
		
		this.listaDeRankings = new ListView(this);//listView2
		this.listaDeRankings.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		this.listaDeRankings.setPadding(2, 2, 
				2, 2);
		
				
		this.btMenu = new Button(this);
		this.btMenu.setLayoutParams(new LayoutParams((int) (height * 0.15),
				(int) (height * 0.15)));
		this.btMenu.setBackgroundResource(R.drawable.voltar);

		
		
		String[] arrayDeTitulos = {"Baixar conteúdo", "Deletar conteúdo"};
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(fachada.getActivity(),
				android.R.layout.simple_list_item_1, android.R.id.text1,
				arrayDeTitulos);
		listaDeViews.setAdapter(adapter);
		
		idsETitulos = ProvasDAO.getIdETituloDasProvas(fachada.getActivity());
		if (idsETitulos.size() == 0) {
			selecaoDeRankingActivityController.criarProvaPadrao();
			idsETitulos = ProvasDAO.getIdETituloDasProvas(fachada.getActivity());
		}

		final String[] arrayDeTitulos2 = (String[]) idsETitulos.keySet().toArray(
				new String[0]);
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(fachada.getActivity(),
				android.R.layout.simple_list_item_1, android.R.id.text1,
				arrayDeTitulos2);
		listaDeRankings.setAdapter(adapter2);
		
		listaDeRankings.setVisibility(ListView.GONE);
		instrucoes.setVisibility(ListView.GONE);
		ranking.setVisibility(ListView.GONE);
		tagRanking.setVisibility(ListView.GONE);
		
		this.abaRanking.setOnTouchListener(new View.OnTouchListener() {
	        @Override
	        public boolean onTouch(View v, MotionEvent event) {
	        	layoutConfiguracoes.setBackgroundResource(R.drawable.rank);
	        	layoutRanking.setBackgroundResource(R.drawable.inst);
	        	layoutInstrucoes.setBackgroundResource(R.drawable.config);
	        	if(idsETitulos.size() == 1){
	        		listaDeRankings.setVisibility(ListView.GONE);
	        		instrucoes.setVisibility(ListView.GONE);
	        		listaDeViews.setVisibility(ListView.GONE);
	        		tagRanking.setVisibility(ListView.GONE);
	        		ranking.setVisibility(ListView.VISIBLE);

					String itemvalue = (String) listaDeRankings
							.getItemAtPosition(0);
					int idSelecionado = idsETitulos.get(itemvalue);
					selecaoDeRankingActivityController
							.selecionarRanking(idSelecionado);
					ranking.setText(rankingActivityController.getDadosDoRanking());
					
	        	}else{	       
	        		instrucoes.setVisibility(ListView.GONE);
	        		listaDeViews.setVisibility(ListView.GONE);	
	        		ranking.setVisibility(ListView.GONE);
	        		tagRanking.setVisibility(ListView.VISIBLE);
	        		listaDeRankings.setVisibility(ListView.VISIBLE);
	        	}
				return true;
	        }
	    });
		
		this.abaConfiguracoes.setOnTouchListener(new View.OnTouchListener() {
	        @Override
	        public boolean onTouch(View v, MotionEvent event) {
	        	layoutConfiguracoes.setBackgroundResource(R.drawable.config);
	        	layoutRanking.setBackgroundResource(R.drawable.rank);
	        	layoutInstrucoes.setBackgroundResource(R.drawable.inst);
	        	instrucoes.setVisibility(ListView.GONE);
        		ranking.setVisibility(ListView.GONE);
        		tagRanking.setVisibility(ListView.GONE);
        		listaDeRankings.setVisibility(ListView.GONE);
        		listaDeViews.setVisibility(ListView.VISIBLE);	
	            return true;
	        }
	    });
		
		this.abaInstrucoes.setOnTouchListener(new View.OnTouchListener() {
	        @Override
	        public boolean onTouch(View v, MotionEvent event) {
	        	layoutConfiguracoes.setBackgroundResource(R.drawable.inst);
	        	layoutRanking.setBackgroundResource(R.drawable.rank);
	        	layoutInstrucoes.setBackgroundResource(R.drawable.config);
        		listaDeViews.setVisibility(ListView.GONE);	
        		ranking.setVisibility(ListView.GONE);
        		tagRanking.setVisibility(ListView.GONE);
        		listaDeRankings.setVisibility(ListView.GONE);
        		instrucoes.setVisibility(ListView.VISIBLE);
				return true;
	        }
	    });

		listaDeViews.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				if(position == 0){
					configActivityController.btCarregarCodigo();
				}else{
					configActivityController.btDeletarCodigo();
				}
			}
		});
			
	
		
		listaDeRankings.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				String itemvalue = (String) listaDeRankings
						.getItemAtPosition(position);
			
				int idSelecionado = idsETitulos.get(itemvalue);
				selecaoDeRankingActivityController
						.selecionarRanking(idSelecionado);
				listaDeRankings.setVisibility(ListView.INVISIBLE);
				tagRanking.setVisibility(ListView.INVISIBLE);
				ranking.setVisibility(ListView.VISIBLE);
				ranking.bringToFront();
				ranking.invalidate();
				ranking.setText(rankingActivityController.getDadosDoRanking());
			}
		});

		this.btMenu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				btMenu.startAnimation(animScale);
				instrucoesActivityController.btMenuCodigo();
			}
		});		

		layout1.addView(logo);
		
			frame.addView(layoutInstrucoes);
			frame.addView(layoutRanking);
			
					layout2.addView(abaRanking);
					layout2.addView(abaInstrucoes);
					layout2.addView(abaConfiguracoes);
				layoutConfiguracoes.addView(layout2);
			
					frame2.addView(ranking);
					frame2.addView(instrucoes);
					frame2.addView(listaDeViews);
					
						layout3.addView(tagRanking);
						layout3.addView(listaDeRankings);
						
					frame2.addView(layout3);
				layoutConfiguracoes.addView(frame2);
			
			frame.addView(layoutConfiguracoes);
		layout4.addView(layout5);
		layout4.addView(frame);
		
		layout1.addView(layout6);
		layout1.addView(layout4);
		layout1.addView(btMenu);

		view.addView(layout1);

		return view;
	}

	
	@Override
	public void onBackPressed() {
		instrucoesActivityController.btMenuCodigo();
	}
}
