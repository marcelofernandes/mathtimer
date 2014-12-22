package br.com.ufpb.mathtimer.view;

import java.io.ByteArrayInputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import br.com.ufpb.mathtimer.controller.ZoomActivityController;
import br.com.ufpb.mathtimer.model.Fachada;
import br.com.ufpb.mathtimer.model.ImageZoom;

public class ZoomActivity extends Activity {

	private ZoomActivityController zoomActivityController;
	private Fachada fachada;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		fachada = Fachada.getInstance();
		zoomActivityController = new ZoomActivityController(this);
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setBackgroundColor(Color.WHITE);

		ByteArrayInputStream is = new ByteArrayInputStream(fachada.getImagem());
		Bitmap b = BitmapFactory.decodeStream(is);

		ImageZoom touch = new ImageZoom(this, b);
		Button btVoltar = new Button(this);

		btVoltar.setText("Voltar ao jogo!");

		layout.addView(btVoltar);
		layout.addView(touch);

		setContentView(layout);
		btVoltar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				zoomActivityController.btVoltarCodigo();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
