/*
 * TipoClienteTest.java
 * Fecha de creaci&oacute;n: 31/07/2013
 * PeyCard. Todos los derechos reservados.
 *
 */
package mx.com.asteca;

import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.EquipoDAO;
import mx.com.asteca.persistencia.dao.TipoClienteDAO;
import mx.com.asteca.persistencia.entidades.Equipos;
import mx.com.asteca.persistencia.entidades.TiposClientes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Rabelt Ibarra Godinez.
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class EquiposTest {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(EquiposTest.class);

	@Autowired
	private EquipoDAO equiposDAO;

	@Test
	@Transactional
	public void testRegistros() {
		try {
			List<Equipos> listaEquipos = equiposDAO.getAll();
			for (Equipos equipos : listaEquipos) {

				LOGGER.debug(equipos.getIdEquipo() + ":" + equipos.getDsc());
			}
		} catch (PersistenciaException ex) {
			LOGGER.error("ERROR EN TEST DE REGISTROS", ex);
		}
	}
}
