package br.com.prova.campaign.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.prova.campaign.models.Campaign;

public class CampaignOperationsImpl implements CampaignOperations {
	
	@Autowired
	private CampaignRepository campaignRepository;

	@Override
	public Campaign createCampaign(Campaign campaign) {
		return campaignRepository.save(campaign);
	}

	@Override
	public List<Campaign> searchCampaign() {
		return campaignRepository.findAll();
	}

	@Override
	public Campaign updateCampaign(Campaign campaign) {
		return campaignRepository.save(campaign);
	}

	@Override
	public void deleteCampaign(Long campaignId) {
		campaignRepository.delete(campaignId);
	}

	@Override
	public Campaign searchCampaign(Campaign campaign) {
		return campaignRepository.findOne(campaign.getCampaignId());
	}

}
