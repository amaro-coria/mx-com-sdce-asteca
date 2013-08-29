/*
 * ClientesTest.java
 * Fecha de creaci&oacute;n: 31/07/2013
 * PeyCard. Todos los derechos reservados.
 *
 */
package mx.com.asteca;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.NotificacionEstadoDAO;
import mx.com.asteca.persistencia.entidades.Notificaciones;
import mx.com.asteca.persistencia.entidades.NotificacionesEstados;

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
 * @author Rabelt Ibarra Godinez
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml"})
public class NotificacionEstadoTest {

    private static final Logger LOGGER = LoggerFactory
    		.getLogger(NotificacionEstadoTest.class);
    
    @Autowired
    private NotificacionEstadoDAO notificacionEstadoDAO;
    
    @Test
    @Transactional
    public void testRegistros(){
        try {
            List<NotificacionesEstados> listaNotificacionesEstado =  notificacionEstadoDAO.getAll();
            for (NotificacionesEstados notificaciones : listaNotificacionesEstado) {
                LOGGER.debug(notificaciones.getNombre());
            }
            assertNotNull(listaNotificacionesEstado);
        } catch (PersistenciaException ex) {
        	LOGGER.error("ERROR DE PERSISTENCIA EN CLASE DE TEST", ex);
            throw new RuntimeException("ERROR DE PERSISTENCIA EN CLASE DE TEST", ex);
        }
    }
    
}
