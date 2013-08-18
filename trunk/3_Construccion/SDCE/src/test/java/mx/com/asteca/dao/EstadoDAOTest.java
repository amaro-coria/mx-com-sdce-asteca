package mx.com.asteca.dao;

import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.EstadoDAO;
import mx.com.asteca.persistencia.entidades.Estados;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.*;

/**
 * {Insert class description here}
 * @version 1.0
 * @since Build 1.0
 * @author Jorge Amaro Coria
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class EstadoDAOTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(EstadoDAOTest.class);
	
	@Autowired
	private EstadoDAO estadoDAO;
	
	@Test
	@Transactional
	public void testEstados(){
		try {
			List<Estados> listaEstados = estadoDAO.getAll();
			LOGGER.debug("IMPRIMIENDO LOS PRIMEROS 10 REGISTROS");
			for(int i = 0; i<10; i++){
				LOGGER.debug("NOMBRE:["+listaEstados.get(i).getNombre()+"]");
			}
			assertNotNull(listaEstados);
		} catch (PersistenciaException ex) {
			LOGGER.error("ERROR DE PERSISTENCIA EN CLASE DE TEST", ex);
            throw new RuntimeException("ERROR DE PERSISTENCIA EN CLASE DE TEST", ex);
		}
	}
	
}
