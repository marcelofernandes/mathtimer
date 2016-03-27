package br.com.ufpb.mathtimer.model;

public class QuestoesAcabaramException extends Exception {

	public QuestoesAcabaramException() {
	}

	public QuestoesAcabaramException(String detailMessage) {
		super(detailMessage);
	}

	public QuestoesAcabaramException(Throwable throwable) {
		super(throwable);
	}

	public QuestoesAcabaramException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

}
