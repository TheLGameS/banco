package br.ufsc.creche.VO;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.DualListModel;

public class ConfiguraTelaVO {

	private DualListModel<TelaWebVO> campos;
	private List<TelaWebVO> disponiveis = new ArrayList<TelaWebVO>();
	private List<TelaWebVO> selecionados = new ArrayList<TelaWebVO>();

	public DualListModel<TelaWebVO> getCampos() {
		return campos;
	}
	public void setCampos(DualListModel<TelaWebVO> campos) {
		this.campos = campos;
	}
	public List<TelaWebVO> getDisponiveis() {
		return disponiveis;
	}
	public void setDisponiveis(List<TelaWebVO> disponiveis) {
		this.disponiveis = disponiveis;
	}
	public List<TelaWebVO> getSelecionados() {
		return selecionados;
	}
	public void setSelecionados(List<TelaWebVO> selecionados) {
		this.selecionados = selecionados;
	}

}


/*   // Using RequestMap. NOTE: myBean2 should be request scoped and already created!
public void action1() {
    MyBean2 myBean2 = (MyBean2) FacesContext.getCurrentInstance().getExternalContext()
        .getRequestMap().get("myBean2");

    // This only works if myBean2 is request scoped and already created.
    if (myBean2 != null) {
        myBean2.getText().setValue("action1");
    }
}

// Using SessionMap. NOTE: myBean2 should be session scoped and already created!
public void action2() {
    MyBean2 myBean2 = (MyBean2) FacesContext.getCurrentInstance().getExternalContext()
        .getSessionMap().get("myBean2");

    // This only works if myBean2 is session scoped and already created.
    if (myBean2 != null) {
        myBean2.getText().setValue("action2");
    }
}

// Using ApplicationMap. NOTE: myBean2 should be application scoped and already created!
public void action3() {
    MyBean2 myBean2 = (MyBean2) FacesContext.getCurrentInstance().getExternalContext()
        .getApplicationMap().get("myBean2");

    // This only works if myBean2 is application scoped and already created.
    if (myBean2 != null) {
        myBean2.getText().setValue("action3");
    }
}*/