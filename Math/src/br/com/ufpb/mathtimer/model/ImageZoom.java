package br.com.ufpb.mathtimer.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

public class ImageZoom extends View {

	private static final int INVALID_POINTER_ID = -1;
	private static Drawable mIcon;
	private static float mPosX;
	private static float mPosY;
	private float mLastTouchX;
	private float mLastTouchY;
	private int mActivePointerId = INVALID_POINTER_ID;
	private ScaleGestureDetector mScaleDetector;
	private static float mScaleFactor = 1.f;

	public ImageZoom(Context context, Bitmap bitmap) {
		this(context, null, 0, bitmap);
	}

	public ImageZoom(Context context, AttributeSet attrs, Bitmap bitmap) {
		this(context, attrs, 0, bitmap);
	}

	public ImageZoom(Context context, AttributeSet attrs, int defStyle,
			Bitmap bitmap) {
		super(context, attrs, defStyle);
		// A imagem será o ícone da aplicação
		// mIcon = context.getResources().getDrawable(id);
		mIcon = new BitmapDrawable(getResources(), bitmap);
		// Define os limites da imagem
		mIcon.setBounds(0, 0, mIcon.getIntrinsicWidth(),
				mIcon.getIntrinsicHeight());

		// Cria o objeto que capturará o "evento de pinça"
		mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());

	}

	@Override
	// Esse método servirá pra mover a imagem na tela
	// e delegar o multi-touch pro ScaleGestureDetector
	public boolean onTouchEvent(MotionEvent ev) {

		// Delega o evento pro ScaleGestureDetector
		// que verificará se o multitouch foi utilizado
		mScaleDetector.onTouchEvent(ev);

		final int action = ev.getAction();
		switch (action & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN: {
			final float x = ev.getX();
			final float y = ev.getY();

			// Ao tocar na tela, salva o local tocado
			mLastTouchX = x;
			mLastTouchY = y;
			mActivePointerId = ev.getPointerId(0);
			break;
		}

		case MotionEvent.ACTION_MOVE: {
			final int pointerIndex = ev.findPointerIndex(mActivePointerId);
			final float x = ev.getX(pointerIndex);
			final float y = ev.getY(pointerIndex);

			// Ao mover, checa se o ScaleGestureDetector
			// não está processando algum gesto
			if (!mScaleDetector.isInProgress()) {
				final float dx = x - mLastTouchX;
				final float dy = y - mLastTouchY;

				// Salva o deslocamento feito ao mover
				mPosX += dx;
				mPosY += dy;

				invalidate();
			}
			mLastTouchX = x;
			mLastTouchY = y;
			break;
		}

		case MotionEvent.ACTION_UP: {
			// nenhum dedo da tela, ponto inválido
			mActivePointerId = INVALID_POINTER_ID;
			break;
		}

		case MotionEvent.ACTION_CANCEL: {
			// nenhum dedo da tela, ponto inválido
			mActivePointerId = INVALID_POINTER_ID;
			break;
		}

		case MotionEvent.ACTION_POINTER_UP: {
			// Ao retirar um dos dedos, atualize o ponto
			// com o dedo que ficou na tela
			final int pointerIndex = (ev.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;

			final int pointerId = ev.getPointerId(pointerIndex);

			if (pointerId == mActivePointerId) {
				final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
				mLastTouchX = ev.getX(newPointerIndex);
				mLastTouchY = ev.getY(newPointerIndex);

				mActivePointerId = ev.getPointerId(newPointerIndex);
			}
			break;
		}
		}
		return true;
	}

	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		canvas.save();

		// Reposicona a matriz do canvas
		canvas.translate(mPosX, mPosY);
		// Define a escala (zoom) do canvas
		canvas.scale(mScaleFactor, mScaleFactor);
		// Redesenha a imagem
		mIcon.draw(canvas);

		canvas.restore();
	}

	// classe que ouve o evento de multi-touch
	// e calcula a escala
	private class ScaleListener extends
			ScaleGestureDetector.SimpleOnScaleGestureListener {

		@Override
		public boolean onScale(ScaleGestureDetector d) {
			mScaleFactor *= d.getScaleFactor();

			// Não permite que o objeto fique
			// muito grande nem muito pequeno
			mScaleFactor = Math.max(0.5f, Math.min(mScaleFactor, 5.0f));

			invalidate();
			return true;
		}
	}
}
