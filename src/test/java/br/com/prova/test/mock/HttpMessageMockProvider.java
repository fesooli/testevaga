package br.com.prova.test.mock;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpInputMessage;
import org.springframework.mock.http.MockHttpOutputMessage;

@Configuration
@Profile("unitTestMockWeb")
public class HttpMessageMockProvider {

	@SuppressWarnings("rawtypes")
    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    public final MediaType CONTENT_TYPE_JSON = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters)
                .stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny().get();

    }

    @SuppressWarnings("unchecked")
    public String toJson(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(o,
                MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

    @SuppressWarnings("unchecked")
    public <T> T fromJson(String str, Class<T> type) throws IOException {
        MockHttpInputMessage mockHttpInputMessage = new MockHttpInputMessage(
                str.getBytes("UTF-8"));

        return (T) this.mappingJackson2HttpMessageConverter.read(type,
                mockHttpInputMessage);

    }

    @Bean
    @Primary
    public ResourceHandler getResourceHandler() {
        return Mockito.mock(ResourceHandler.class);
    }
}
