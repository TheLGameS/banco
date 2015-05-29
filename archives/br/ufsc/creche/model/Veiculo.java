package br.ufsc.creche.model;

// Generated 22/12/2014 15:06:02 by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Veiculo generated by hbm2java
 */
@Entity
@Table(name = "VEICULO")
public class Veiculo implements java.io.Serializable {
	
	private static final long serialVersionUID = 4628990886595257539L;
	private String veiPlc;
	private Motorista motorista;
	private Placa placaByPlcCod;
	private Placa placaByVeiPld;
	private Placa placaByVeiPlb;
	private Placa placaByVeiPlr;
	private BigDecimal veiCat;
	private String veiOb1;
	private String veiOb2;
	private Set<Colaborador> colaboradors = new HashSet<Colaborador>(0);	

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "placaByPlcCod"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "VEI_PLC", unique = true, nullable = false, length = 10)
	public String getVeiPlc() {
		return this.veiPlc;
	}

	public void setVeiPlc(String veiPlc) {
		this.veiPlc = veiPlc;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VEI_MTR")
	public Motorista getMotorista() {
		return this.motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Placa getPlacaByPlcCod() {
		return this.placaByPlcCod;
	}

	public void setPlacaByPlcCod(Placa placaByPlcCod) {
		this.placaByPlcCod = placaByPlcCod;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VEI_PLD")
	public Placa getPlacaByVeiPld() {
		return this.placaByVeiPld;
	}

	public void setPlacaByVeiPld(Placa placaByVeiPld) {
		this.placaByVeiPld = placaByVeiPld;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VEI_PLB")
	public Placa getPlacaByVeiPlb() {
		return this.placaByVeiPlb;
	}

	public void setPlacaByVeiPlb(Placa placaByVeiPlb) {
		this.placaByVeiPlb = placaByVeiPlb;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VEI_PLR")
	public Placa getPlacaByVeiPlr() {
		return this.placaByVeiPlr;
	}

	public void setPlacaByVeiPlr(Placa placaByVeiPlr) {
		this.placaByVeiPlr = placaByVeiPlr;
	}

	@Column(name = "VEI_CAT", nullable = false, precision = 8, scale = 3)
	public BigDecimal getVeiCat() {
		return this.veiCat;
	}

	public void setVeiCat(BigDecimal veiCat) {
		this.veiCat = veiCat;
	}

	@Column(name = "VEI_OB1", length = 40)
	public String getVeiOb1() {
		return this.veiOb1;
	}

	public void setVeiOb1(String veiOb1) {
		this.veiOb1 = veiOb1;
	}

	@Column(name = "VEI_OB2", length = 40)
	public String getVeiOb2() {
		return this.veiOb2;
	}

	public void setVeiOb2(String veiOb2) {
		this.veiOb2 = veiOb2;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "COLABORADORVEICULO", joinColumns = { @JoinColumn(name = "CLV_PLC", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "CLV_COL", nullable = false, updatable = false) })
	public Set<Colaborador> getColaboradors() {
		return this.colaboradors;
	}

	public void setColaboradors(Set<Colaborador> colaboradors) {
		this.colaboradors = colaboradors;
	}

}