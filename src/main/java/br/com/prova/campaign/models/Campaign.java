package br.com.prova.campaign.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "\"campaign\"")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Campaign implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_SEQ")
    @Column(name = "customer_id", unique = true)
	private Long campaignId;
	
    @Column(name = "campaign_name")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
	private Integer campaignName;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "effective_date", nullable = false)
	private Date effectiveDate;
	
	@Size(max = 5, min = 1)
    @NotNull
    @Column(name = "club_id", nullable = false, length = 5)
	private Integer clubId;

	public Integer getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(Integer campaignName) {
		this.campaignName = campaignName;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Integer getClubId() {
		return clubId;
	}

	public void setClubId(Integer clubId) {
		this.clubId = clubId;
	}

	public Long getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(Long campaignId) {
		this.campaignId = campaignId;
	}
	
	/*@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @JoinColumn(name = "\"invitation_id_Invitation\"", nullable = true)*/


	
}
