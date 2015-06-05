/*

public class ColaboradorRN extends RN<Colaborador> {

	public ColaboradorRN() {
		super(new ColaboradorDAO());
	}

	@Override
	public void salvar(Colaborador model) throws RNException {
		try {
			dao.salvar(model);
		} catch (DAOException e) {
			throw new RNException(e.getMessage());
		}
	}

	@Override
	public void excluir(Colaborador model) throws RNException {
		try {
			dao.excluir(model);
		} catch (DAOException e ) {
			e.printStackTrace();
			throw new RNException(e.getMessage());
		}
	}

	@Override
	public Colaborador obterPorId(Colaborador filtro) {
		return dao.obterPorId(filtro);
	}

	public Colaborador obterPorDocumento(Colaborador filtro) {
		return ((ColaboradorDAO) dao).obterPorDocumento(filtro);
	}

	public Colaborador obterPorCodigoVO(ColaboradorVO filtro) {
		return ((ColaboradorDAO) dao).obterPorCodigoVO(filtro);
	}

	@Override
	public List<Colaborador> pesquisar(Colaborador filtros) {
		return dao.pesquisar(filtros);
	}

	public List<ColaboradorVO> pesquisarTelaWeb(List<TelaWebVO> tela) {
		return ((ColaboradorDAO) dao).pesquisarTelaWeb(tela);
	}

	public void alterar(Colaborador model) throws RNException {
		try {
			dao.salvar(model);
		} catch (DAOException e) {
			throw new RNException(e);
		}
	}
	public Integer geraCodigoMax(){
		return ((ColaboradorDAO) dao).geraCodigo();
	}
}
*/