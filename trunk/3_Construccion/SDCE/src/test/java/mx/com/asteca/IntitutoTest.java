/*
 * IntitutoTest.java
 *
 */
package mx.com.asteca;

import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.InstitutoDAO;
import mx.com.asteca.persistencia.entidades.Institutos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * {Insert class description here}
 * 
 * @author Rabelt Ibarra Godinez.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class IntitutoTest {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(IntitutoTest.class);

	@Autowired
	private InstitutoDAO institutoDAO;

	@Test
	@Transactional
	public void testRegistros() {
		try {
			List<Institutos> listaInstitutos = institutoDAO.getAll();
			for (Institutos institutos : listaInstitutos) {

				LOGGER.debug(institutos.getIdInstituto() + ":"
						+ institutos.getNombre());
			}
		} catch (PersistenciaException ex) {
			LOGGER.error("ERROR EN TEST DE REGISTROS", ex);
		}
	}
}
