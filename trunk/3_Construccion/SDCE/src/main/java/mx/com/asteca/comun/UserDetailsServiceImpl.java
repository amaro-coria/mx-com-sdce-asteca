package mx.com.asteca.comun;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedProperty;

import mx.com.asteca.comun.dto.PersonaDTO;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.fachada.PersonaFachada;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	private transient PersonaFachada fachadaPersona;

	@SuppressWarnings("deprecation")
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		PersonaDTO usuario=null;
		try{
			usuario = fachadaPersona.getUser(username);
			}catch (FachadaException e) {
				
			}
		if (usuario != null) {
			List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
            // convert roles
           /* List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
            Set<OPermisoOperadorCct> permisos=usuario.getOPermisoOperadorCcts();
            for(OPermisoOperadorCct p:permisos){
            	roles.add(new GrantedAuthorityImpl(p.getCPermisos().getNombre()));
            }
            */
            User user = new User(usuario.getUsuario(), usuario.getPassword(), true,true, true, true, roles.toArray(new GrantedAuthority[0]));
            
            return user;

        } else {
            throw new UsernameNotFoundException("No user found");
        }
		
	}

	public PersonaFachada getFachadaPersona() {
		return fachadaPersona;
	}

	public void setFachadaPersona(PersonaFachada fachadaPersona) {
		this.fachadaPersona = fachadaPersona;
	}
}
