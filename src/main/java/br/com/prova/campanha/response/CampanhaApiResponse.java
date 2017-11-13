package br.com.prova.campanha.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Esta classe é responsavel por representar o cabeçalho de status de uma
 * resposta da API. O conteudo da resposta é representado pelo atributo <b>body
 * <b>
 * 
 * @author Fellipe Oliveira
 *
 * @param <T> Tipo de objeto que participa da resposta
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "OpApiResponse", description = "Representa a resposta de um serviço")
public class CampanhaApiResponse<T> {

	@ApiModelProperty(value = "Status do serviço: OK ou ERROR", required = true)
    private ApiStatusEnum status;
    @ApiModelProperty(value = "Código de erro", required = false)
    private String errorCode;
    @ApiModelProperty(value = "Descrição do erro", required = false)
    private String errorDescription;
    @ApiModelProperty(value = "Conteudo da mensagem", required = false)
    private T body;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public ApiStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ApiStatusEnum status) {
        this.status = status;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public enum ApiStatusEnum {
        OK, ERROR
    }
}
