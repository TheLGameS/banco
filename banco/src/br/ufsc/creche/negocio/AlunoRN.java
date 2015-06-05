package br.ufsc.creche.negocio;

import java.util.List;

import br.ufsc.creche.dao.AlunoDAO;
import br.ufsc.creche.model.Aluno;
import br.ufsc.creche.util.RNException;

public class AlunoRN  extends RN<Aluno>{

	public AlunoRN() {
		super(new AlunoDAO());
	}
	
	@Override
	public void salvar(Aluno model) throws RNException {
		
	}

	@Override
	public void excluir(Aluno model) throws RNException {
		
	}

	@Override
	public Aluno obterPorId(Aluno filtro) {
		return null;
	}

	@Override
	public List<Aluno> pesquisar(Aluno filtros) {
		return dao.pesquisar(filtros);
	}
	
	

}
