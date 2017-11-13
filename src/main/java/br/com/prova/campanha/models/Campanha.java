package br.com.prova.campanha.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "\"BankAccount\"")
@JsonIgnoreProperties(ignoreUnknown = true)
@SequenceGenerator(name = "BANK_ACCOUNT_SEQ", sequenceName = "\"BankAccount_bank_account_id_seq\"", allocationSize = 1)
public class Campanha {

	@Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
	private Integer campanhaId;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update", nullable = false)
	private Date dataVigencia;
	
	@Size(max = 5, min = 1)
    @NotNull
    @Column(name = "agency_number", nullable = false, length = 5)
	private Integer timeId;
	
	/*@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @JoinColumn(name = "\"invitation_id_Invitation\"", nullable = true)*/

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
