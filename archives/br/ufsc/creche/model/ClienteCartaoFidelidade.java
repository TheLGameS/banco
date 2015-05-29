package br.ufsc.creche.model;

// Generated 22/12/2014 15:06:02 by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ClienteCartaoFidelidade generated by hbm2java
 */
@Entity
@Table(name = "CLIENTECARTAOFIDELIDADE")
public class ClienteCartaoFidelidade implements java.io.Serializable {

	private static final long serialVersionUID = 2922932559593146497L;
	private ClienteCartaoFidelidadeId id;
	private Cliente cliente;
	private Filial filial;
	private BigDecimal ccfDes;

	public ClienteCartaoFidelidade() {
	}

	public ClienteCartaoFidelidade(ClienteCartaoFidelidadeId id,
			Cliente cliente, Filial filial, BigDecimal ccfDes) {
		this.id = id;
		this.cliente = cliente;
		this.filial = filial;
		this.ccfDes = ccfDes;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "ccfEmp", column = @Column(name = "CCF_EMP", nullable = false)),
			@AttributeOverride(name = "ccfFil", column = @Column(name = "CCF_FIL", nullable = false)),
			@AttributeOverride(name = "ccfCol", column = @Column(name = "CCF_COL", nullable = false)),
			@AttributeOverride(name = "ccfNcf", column = @Column(name = "CCF_NCF", nullable = false)) })
	public ClienteCartaoFidelidadeId getId() {
		return this.id;
	}

	public void setId(ClienteCartaoFidelidadeId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "CCF_EMP", referencedColumnName = "CLI_EMP", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "CCF_COL", referencedColumnName = "CLI_COL", nullable = false, insertable = false, updatable = false) })
	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "CCF_EMP", referencedColumnName = "FIL_EMP", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "CCF_FIL", referencedColumnName = "FIL_COD", nullable = false, insertable = false, updatable = false) })
	public Filial getFilial() {
		return this.filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	@Column(name = "CCF_DES", nullable = false, precision = 15)
	public BigDecimal getCcfDes() {
		return this.ccfDes;
	}

	public void setCcfDes(BigDecimal ccfDes) {
		this.ccfDes = ccfDes;
	}

}