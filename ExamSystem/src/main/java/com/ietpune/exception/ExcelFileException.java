package com.ietpune.exception;

public class ExcelFileException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcelFileException() {
	}

	public ExcelFileException(String message) {
		super(message);
	}

	public ExcelFileException(Throwable cause) {
		super(cause);
	}

	public ExcelFileException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExcelFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
