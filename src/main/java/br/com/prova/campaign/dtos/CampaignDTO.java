package br.com.prova.campaign.dtos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class CampaignDTO {

	private Long campaignId;
	
	private String campaignName;

	private Date campaignStartDate;

	private Date campaignEndDate;
	
	private Long clubId;

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public Long getClubId() {
		return clubId;
	}

	public void setClubId(Long clubId) {
		this.clubId = clubId;
	}

	public Long getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(Long campaignId) {
		this.campaignId = campaignId;
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

	@Override
	public String toString() {
		return "CampaignDTO [campaignId=" + campaignId + ", campaignName=" + campaignName + ", campaignStartDate="
				+ campaignStartDate + ", campaignEndDate=" + campaignEndDate + ", clubId=" + clubId + "]";
	}
		
}
