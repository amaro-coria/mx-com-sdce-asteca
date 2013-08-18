package mx.com.asteca.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Random;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.AsentamientoDAO;
import mx.com.asteca.persistencia.dao.MunicipioDAO;
import mx.com.asteca.persistencia.entidades.Asentamientos;
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
public class AsentamiendoDAOTest {


	private static final Logger LOGGER = LoggerFactory.getLogger(AsentamiendoDAOTest.class);
		
		@Autowired
		private AsentamientoDAO asentamientoDAO;
		@Autowired
		private MunicipioDAO municipioDAO;
		
		@Test
		@Transactional
		public void testEstados(){
			try {
				int randomInt = new Random().nextInt(32);
				List<Municipios> listMunicipios = municipioDAO.findByEstado(new Estados(randomInt));
				int randomInt2 = new Random().nextInt(listMunicipios.size());
				List<Asentamientos> listAsentamientos = asentamientoDAO.findByMunicipio(listMunicipios.get(randomInt2));
				LOGGER.debug("IMPRIMIENDO LA LISTA DE LOS ASENTAMIENTOS DEL ESTADO CON ID "+randomInt+" Y DEL MUNICIPIO CON ID "+randomInt2);
				for (Asentamientos asentamiento : listAsentamientos) {
					LOGGER.debug("ASENTAMIENTO:["+asentamiento.getNombre()+"]");
				}
				assertNotNull(listAsentamientos);
			} catch (PersistenciaException ex) {
				LOGGER.error("ERROR DE PERSISTENCIA EN CLASE DE TEST", ex);
	            throw new RuntimeException("ERROR DE PERSISTENCIA EN CLASE DE TEST", ex);
			}
		}
	
}
