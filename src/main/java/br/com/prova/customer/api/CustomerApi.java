package br.com.prova.customer.api;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.prova.campaign.response.CampaignApiResponse;
import br.com.prova.campaign.response.CampaignApiResponse.ApiStatusEnum;
import br.com.prova.club.ClubOperations;
import br.com.prova.util.TestApiErrorEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Customer", produces = "application/json", description = "API para realizar operações de customer")
@RestController
@RequestMapping("/v1/customer")
public class CustomerApi {

	private static final Log LOGGER = LogFactory.getLog(CustomerApi.class);
	
	@Autowired
	private CustomerOperations customerOperations;
	
	@Autowired
	private ClubOperations clubOperations;

	@ApiOperation(value = "Cadastrar cliente", notes = "Cadastra um cliente")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_ACCEPTED, message = "Cliente cadastrada com sucesso."),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Cliente não cadastrada.") })
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    HttpEntity<CampaignApiResponse<Customer>> insertCustomer(@RequestBody CustomerDto customerDto) {
		Customer customer = parseCustomerDto(customerDto);
		Customer customerResponse = customerOperations.findCustomerByEmail(customer.getEmail());
		if(customerResponse != null){
			CampaignApiResponse<Customer> response = new CampaignApiResponse<>();
	        response.setStatus(ApiStatusEnum.ERROR);
	        response.setErrorCode("409");
	        response.setErrorDescription("Cliente já cadastrado com esse email.");
	        response.setBody(null);
	        return new ResponseEntity<CampaignApiResponse<Customer>>(response, HttpStatus.CONFLICT);
		}
		else{
			Club club = clubOperations.findClubById(customer.getClub().getClubId());
			if(club == null){
				CampaignApiResponse<Customer> response = new CampaignApiResponse<>();
				response.setStatus(ApiStatusEnum.ERROR);
				response.setErrorCode("404");
				response.setErrorDescription("Time informado não encontrado.");
		        response.setBody(null);
		        return new ResponseEntity<CampaignApiResponse<Customer>>(response, HttpStatus.NOT_FOUND);
			}
			customer.setClub(club);
			customer = customerOperations.createCustomer(customer);
			CampaignApiResponse<Customer> response = new CampaignApiResponse<>();
	        response.setStatus(ApiStatusEnum.OK);
	        response.setBody(customer);
	        return new ResponseEntity<CampaignApiResponse<Customer>>(response, HttpStatus.OK);
		}		
    }

	/**
     * Consulta todas as clientes
     * 
     * @return List<ClienteDTO>
     */
    @ApiOperation(value = "customer", notes = "Retorna os dados de todos os clientes")
    @ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK!"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Dados inválidos!") })
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    HttpEntity<CampaignApiResponse<List<Customer>>> getCustomer() {
    	List<Customer> customers = customerOperations.searchCustomers();
    	CampaignApiResponse<List<Customer>> response = new CampaignApiResponse<>();
        response.setStatus(ApiStatusEnum.OK);
        response.setBody(customers);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
    @ApiOperation(value = "Atualizar cliente", notes = "Atualiza um cliente")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_ACCEPTED, message = "Cliente atualizada com sucesso."),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Cliente não atualizada.") })
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    HttpEntity<CampaignApiResponse<Customer>> updateCustomer(@RequestBody CustomerDto customerDto) {
		Customer customer = parseCustomerDto(customerDto);
    	customer = customerOperations.updateCustomer(customer);
		CampaignApiResponse<Customer> response = new CampaignApiResponse<>();
        response.setStatus(ApiStatusEnum.OK);
        response.setBody(customer);
        return new ResponseEntity<CampaignApiResponse<Customer>>(response, HttpStatus.OK);
    }
	
    @ApiOperation(value = "Consultar CLiente por email", notes = "Consultar CLiente por email")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_ACCEPTED, message = "Cliente consultado com sucesso."),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Cliente não consultado.") })
	@RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    HttpEntity<CampaignApiResponse<Customer>> getCustomerByEmail(@RequestBody String email) {
    	Customer customer = customerOperations.findCustomerByEmail(email);
    	CampaignApiResponse<Customer> response = new CampaignApiResponse<>();
    	response.setStatus(ApiStatusEnum.OK);
        response.setBody(customer);
        return new ResponseEntity<CampaignApiResponse<Customer>>(response, HttpStatus.OK);
    }
    
    private Customer parseCustomerDto(CustomerDto customerDto){
    	Customer customer = new Customer();
    	customer.setCustomerId(customerDto.getCustomerId());
    	customer.setEmail(customerDto.getEmail());
    	customer.setBornDate(customerDto.getBornDate());
    	customer.setFullName(customerDto.getFullName());
    	customer.setClub(new Club());
    	customer.getClub().setClubId(customerDto.getClubId());
    	return customer;
    }
    
    private CustomerDto parseCustomer(Customer customer){
    	CustomerDto customerDto = new CustomerDto();
    	customerDto.setCustomerId(customer.getCustomerId());
    	customerDto.setEmail(customer.getEmail());
    	customerDto.setBornDate(customer.getBornDate());
    	customerDto.setFullName(customer.getFullName());
    	customerDto.setClubId(customer.getClub().getClubId());
    	return customerDto;
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
