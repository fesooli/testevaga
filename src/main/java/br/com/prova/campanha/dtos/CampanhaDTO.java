package br.com.prova.campanha.dtos;

import java.util.Date;

public class CampanhaDTO {

	private Integer campanhaId;
	
	private String nomeCampanha;
	
	private Date dataVigencia;
	
	private Integer timeId;

	public Integer getCampanhaId() {
		return campanhaId;
	}

	public void setCampanhaId(Integer campanhaId) {
		this.campanhaId = campanhaId;
	}

	public Date getDataVigencia() {
		return dataVigencia;
	}

	public void setDataVigencia(Date dataVigencia) {
		this.dataVigencia = dataVigencia;
	}

	public Integer getTimeId() {
		return timeId;
	}

	public void setTimeId(Integer timeId) {
		this.timeId = timeId;
	}
	
}
