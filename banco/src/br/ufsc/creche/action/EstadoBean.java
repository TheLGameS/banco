package br.ufsc.creche.action;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.ufsc.creche.model.Estado;
import br.ufsc.creche.negocio.EstadoRN;

@ManagedBean
@RequestScoped
public class EstadoBean {
	
	private Estado estado = new Estado();
	private List<Estado> lista;
		
	public List<Estado> getLista() {		
		if (lista == null){
			 lista = new EstadoRN().pesquisar(null);
		}
		return lista;
	}
	
	public Estado obterPorId(Estado estado) {		
		return new EstadoRN().obterPorId(estado);
	}

	public void setLista(List<Estado> lista) {
		this.lista = lista;
	}	

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
}
