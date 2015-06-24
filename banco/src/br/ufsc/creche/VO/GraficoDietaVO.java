package br.ufsc.creche.VO;


public class GraficoDietaVO {

	private Number quantidade;
	private String descricao;
	private Integer produto;

	public Number getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Number quantidade) {
		this.quantidade = quantidade;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getProduto() {
		return produto;
	}
	public void setProduto(Integer produto) {
		this.produto = produto;
	}

}
