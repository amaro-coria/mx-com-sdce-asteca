package mx.com.asteca.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Random;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.MunicipioDAO;
import mx.com.asteca.persistencia.entidades.Estados;
import mx.com.asteca.persistencia.entidades.Municipios;

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
 * @version 1.0
 * @since Build 1.0
 * @author Jorge Amaro Coria
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MunicipioDAOTest {

	
private static final Logger LOGGER = LoggerFactory.getLogger(MunicipioDAOTest.class);
	
	@Autowired
	private MunicipioDAO municipioDAO;
	
	@Test
	@Transactional
	public void testEstados(){
		try {
			int randomInt = new Random().nextInt(33);
			List<Municipios> listMunicipios = municipioDAO.findByEstado(new Estados(randomInt));
			LOGGER.debug("IMPRIMIENDO LA LISTA DE LOS MUNICIPIOS DEL ESTADO CON ID "+randomInt);
			for (Municipios municipios : listMunicipios) {
				LOGGER.debug("MUNICIPIO:["+municipios.getNombre()+"]");
			}
			assertNotNull(listMunicipios);
		} catch (PersistenciaException ex) {
			LOGGER.error("ERROR DE PERSISTENCIA EN CLASE DE TEST", ex);
            throw new RuntimeException("ERROR DE PERSISTENCIA EN CLASE DE TEST", ex);
		}
	}
	
}
