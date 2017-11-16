package br.com.prova.campanha.test;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import br.com.prova.campaign.dtos.CampaignDTO;
import br.com.prova.campaign.response.CampaignApiResponse.ApiStatusEnum;
import br.com.prova.campaign.start.BootApplication;
import br.com.prova.test.mock.HttpMessageMockProvider;

@DirtiesContext(classMode=ClassMode.AFTER_CLASS)
@SpringBootTest(classes = { HttpMessageMockProvider.class,
        BootApplication.class })
@WebAppConfiguration
@ActiveProfiles({ "unitTestMockWeb", "MessagingMockProvider", "Campaign" })
@Rollback(true)
@Transactional
public class CampaignApiTest extends AbstractJUnit4SpringContextTests{

	@PersistenceContext
    private EntityManager entityManager;

    private MockMvc mockMvc;
    
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Autowired
    private HttpMessageMockProvider httpMessageMock;
    
    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }
    
    @Test
    public void createCampaignTest() throws Exception {
    	  CampaignDTO campaign = new CampaignDTO();
    	  campaign.setCampaignEndDate(new Date());
    	  campaign.setCampaignStartDate(new Date());
    	  campaign.setCampaignName("Teste");
    	  campaign.setClubId(3L);

    	  this.mockMvc.perform(post("/v1" + "/campaign/")
                          .content(httpMessageMock.toJson(campaign))
                          .contentType(MediaType.APPLICATION_JSON_UTF8))
                  .andDo(print())
                  .andExpect(status().isOk())
                  .andExpect(content().contentType(httpMessageMock.CONTENT_TYPE_JSON))
                  .andExpect(jsonPath("$.status", is(ApiStatusEnum.OK.toString())))
                  .andExpect(jsonPath("$.errorCode").value(IsNull.nullValue()))
                  .andExpect(jsonPath("$.body").isNotEmpty());
      }
    
    @Test
    public void createCampaignClubNotFoundTest() throws Exception {
    	  CampaignDTO campaign = new CampaignDTO();
    	  campaign.setCampaignEndDate(new Date());
    	  campaign.setCampaignStartDate(new Date());
    	  campaign.setCampaignName("Teste");
    	  campaign.setClubId(10L);

    	  this.mockMvc.perform(post("/v1" + "/campaign/")
                          .content(httpMessageMock.toJson(campaign))
                          .contentType(MediaType.APPLICATION_JSON_UTF8))
                  .andDo(print())
                  .andExpect(status().isNotFound())
                  .andExpect(content().contentType(httpMessageMock.CONTENT_TYPE_JSON))
                  .andExpect(jsonPath("$.status", is(ApiStatusEnum.ERROR.toString())))
                  .andExpect(jsonPath("$.errorCode").value(IsNull.notNullValue()))
                  .andExpect(jsonPath("$.body").isEmpty());
      }
    
    @Test
    public void serachCampaignNotFoundTest() throws Exception {
    	  CampaignDTO campaign = new CampaignDTO();
    	  campaign.setCampaignId(10L);
    	  campaign.setCampaignEndDate(new Date());
    	  campaign.setCampaignStartDate(new Date());
    	  campaign.setCampaignName("Teste");
    	  campaign.setClubId(10L);

    	  this.mockMvc.perform((get("/v1" + "/campaign/")
        		  .param("campaignId", campaign.getCampaignId().toString())))
          .andDo(MockMvcResultHandlers.print())
          .andExpect(status().isOk())
          .andExpect(content().contentType(httpMessageMock.CONTENT_TYPE_JSON));
      }
      
}
