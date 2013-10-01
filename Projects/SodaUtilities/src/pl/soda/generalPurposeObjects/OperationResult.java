package pl.soda.generalPurposeObjects;

import java.io.Serializable;

public class OperationResult<T> implements Serializable{
	private T result;
	private String message;

	public OperationResult(T result, String message){
		this.result = result;
		this.message = message;
	}

	// ===================== GETTERS & SETTERS ======================== //
	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
