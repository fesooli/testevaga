package br.com.prova.customer.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.Date;
import java.util.Random;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import br.com.prova.campaign.start.BootApplication;
import br.com.prova.customer.api.CustomerDto;
import br.com.prova.test.mock.HttpMessageMockProvider;

@DirtiesContext(classMode=ClassMode.AFTER_CLASS)
@SpringBootTest(classes = { HttpMessageMockProvider.class,
        BootApplication.class })
@WebAppConfiguration
@ActiveProfiles({ "unitTestMockWeb", "MessagingMockProvider", "Customer" })
@Rollback(true)
@Transactional
public class CustomerApiTest extends AbstractJUnit4SpringContextTests {

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
    public void createCustomerConflictTest() throws Exception {
    	  CustomerDto customer = new CustomerDto();
    	  customer.setBornDate(new Date());
    	  customer.setClubId(2L);
    	  customer.setEmail("fellipe@hotmail.com");
    	  customer.setFullName("Fellipe SSooo");

    	  this.mockMvc.perform(post("/v1" + "/customer/")
                          .content(httpMessageMock.toJson(customer))
                          .contentType(MediaType.APPLICATION_JSON_UTF8))
                  .andDo(print())
                  .andExpect(status().isConflict())
                  .andExpect(content().contentType(httpMessageMock.CONTENT_TYPE_JSON))
                  .andExpect(jsonPath("$.errorCode").value(IsNull.notNullValue()))
                  .andExpect(jsonPath("$.body").isEmpty());
      }
    
    @Test
    public void createCustomerTest() throws Exception {
    	  CustomerDto customer = new CustomerDto();
    	  customer.setBornDate(new Date());
    	  customer.setClubId(2L);
    	  Random r = new Random();    	  
    	  customer.setEmail("fellipe" + r.nextInt() + "@hotmail.com");
    	  customer.setFullName("Fellipe SSooo");

    	  this.mockMvc.perform(post("/v1" + "/customer/")
                          .content(httpMessageMock.toJson(customer))
                          .contentType(MediaType.APPLICATION_JSON_UTF8))
                  .andDo(print())
                  .andExpect(status().isOk())
                  .andExpect(content().contentType(httpMessageMock.CONTENT_TYPE_JSON));
      }
}
