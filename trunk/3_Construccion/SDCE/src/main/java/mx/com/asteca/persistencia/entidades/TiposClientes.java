package mx.com.asteca.persistencia.entidades;
// Generated 31/07/2013 11:41:38 AM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TiposClientes generated by hbm2java
 */
@Entity
@Table(name="tipos_clientes"
    ,catalog="astecadb"
)
public class TiposClientes  implements java.io.Serializable {


     private short idTipoCliente;
     private String nombre;
     private Set<Clientes> clienteses = new HashSet<Clientes>(0);

    public TiposClientes() {
    }

	
    public TiposClientes(short idTipoCliente) {
        this.idTipoCliente = idTipoCliente;
    }
    public TiposClientes(short idTipoCliente, String nombre, Set<Clientes> clienteses) {
       this.idTipoCliente = idTipoCliente;
       this.nombre = nombre;
       this.clienteses = clienteses;
    }
   
     @Id 
     @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_TIPO_CLIENTE", unique=true, nullable=false)
    public short getIdTipoCliente() {
        return this.idTipoCliente;
    }
    
    public void setIdTipoCliente(short idTipoCliente) {
        this.idTipoCliente = idTipoCliente;
    }
    
    @Column(name="NOMBRE", length=20)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="tiposClientes")
    public Set<Clientes> getClienteses() {
        return this.clienteses;
    }
    
    public void setClienteses(Set<Clientes> clienteses) {
        this.clienteses = clienteses;
    }




}

