package br.com.prova.campaign.services;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.prova.campaign.models.Campaign;

public interface CampaignRepository extends JpaRepository<Campaign, Long>{

}
