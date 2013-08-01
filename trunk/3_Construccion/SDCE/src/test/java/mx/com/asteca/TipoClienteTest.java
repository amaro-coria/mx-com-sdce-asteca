/*
 * TipoClienteTest.java
 * Fecha de creaci&oacute;n: 31/07/2013
 * PeyCard. Todos los derechos reservados.
 *
 */
package mx.com.asteca;

import java.util.List;
import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.TipoClienteDAO;
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
 * {Insert class description here}
 * @version 1.0
 * @since Build 1.0
 * @author Jorge Amaro Coria
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TipoClienteTest {

     private static final Logger LOGGER = LoggerFactory.getLogger(TipoClienteTest.class);
    
    @Autowired
    private TipoClienteDAO tipoClienteDAO;
    
    @Test
    @Transactional
    public void testRegistros(){
        try {
            List<TiposClientes> listaClientes = tipoClienteDAO.getAll();
            for (TiposClientes tiposClientes : listaClientes) {
                
                LOGGER.debug(tiposClientes.getIdTipoCliente() + ":"+tiposClientes.getNombre());
            }
        } catch (PersistenciaException ex) {
            LOGGER.error("ERROR EN TEST DE REGISTROS", ex);
        }
    }
}
