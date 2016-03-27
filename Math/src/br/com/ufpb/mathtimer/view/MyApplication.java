package br.com.ufpb.mathtimer.view;

import org.acra.ACRA;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

import android.app.Application;
import android.content.Intent;

// Insira o código formKey copiado anteriormente como parâmetro da anotação
@ReportsCrashes(
	formKey = "",
	mailTo = "suporte.mathtimer@gmail.com",
	mode = ReportingInteractionMode.DIALOG,
    resDialogText = R.string.crash_dialog_text,
    resDialogIcon = android.R.drawable.ic_dialog_info, //optional. default is a warning sign
    resDialogTitle = R.string.crash_dialog_title, // optional. default is your application name
    resDialogCommentPrompt = R.string.crash_dialog_comment_prompt // optional. When defined, adds a user text field input with this text resource as a label   
    )
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        ACRA.init(this); // inicia a biblioteca
        super.onCreate();
    }
}