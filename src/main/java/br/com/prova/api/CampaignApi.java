package br.com.prova.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.prova.campaign.dtos.CampaignDTO;
import br.com.prova.campaign.models.Campaign;
import br.com.prova.campaign.response.CampaignApiResponse;
import br.com.prova.campaign.response.CampaignApiResponse.ApiStatusEnum;
import br.com.prova.campaign.services.CampaignOperations;
import br.com.prova.club.ClubOperations;
import br.com.prova.customer.api.Club;
import br.com.prova.util.TestApiErrorEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Campaign", produces = "application/json", description = "API para realizar operações de campanhas")
@RestController
@RequestMapping("/v1/campaign")
public class CampaignApi {
	
	private static final Log LOGGER = LogFactory.getLog(CampaignApi.class);
	
	@Autowired
	private CampaignOperations campaignOperations;
	
	@Autowired
	private ClubOperations clubOperations;

	@ApiOperation(value = "Cadastrar Campanha", notes = "Cadastra uma campanha")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_ACCEPTED, message = "Campanha cadastrada com sucesso."),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Campanha não cadastrada.") })
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    HttpEntity<CampaignApiResponse<CampaignDTO>> insertCampaign(@RequestBody CampaignDTO campaignDto) {
		LOGGER.info(campaignDto.toString());
		Campaign campaign = parseCampaignDto(campaignDto);
		if(campaign == null){
			CampaignApiResponse<CampaignDTO> response = new CampaignApiResponse<>();
	        response.setStatus(ApiStatusEnum.ERROR);
	        response.setErrorCode("404");
	        response.setErrorDescription("Club informado não encontrado.");
	        response.setBody(null);
	        return new ResponseEntity<CampaignApiResponse<CampaignDTO>>(response, HttpStatus.NOT_FOUND);
		}
		campaign = campaignOperations.createCampaign(campaign);
		campaignDto = parseCampaign(campaign);		
		CampaignApiResponse<CampaignDTO> response = new CampaignApiResponse<>();
        response.setStatus(ApiStatusEnum.OK);
        response.setBody(campaignDto);
        return new ResponseEntity<CampaignApiResponse<CampaignDTO>>(response, HttpStatus.OK);
    }

	/**
     * Consulta todas as campanhas
     * 
     * @return List<CampanhaDTO>
     */
    @ApiOperation(value = "campanha", notes = "Retorna os dados de todas as campanhas")
    @ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK!"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Dados inválidos!") })
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    HttpEntity<CampaignApiResponse<List<Campaign>>> getCampaign() {
    	List<Campaign> campaigns = campaignOperations.searchCampaign();
    	CampaignApiResponse<List<Campaign>> response = new CampaignApiResponse<>();
        response.setStatus(ApiStatusEnum.OK);
        response.setBody(campaigns);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    /**
     * Consulta apenas uma campanha pelo ID da mesma
     * 
     * @return List<CampanhaDTO>
     */
    @ApiOperation(value = "campanha", notes = "Retorna os dados de uma campanha")
    @ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK!"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Dados inválidos!") })
	@RequestMapping(value="/{campaignId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    HttpEntity<CampaignApiResponse<Campaign>> getCampaign(@PathVariable Long campaignId) {
    	Campaign campaign = campaignOperations.searchCampaign(campaignId);
    	CampaignApiResponse<Campaign> response = new CampaignApiResponse<>();
    	if(campaign != null){
    		response.setStatus(ApiStatusEnum.OK);
            response.setBody(campaign);
            return new ResponseEntity<>(response, HttpStatus.OK);
    	}
    	else{
    		response.setStatus(ApiStatusEnum.ERROR);
            response.setBody(null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    	}
    }
    
    @ApiOperation(value = "Atualizar Campanha", notes = "Atualiza uma campanha e retorna uma lista de todas as campanhas atualizadas")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_ACCEPTED, message = "Campanha atualizada com sucesso."),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Campanha não atualizada.") })
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    HttpEntity<CampaignApiResponse<List<Campaign>>> updateCampaign(@RequestBody CampaignDTO campaignDto) {
    	LOGGER.info(campaignDto.toString());
    	Campaign campaign = parseCampaignDto(campaignDto);
		if(campaign == null){
			CampaignApiResponse<List<Campaign>> response = new CampaignApiResponse<>();
	        response.setStatus(ApiStatusEnum.ERROR);
	        response.setErrorCode("404");
	        response.setErrorDescription("Club informado não encontrado.");
	        response.setBody(null);
	        return new ResponseEntity<CampaignApiResponse<List<Campaign>>>(response, HttpStatus.NOT_FOUND);
		}
		List<Campaign> campaigns = campaignOperations.updateCampaign(campaign);
		CampaignApiResponse<List<Campaign>> response = new CampaignApiResponse<>();
        response.setStatus(ApiStatusEnum.OK);
        response.setBody(campaigns);
        return new ResponseEntity<CampaignApiResponse<List<Campaign>>>(response, HttpStatus.OK);
    }
	
    @ApiOperation(value = "Excluir Campanha", notes = "Exclui uma campanha de acordo com o id informado")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_ACCEPTED, message = "Campanha excluida com sucesso."),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Campanha não excluida.") })
	@RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    HttpEntity<CampaignApiResponse<String>> deleteCampaign(@RequestBody Long campaignId) {
    	campaignOperations.deleteCampaign(campaignId);
    	CampaignApiResponse<String> response = new CampaignApiResponse<>();
    	response.setStatus(ApiStatusEnum.OK);
        response.setBody("Campanha com ID " + campaignId + " foi excluída com sucesso.");
        return new ResponseEntity<CampaignApiResponse<String>>(response, HttpStatus.OK);
    }

    private Campaign parseCampaignDto(CampaignDTO campaignDTO){
    	Campaign campaign = new Campaign();
    	campaign.setCampaignId(campaignDTO.getCampaignId());
    	campaign.setCampaignName(campaignDTO.getCampaignName());
    	campaign.setCampaignStartDate(campaignDTO.getCampaignStartDate());
    	campaign.setCampaignEndDate(campaignDTO.getCampaignEndDate());
    	Club club = clubOperations.findClubById(campaignDTO.getClubId());
    	if(club == null){
    		return null;
    	}
    	campaign.setClubs(new ArrayList<>());
    	campaign.getClubs().add(club);
    	campaign.setCreatedDate(new Date());
    	return campaign;
    }
    
    private CampaignDTO parseCampaign(Campaign campaign){
    	CampaignDTO campaignDTO = new CampaignDTO();
    	campaignDTO.setCampaignId(campaign.getCampaignId());
    	campaignDTO.setCampaignName(campaign.getCampaignName());
    	campaignDTO.setCampaignStartDate(campaign.getCampaignStartDate());
    	campaignDTO.setCampaignEndDate(campaign.getCampaignEndDate());
    	return campaignDTO;
    }
    
    @ExceptionHandler({ IllegalArgumentException.class,
        HttpMessageNotReadableException.class })
	HttpEntity<CampaignApiResponse<Object>> handleIllegalArgumentException(
	        Exception e) {
	    return createErrorResponse(TestApiErrorEnum.INVALID_ARGUMENT,
	            e.getMessage());
	}
    	
    
    @ExceptionHandler({ EmptyResultDataAccessException.class })
	HttpEntity<CampaignApiResponse<Object>> handleNotFoundException(
	        Exception e) {
	    return createErrorResponse(TestApiErrorEnum.REGISTRY_NOT_FOUND,
	            "Campanha não encontrada para realizar a exclusão.");
	}
    
	@ExceptionHandler({ Exception.class })
	HttpEntity<CampaignApiResponse<Object>> handleException(Exception e) {
	    LOGGER.error("Erro inesperado!", e);
	    return createErrorResponse(TestApiErrorEnum.UNEXPECTED_TECHNICAL_ERROR,
	            e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	private HttpEntity<CampaignApiResponse<Object>> createErrorResponse(
			TestApiErrorEnum errorCode, String description) {
	    return createErrorResponse(errorCode, description,
	            HttpStatus.BAD_REQUEST);
	}
	
	/**
     * Cria um ResponseEntity de erro mas atribuindo o código de erro e mensagens indicados
     * 
     * @param errorCode
     * @param description
     * @return
     */
    private HttpEntity<CampaignApiResponse<Object>> createErrorResponse(
    		TestApiErrorEnum errorCode, String description,
            HttpStatus statusCode) {
    	CampaignApiResponse<Object> response = new CampaignApiResponse<>();
        response.setStatus(ApiStatusEnum.ERROR);
        response.setErrorCode(errorCode.toString());
        response.setErrorDescription(description);
        return new ResponseEntity<>(response, statusCode);
    }
    
}
