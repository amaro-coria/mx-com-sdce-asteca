/**
 * 
 */
package mx.com.asteca.persistencia.dao.impl;

import mx.com.asteca.persistencia.dao.PersonaDAO;
import mx.com.asteca.persistencia.entidades.Personas;

import org.springframework.stereotype.Repository;

/**
 * @author JAMARO
 *
 */
@Repository
public class PersonasDAOImpl extends BaseDAOImpl<Personas, Integer> implements
		PersonaDAO {

}
