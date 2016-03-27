package br.com.ufpb.mathtimer.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import br.com.ufpb.mathtimer.controller.MainActivityController;
import br.com.ufpb.mathtimer.model.Fachada;

public class MainActivity extends Activity {

	private MainActivityController mainActivityController;
	private Button btJogar, btmenu;
	private Fachada fachada;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final Animation animScale = AnimationUtils.loadAnimation(this,
			    R.layout.anim_scale);
		fachada = Fachada.getInstance();
		mainActivityController = new MainActivityController(this);
		fachada.setActivity(this);
		
		this.btJogar = (Button) findViewById(R.id.Button2);
		this.btJogar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				btJogar.startAnimation(animScale);
				mainActivityController.btJogarCodigo();
			}
		});

		this.btmenu = (Button) findViewById(R.id.Button1);
		this.btmenu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				btmenu.startAnimation(animScale);
				mainActivityController.btInstrucoesCodigo();
			}
		});

	}
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
		startActivity(intent);
//		finish();
//		System.exit(0);
		//moveTaskToBack(true);
	}

}