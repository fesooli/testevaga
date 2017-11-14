package br.com.prova.api;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.prova.campaign.dtos.CampaignDTO;
import br.com.prova.campaign.response.CampaignApiResponse;
import br.com.prova.campaign.response.CampaignApiResponse.ApiStatusEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Campaign", produces = "application/json", description = "User REST for Integration Testing")
@RestController
@RequestMapping("/campaign")
@Transactional
public class CampaignApi {
	
	private static final Log LOGGER = LogFactory.getLog(CampaignApi.class);

	@ApiOperation(value = "Cadastrar Campanha", notes = "Cadastra uma campanha")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_ACCEPTED, message = "Campanha cadastrada com sucesso."),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Campanha não cadastrada.") })
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    HttpEntity<CampaignApiResponse> insertCampaign(@RequestBody CampaignDTO campaignDto) {
		
        return null;
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
    HttpEntity<CampaignApiResponse<List<CampaignDTO>>> getCampaign() {

    	CampaignApiResponse<List<CampaignDTO>> response = new CampaignApiResponse<>();
        response.setStatus(ApiStatusEnum.OK);
        
        LOGGER.info("Campanhas: ");

        // Retorna campanhas
        response.setBody(null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @ApiOperation(value = "Atualizar Campanha", notes = "Atualiza uma campanha")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_ACCEPTED, message = "Campanha atualizada com sucesso."),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Campanha não atualizada.") })
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    HttpEntity<CampaignApiResponse> updateCampaign(@RequestBody boolean teste) {

        return null;
    }
	
    @ApiOperation(value = "Excluir Campanha", notes = "Exclui uma campanha de acordo com o id informado")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_ACCEPTED, message = "Campanha excluida com sucesso."),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Campanha não excluida.") })
	@RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    HttpEntity<CampaignApiResponse> deleteCampaign(@RequestBody Integer campanhaId) {

        return null;
    }


}
