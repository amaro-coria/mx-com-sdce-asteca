package mx.com.asteca.vista;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.PersonaDTO;
import mx.com.asteca.fachada.BaseFachada;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.fachada.PersonaFachada;

@ManagedBean(name = Constantes.BEAN_LOGIN)
@RequestScoped
public class LoginControlador extends BaseController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	
	@ManagedProperty("#{authenticationManager}")
	private AuthenticationManager am;
	@ManagedProperty("#{personaFachadaImpl}")
	private transient PersonaFachada fachadaPersona;
	
	protected AuthenticationDetailsSource authenticationDetailsSource = new WebAuthenticationDetailsSource();
	
	public String login() {
		try{
			HttpServletRequest servletReq = (HttpServletRequest) super.getFacesContext().getCurrentInstance().getExternalContext().getRequest();
			
			UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(this.getUserName(), this.getPassword());
			
			HttpSession session = servletReq.getSession(false);
			
			if(session != null) {
				PersonaDTO usuario = fachadaPersona.getUser(this.getUserName());
				servletReq.getSession().setAttribute("SPRING_SECURITY_CURRENT_USER", usuario);
				
				StringBuffer nombreCompleto=new StringBuffer();
				nombreCompleto.append(usuario.getNombre()!=null?usuario.getNombre():"").append(" ")
							  .append(usuario.getApellidoP()!=null?usuario.getApellidoP():"").append(" ")
							  .append(usuario.getApellidoM()!=null?usuario.getApellidoM():"");
				servletReq.getSession().setAttribute("nombreUsuario", nombreCompleto.toString());
				servletReq.getSession().setAttribute("fechaHora", DateFormat.getDateInstance(DateFormat.FULL).format(new Date()));
				
			}
			
			authRequest.setDetails(authenticationDetailsSource.buildDetails(servletReq));
			Authentication authResult = am.authenticate(authRequest);
			
			SecurityContextHolder.getContext().setAuthentication(authResult);
		} catch (BadCredentialsException bce) {
			super.addErrorMessage(Constantes.ERROR_LOGIN);
			return null;
		} catch (AuthenticationException ae) {
			super.addErrorMessage(Constantes.ERROR_AUTENTICACION);
			return null;
		} catch (FachadaException fe) {
			super.addErrorMessage(fe.getMessage());
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			super.addErrorMessage(e.getMessage());
			return null;
		}
		return "/faces/secured/workarea?faces-redirect=true";
	}

	public PersonaFachada getFachadaPersona() {
		return fachadaPersona;
	}

	public void setFachadaPersona(PersonaFachada fachadaPersona) {
		this.fachadaPersona = fachadaPersona;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AuthenticationManager getAm() {
		return am;
	}

	public void setAm(AuthenticationManager am) {
		this.am = am;
	}

	public AuthenticationDetailsSource getAuthenticationDetailsSource() {
		return authenticationDetailsSource;
	}

	public void setAuthenticationDetailsSource(
			AuthenticationDetailsSource authenticationDetailsSource) {
		this.authenticationDetailsSource = authenticationDetailsSource;
	}

	@Override
	String getModulo() {
		// TODO Auto-generated method stub
		return null;
	}
}
