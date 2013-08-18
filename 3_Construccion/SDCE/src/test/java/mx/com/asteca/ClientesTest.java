/*
 * ClientesTest.java
 * Fecha de creaci&oacute;n: 31/07/2013
 * PeyCard. Todos los derechos reservados.
 *
 */
package mx.com.asteca;

import java.util.List;
import java.util.logging.Level;
import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.ClienteDAO;
import mx.com.asteca.persistencia.entidades.Clientes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

/**
 * {Insert class description here}
 * @version 1.0
 * @since Build 1.0
 * @author Jorge Amaro Coria
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ClientesTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientesTest.class);
    
    @Autowired
    private ClienteDAO clienteDAO;
    
    @Test
    public void testClientes(){
        try {
            List<Clientes> listaClientes =  clienteDAO.getAll();
            for (Clientes clientes : listaClientes) {
                LOGGER.debug(clientes.getNombre());
            }
            assertNotNull(listaClientes);
        } catch (PersistenciaException ex) {
        	LOGGER.error("ERROR DE PERSISTENCIA EN CLASE DE TEST", ex);
            throw new RuntimeException("ERROR DE PERSISTENCIA EN CLASE DE TEST", ex);
        }
    }
    
}
