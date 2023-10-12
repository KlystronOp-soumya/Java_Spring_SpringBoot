package com.demo.cartapi.controller.exceptions

class CartException extends Exception{
	
	CartException(final String message)
	{
		super(message)
	}
	
	 CartException(String message, Throwable cause) {
		super(message, cause)
	}
	
	 CartException(Throwable cause) {
		super(cause)
	}
	
}
