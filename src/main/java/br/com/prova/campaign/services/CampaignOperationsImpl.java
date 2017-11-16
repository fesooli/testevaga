package br.com.prova.campaign.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.prova.campaign.models.Campaign;

@Service
public class CampaignOperationsImpl implements CampaignOperations {
	
	@Autowired
	private CampaignRepository campaignRepository;

	@Override
	public Campaign createCampaign(Campaign campaign) {
		List<Campaign> campaigns = campaignRepository.findCampaignByEffectiveDate(campaign);
		campaigns.forEach(c -> { //Adiciona um dia a mais por ser do mesmo período
			c.setCampaignEndDate(addDate(c.getCampaignEndDate()));
		});
		
		//Faz a validação das datas
		campaigns = validateEffectiveDateOnCreateCampaign(campaign, campaigns);
		
		campaigns.forEach(c -> {
			campaignRepository.saveAndFlush(c);
		});
		
		return campaignRepository.save(campaign);
	}

	@Override
	public List<Campaign> searchCampaign() {
		List<Campaign> campaigns = campaignRepository.findAll();
		//Retira da lista todas as campanhas com a data de vigência expirada
		campaigns.removeIf(campaign -> validateEffectiveDateOnSearchCampaign(campaign));
		return campaigns;
	}

	@Override
	public List<Campaign> updateCampaign(Campaign campaign) {
		List<Campaign> campaigns = campaignRepository.findCampaignByEffectiveDate(campaign);
		campaigns.forEach(c -> { //Adiciona um dia a mais por ser do mesmo período
			c.setCampaignEndDate(addDate(c.getCampaignEndDate()));
		});
		
		//Faz a validação das datas
		campaigns = validateEffectiveDateOnCreateCampaign(campaign, campaigns);
		
		campaigns.forEach(c -> {
			campaignRepository.saveAndFlush(c);
		});
		
		campaign = campaignRepository.saveAndFlush(campaign);
		campaigns.add(campaign);
		return campaigns;
	}

	@Override
	public void deleteCampaign(Long campaignId) {
		campaignRepository.delete(campaignId);
	}

	@Override
	public Campaign searchCampaign(Long campaignId) {
		return campaignRepository.findOne(campaignId);
	}
	
	private Campaign compareDates(Campaign campaignOne, Campaign campaignTwo){
		if(campaignOne.getCampaignEndDate().compareTo(campaignTwo.getCampaignEndDate()) == 0){
			if(campaignOne.getCampaignId() < campaignTwo.getCampaignId()){
				campaignOne.setCampaignEndDate(addDate(campaignOne.getCampaignEndDate()));
				return campaignOne;
			}
			else{
				campaignTwo.setCampaignEndDate(addDate(campaignTwo.getCampaignEndDate()));
				return campaignTwo;
			}
		}
		return null;
	}
	
	private Date addDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	private List<Campaign> validateEffectiveDateOnCreateCampaign(Campaign campaign, List<Campaign> campaigns){
		campaigns.forEach(c -> {			
			try{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date dateWithoutTime = sdf.parse(sdf.format(campaign.getCampaignEndDate()));				
				if(c.getCampaignEndDate().compareTo(dateWithoutTime) == 0){
					c.setCampaignEndDate(addDate(c.getCampaignEndDate()));
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		});

		List<Campaign> cams = campaigns;
		while(true){
			int cont = 0;
			for(int i = 0; i < campaigns.size(); i++){				
				for(Campaign c : cams){
					if(campaigns.get(i).getCampaignId().compareTo(c.getCampaignId()) != 0){
						Campaign cam = compareDates(campaigns.get(i), c);
						if(cam != null){
							if(campaigns.get(i).getCampaignId().compareTo(cam.getCampaignId()) == 0){
								campaigns.get(i).setCampaignEndDate(cam.getCampaignEndDate());
								cont++;
							}
						}						
					}
				}				
			}
			if(cont == 0){
				break;
			}
		}
		
		return campaigns;
	}
	
	private Boolean validateEffectiveDateOnSearchCampaign(Campaign campaign){
		return campaign.getCampaignEndDate().before(new Date()) ? true : false;
	}
	
}
