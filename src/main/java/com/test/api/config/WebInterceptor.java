/**
 * 
 */
package com.test.api.config;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import com.test.api.interceptors.APILoggingInterceptor;

/**
 * @author MGupta
 *
 */
@Configuration
public class WebInterceptor {

	/**
	 * Creating rest template with interceptors.</br>
	 * 
	 * @return restTemplate with set interceptors
	 */
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate template = new RestTemplate();
		
		Supplier<List<ClientHttpRequestInterceptor>> interceptors = () -> {
			List<ClientHttpRequestInterceptor> interceptor = new ArrayList<>();//Empty List
			interceptor.add(new APILoggingInterceptor());//Adding the interceptor
			
			return interceptor;//result
		};
		
		template.setInterceptors(interceptors.get());//setting the interceptors to rest template
		
		return template;//result
	}
}
