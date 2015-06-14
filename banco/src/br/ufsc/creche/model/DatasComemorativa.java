package br.ufsc.creche.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name = "seq_data_comemorativa", sequenceName = "seq_data_comemorativa", allocationSize = 1, initialValue = 1)
@Table(name = "datacomemorativa", schema="public")
public class DatasComemorativa implements Serializable {

	private static final long serialVersionUID = 2471765773806278661L;

	private Integer codigoDataComemorativa;
	private String descricao;
	private   Date data;

	@Id
	@GeneratedValue(generator="seq_data_comemorativa")
	@Column(name="cd_data_comemorativa", unique=true, nullable=false )
	public Integer getCodigoDataComemorativa() {
		return codigoDataComemorativa;
	}

	public void setCodigoDataComemorativa(Integer codigoDataComemorativa) {
		this.codigoDataComemorativa = codigoDataComemorativa;
	}

	@Column(name = "descricao", length = 50)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "data", length = 10, nullable=false)
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}






}