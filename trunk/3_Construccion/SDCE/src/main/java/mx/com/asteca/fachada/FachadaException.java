package mx.com.asteca.fachada;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FachadaException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger LOGGER = LoggerFactory.getLogger(FachadaException.class);

	public FachadaException() {
		LOGGER.error("Error en Fachada sin descripcion");
	}

	public FachadaException(String message) {
		super(message);
		LOGGER.error("Error en Fachada::"+message);
	}

	public FachadaException(Throwable cause) {
		super(cause);
		LOGGER.error("Error en Fachada solo con causa::"+cause.getMessage(), cause);
	}

	public FachadaException(String message, Throwable cause) {
		super(message, cause);
		LOGGER.error("Error en Fachada::"+message, cause);
	}

}
