/**
 * 
 */
package mx.com.asteca.persistencia.dao.impl;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.stereotype.Repository;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.ModulosDAO;
import mx.com.asteca.persistencia.entidades.Modulos;

/**
 * @author Javier
 *
 */
@Repository
public class ModulosDAOImpl extends BaseDAOImpl<Modulos, Integer> implements
		ModulosDAO {

}
