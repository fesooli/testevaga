package br.com.prova.campaign.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.prova.customer.api.Club;

@Entity
@Table(name = "\"campaign\"")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Campaign implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "campaign_id", unique = true)
	private Long campaignId;
	
    @Column(name = "campaign_name", nullable = false)
	private String campaignName;
	
    @Temporal(TemporalType.DATE)
    @Column(name = "campaign_start_date", nullable = false)
	private Date campaignStartDate;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "campaign_end_date", nullable = false)
	private Date campaignEndDate;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "created_date", nullable = false)
	private Date createdDate;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @JoinColumn(name = "\"club_club_id\"", nullable = true)
	private List<Club> clubs;

	public Long getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(Long campaignId) {
		this.campaignId = campaignId;
	}

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public Date getCampaignStartDate() {
		return campaignStartDate;
	}

	public void setCampaignStartDate(Date campaignStartDate) {
		this.campaignStartDate = campaignStartDate;
	}

	public Date getCampaignEndDate() {
		return campaignEndDate;
	}

	public void setCampaignEndDate(Date campaignEndDate) {
		this.campaignEndDate = campaignEndDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public List<Club> getClubs() {
		return clubs;
	}

	public void setClubs(List<Club> clubs) {
		this.clubs = clubs;
	}

}
