package br.com.prova.campaign.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.prova.campaign.models.Campaign;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long>{

	@Query("select c from Campaign c "
            + "where c.campaignStartDate = :#{#campaign.campaignStartDate} ")
	List<Campaign> findCampaignByEffectiveDate(@Param("campaign") Campaign campaign);
}
