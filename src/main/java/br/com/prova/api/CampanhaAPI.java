package br.com.prova.api;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.prova.campanha.dtos.CampanhaDTO;
import br.com.prova.campanha.response.CampanhaApiResponse;
import br.com.prova.campanha.response.CampanhaApiResponse.ApiStatusEnum;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public class CampanhaAPI {
	
	private static final Log LOGGER = LogFactory.getLog(CampanhaAPI.class);

	@RequestMapping(value = "/campanha", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    HttpEntity<CampanhaApiResponse> campanha(@RequestBody String teste) {

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
	@RequestMapping(value = "/campanha", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    HttpEntity<CampanhaApiResponse<List<CampanhaDTO>>> campanha() {

    	CampanhaApiResponse<List<CampanhaDTO>> response = new CampanhaApiResponse<>();
        response.setStatus(ApiStatusEnum.OK);
        
        LOGGER.info("Campanhas: ");

        // Retorna campanhas
        response.setBody(null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/campanha", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    HttpEntity<CampanhaApiResponse> campanha(@RequestBody boolean teste) {

        return null;
    }
	
	@RequestMapping(value = "/campanha", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    HttpEntity<CampanhaApiResponse> campanha(@RequestBody Integer campanhaId) {

        return null;
    }


}
