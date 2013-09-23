package mx.com.asteca.servicio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServicioException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger LOGGER = LoggerFactory.getLogger(ServicioException.class);
	
	public ServicioException() {
		LOGGER.error("Error en Servicio sin descripcion");
	}

	public ServicioException(String message) {
		super(message);
		LOGGER.error("Error en Servicio::"+message);
	}

	public ServicioException(Throwable cause) {
		super(cause);
		LOGGER.error("Error en servicio solo con causa::"+cause.getMessage(), cause);
	}

	public ServicioException(String message, Throwable cause) {
		super(message, cause);
		LOGGER.error("Error en Servicio::"+message, cause);
	}

}
