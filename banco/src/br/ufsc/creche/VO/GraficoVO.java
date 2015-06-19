package br.ufsc.creche.VO;

import java.math.BigDecimal;
import java.util.Date;

public class GraficoVO {

	private Date data;
	private BigDecimal valor;
	
 
	public BigDecimal getValor() {
		return valor;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
}
