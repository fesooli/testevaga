package br.com.prova.campaign.services;

import java.util.List;

import br.com.prova.campaign.models.Campaign;


public interface CampaignOperations {

	Campaign createCampaign(Campaign campaign);
	
	List<Campaign> searchCampaign();
	
	List<Campaign> updateCampaign(Campaign campaign);
	
	void deleteCampaign(Long campaignId);
	
	Campaign searchCampaign(Long campaignId);
}
