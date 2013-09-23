package mx.com.asteca.persistencia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersistenciaException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(PersistenciaException.class);
	
	public PersistenciaException() {
		super();
		LOGGER.error("Error en Persistencia sin mensaje");
	}

	public PersistenciaException(String message, Throwable cause) {
		super(message, cause);
		LOGGER.error("Error en Persistencia::"+message, cause);
	}

	public PersistenciaException(String message) {
		super(message);
		LOGGER.error("Error en Persistencia::"+message);
	}

	public PersistenciaException(Throwable cause) {
		super(cause);
		LOGGER.error("Error en Persistencia solo con causa::"+cause.getMessage(), cause);
	}

	
	
}
