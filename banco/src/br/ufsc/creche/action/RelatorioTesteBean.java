package br.ufsc.creche.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import net.sf.jasperreports.engine.JRException;
import br.ufsc.creche.model.TesteRelatorio;
import br.ufsc.creche.util.GeraRelatorioUtil;

@ManagedBean
@RequestScoped
public class RelatorioTesteBean  extends ActionBean implements Serializable  {

	private static final long serialVersionUID = -6568681056451577025L;

	private List<TesteRelatorio> list;
	private int tipoRelatorio=0;

	@PostConstruct
	public void PreparandoTela() {

		TesteRelatorio c1 = new TesteRelatorio("Caju Jones","caju@gmail.com","1111-1111");
		TesteRelatorio c2 = new TesteRelatorio("Joao Cana Brava","joao@gmail.com","2222-2222");
		TesteRelatorio c3 = new TesteRelatorio("Francisco Garoto Super","franscisco@gmail.com","3333-3333");
		TesteRelatorio c4 = new TesteRelatorio("Fabricio Grande Polegar","fabricio@gmail.com","4444-4444");
		TesteRelatorio c5 = new TesteRelatorio("Iuri Martinho","iuri@gmail.com","5555-5555");
		TesteRelatorio c6 = new TesteRelatorio("Rui Fidalgo","rui@gmail.com","6666-6666");
		TesteRelatorio c7 = new TesteRelatorio("Isadora Meireles","isadora@gmail.com","7777-7777");
		TesteRelatorio c8 = new TesteRelatorio("Vanessa Fontes","fontes@gmail.com","8888-8888");
		TesteRelatorio c9 = new TesteRelatorio("Filomena Regueira","filomena@gmail.com","9999-9999");

		getList().add(c1);
		getList().add(c2);
		getList().add(c3);
		getList().add(c4);
		getList().add(c5);
		getList().add(c6);
		getList().add(c7);
		getList().add(c8);
		getList().add(c9);


	}

	public void montaRelatorio() {
		String nomeArquivo = "todosClientes";
		String template = "RelatorioClientes.jrxml";

		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("title", "Relatório de Clientes" );

		GeraRelatorioUtil a = new GeraRelatorioUtil();
		try {
			a.montaRelatorio(list, template, tipoRelatorio, parametros, nomeArquivo);
		} catch (JRException e) {
			e.printStackTrace();
		}

	}

	public List<TesteRelatorio> getList() {
		if (list == null) {
			list = new ArrayList<TesteRelatorio>();
		}
		return list;
	}

	public void setList(List<TesteRelatorio> list) {
		this.list = list;
	}

	public int getTipoRelatorio() {
		return tipoRelatorio;
	}

	public void setTipoRelatorio(int tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}

}
