package io.jscode.util;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ExcepcionGenerica extends Exception {
	private Integer errorCode = Constantes.ERROR_CODE;
	private String errorStatus = Constantes.ERROR_STATUS;
	private String errorMessage;

	public ExcepcionGenerica(String message) {
		super(message);
		setErrorMessage(message);
	}

	public ExcepcionGenerica(String message, Integer code) {
		super(message);
		setErrorCode(code);
		setErrorMessage(message);
	}

	public ExcepcionGenerica(Throwable throwable) {
		super(throwable);
		setErrorMessage(throwable.getLocalizedMessage());		
	}
	
	public ExcepcionGenerica(String message, Integer code, Throwable throwable) {
		super(throwable);
		setErrorMessage(throwable.getLocalizedMessage());
		setErrorCode(code);
	}
}
